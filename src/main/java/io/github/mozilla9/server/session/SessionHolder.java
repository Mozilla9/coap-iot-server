/**
 * @project: coap-iot-server
 * @created: 05.06.17
 * @author: Serge Maslyakov
 */


package io.github.mozilla9.server.session;


import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;


public class SessionHolder {

    private SecureRandom random = new SecureRandom();
    private final long expirationTimeoutMs;   // 5 min
    private HashMap<Integer, Integer> serialNumberToTokenMapper = new HashMap<Integer, Integer>();
    private HashMap<Integer, CoapSession> store = new HashMap<Integer, CoapSession>();


    public SessionHolder(long expirationTimeoutMs) {
        this.expirationTimeoutMs = expirationTimeoutMs;
    }

    public synchronized CoapSession createSession(Integer serialNumber) {

        Integer oldToken = serialNumberToTokenMapper.get(serialNumber);

        if (oldToken != null) {
            store.remove(oldToken);
        }

        Integer token = sessionTokenGenerator();
        CoapSession session = new CoapSession(token, serialNumber);
        serialNumberToTokenMapper.put(serialNumber, token);
        store.put(token, session);

        return session;
    }

    public synchronized CoapSession getSession(Integer token) {
        CoapSession session = store.get(token);

        if (session != null) {
            if (isSessionOnExpired(session)) {
                serialNumberToTokenMapper.remove(session.getSerialNumber());
                store.remove(token);
                session = null;
            } else {
                session.setUpdateTs(new Date());
            }
        }

        return session;
    }

    private boolean isSessionOnExpired(CoapSession session) {
        Date now = new Date();
        Date lastUseDate = session.getUpdateTs();

        return now.getTime() - lastUseDate.getTime() > expirationTimeoutMs;
    }

    @org.jetbrains.annotations.NotNull
    private Integer sessionTokenGenerator() {
        return random.nextInt();
    }

}

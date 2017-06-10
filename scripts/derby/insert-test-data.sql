--
-- @project: coap-iot-server
-- @created: 05.06.17
-- @author:  Serge Maslyakov
--



connect 'jdbc:derby://localhost:1527/coap_iot_db;create=true' user 'root' password 'root';


insert into FIRMWARE_DATA
(
    CREATE_TS,
    TOKEN,
    VERSION,
    DESCRIPTION,
    FILE_URL
)
values
(
    CURRENT_TIMESTAMP,
    333,
    '1.013',
    'iot-gsm',
    'ftp://mozilla9.github.io/firmware/iot_1_013.bin'
);

insert into DEVICE_CONFIG
(
    CREATE_TS,
    TOKEN,
    FREQ_OF_SENDING_DATA,
    DURATION_OF_SESSION
)
values
(
    CURRENT_TIMESTAMP,
    999,
    900000,
    300000
);


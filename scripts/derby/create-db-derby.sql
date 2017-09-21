--
-- @project: coap-iot-server
-- @created: 05.06.17
-- @author:  Serge Maslyakov
--


--- TODO: don't forget about credentials
connect 'jdbc:derby://localhost:1527/coap_iot_db;create=true' user 'root' password 'root';


----- FirmwareData -----
create table FIRMWARE_DATA
(
    ---------- staff fields --------
    ID integer not null generated always as identity(start with 13, increment by 9),
    CREATE_TS timestamp,
    UPDATE_TS timestamp,
    DELETE_TS timestamp,
    PRIMARY KEY (ID),
    --------------------------------

    TOKEN integer,
    VERSION varchar(2048),
    DESCRIPTION varchar(2048),
    FILE_URL varchar(2048)
);


----- DeviceConfig -----
create table DEVICE_CONFIG
(
    ---------- staff fields --------
    ID integer not null generated always as identity(start with 13, increment by 9),
    CREATE_TS timestamp,
    UPDATE_TS timestamp,
    DELETE_TS timestamp,
    PRIMARY KEY (ID),
    --------------------------------

    TOKEN integer,
    FREQ_OF_SENDING_DATA integer,
    DURATION_OF_SESSION integer
);


-- IotDevice --
create table IOT_DEVICE
(
    ---------- staff fields --------
    ID integer not null,
    CREATE_TS timestamp,
    UPDATE_TS timestamp,
    DELETE_TS timestamp,
    PRIMARY KEY (ID),
    --------------------------------

    ADDRESS varchar(2048),
    LATITUDE double,
    LONGITUDE double,

    CONFIG_ID integer,
    FIRMWARE_ID integer
);


alter table IOT_DEVICE add constraint FK_IOT_DEVICE_TO_CONFIG
foreign key (CONFIG_ID) references DEVICE_CONFIG(ID);

alter table IOT_DEVICE add constraint FK_IOT_DEVICE_TO_FIRMWARE
foreign key (FIRMWARE_ID) references FIRMWARE_DATA(ID);


----- DeviceData -----
create table DEVICE_DATA
(
    ---------- staff fields --------
    ID integer not null generated always as identity(start with 13, increment by 9),
    CREATE_TS timestamp,
    UPDATE_TS timestamp,
    DELETE_TS timestamp,
    PRIMARY KEY (ID),
    --------------------------------

    DATESTAMP timestamp,
    ALARM_MASK integer,

    RAW_DATA varchar(2048),

    DEVICE_ID integer not null
);

alter table DEVICE_DATA add constraint FK_DEVICE_DATA_TO_DEVICE
foreign key (DEVICE_ID) references IOT_DEVICE(ID);


----- LoginData -----
create table LOGIN_DATA
(
    ---------- staff fields --------
    ID integer not null generated always as identity(start with 13, increment by 9),
    CREATE_TS timestamp,
    UPDATE_TS timestamp,
    DELETE_TS timestamp,
    PRIMARY KEY (ID),
    --------------------------------

    CONFIG_TOKEN integer,
    FIRMWARE_TOKEN integer,
    SIGNAL_QUALITY double,
    BATTERY_VOLTAGE double,
    BATTERY_VOLTAGE_MIN double,

    RAW_DATA varchar(2048),

    DEVICE_ID integer not null
);

alter table LOGIN_DATA add constraint FK_LOGIN_DATA_TO_DEVICE
foreign key (DEVICE_ID) references IOT_DEVICE(ID);

disconnect all;
exit;

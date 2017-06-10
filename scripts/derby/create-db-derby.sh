#
# @project: coap-iot-server
# @created: 05.06.17
# @author:  Serge Maslyakov
#

#!/usr/bin/env bash


DB_DIR_NAME="coap-iot-db"
PATH_TO_APP="/home/root"

# Check that the dir is not exists
if [ ! -d "${PATH_TO_APP}/${DB_DIR_NAME}" ]; then

    # Check that the dir is not exists
    if [ ! -d "${PATH_TO_APP}" ]; then
        mkdir "${PATH_TO_APP}"
    fi

    echo "--- Start db creation!"

    mkdir "${PATH_TO_APP}/${DB_DIR_NAME}"

    cp *.sql "${PATH_TO_APP}/${DB_DIR_NAME}/"

    cd "${PATH_TO_APP}/${DB_DIR_NAME}"

    # Process all files in directory
    for sql_file in *; do

            # Check that the file is exists
            if [ "${sql_file##*.}" = "sql" ]; then

                echo "--- Run sql file ${sql_file}\r\n"

                java -Dderby.system.home=/home/root/coap-iot-db -jar "${JAVA_HOME}/db/lib/derbyrun.jar" ij "${sql_file}"

                if [ "0" != $? ]; then
                    echo "Error"
                    exit -1
                fi
            fi
        done

fi


echo "--- Finished!"
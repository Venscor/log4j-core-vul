package com.venscor.jdni.vul;

import javax.naming.InitialContext;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-07
 */
public class RMIClient {
    public static void main(String[] args) {
        try {
            //            System.out.println("before set:");
            //            System.out.println(
            //                    "java.rmi.server.useCodebaseOnly=" + System.getProperty("java.rmi.server
            //                    .useCodebaseOnly"));
            //            System.out.println("com.sun.jndi.rmi.object.trustURLCodebase=" + System
            //                    .getProperty("com.sun.jndi.rmi.object.trustURLCodebase"));
            //
            //            System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
            //            System.setProperty("java.rmi.server.useCodebaseOnly", "false");
            //
            //            System.out.println("after set:");
            //
            //            System.out.println(
            //                    "java.rmi.server.useCodebaseOnly=" + System.getProperty("java.rmi.server
            //                    .useCodebaseOnly"));
            //            System.out.println("com.sun.jndi.rmi.object.trustURLCodebase=" + System
            //                    .getProperty("com.sun.jndi.rmi.object.trustURLCodebase"));

            InitialContext context = new InitialContext();
            String name = "rmi://127.0.0.1:53424/exp";
            Object remoteObj = context.lookup(name);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

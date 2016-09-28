package com.github.drizzleme.memcached;

import java.io.*;

/**
 * Created with jmemcache
 *
 * @author ; DRIZZLEME
 *         DATE : 2016/9/26
 */
public class ContextObjectInputStream extends ObjectInputStream{
    ClassLoader mLoader;

    public ContextObjectInputStream(InputStream in, ClassLoader loader ) throws IOException, SecurityException {
        super( in );
        mLoader = loader;
    }

    protected Class resolveClass( ObjectStreamClass v ) throws IOException, ClassNotFoundException {
        if ( mLoader == null )
            return super.resolveClass( v );
        else
            return Class.forName( v.getName(), true, mLoader );
    }
}

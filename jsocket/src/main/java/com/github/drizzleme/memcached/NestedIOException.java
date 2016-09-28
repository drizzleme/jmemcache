package com.github.drizzleme.memcached;

import java.io.IOError;
import java.io.IOException;

/**
 * Created with jmemcache
 *
 * @author ; DRIZZLEME
 *         DATE : 2016/9/26
 */
public class NestedIOException extends IOException{
    /**
     * Create a new <code>NestedIOException</code> instance.
     * @param cause object of type throwable
     */
    public NestedIOException( Throwable cause ) {
        super( cause.getMessage() );
        super.initCause( cause );
    }

    public NestedIOException( String message, Throwable cause ) {
        super( message );
        initCause( cause );
    }
}

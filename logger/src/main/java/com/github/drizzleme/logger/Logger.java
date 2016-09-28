package com.github.drizzleme.logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with jmemcache
 *
 * @author ; DRIZZLEME
 *         DATE : 2016/9/26
 */
public class Logger {

    public enum LogLevel{
        DEBUG,
        INFO,
        WARN,
        ERROR,
        FATAL

    }

    private static Map<String,Logger> loggers =
            new HashMap<String,Logger>();

    private String name;
    private int level;
    private boolean initialized = false;

    public void setLevel( int level ) { this.level = level; }
    public int getLevel() { return this.level; }

    protected Logger( String name, int level ) {
        this.name  = name;
        this.level = level;
        this.initialized = true;
    }

    protected Logger( String name ) {
        this.name  = name;
        this.level = LogLevel.INFO.ordinal();
        this.initialized = true;
    }

    /**
     * Gets a Logger obj for given name and level.
     *
     * @param name
     * @param level
     * @return
     */
    public static synchronized Logger getLogger( String name, int level ) {
        Logger log = getLogger( name );
        if ( log.getLevel() != level )
            log.setLevel( level );

        return log;
    }

    /**
     * Gets a Logger obj for given name
     * and sets default level.
     *
     * @param name
     * @return
     */
    public static synchronized Logger getLogger( String name ) {

        Logger log = null;
        if ( loggers.containsKey( name ) ) {
            log = loggers.get( name );
        }
        else {
            log = new Logger( name );
            loggers.put( name, log );
        }

        return log;
    }

    /**
     * logs mesg to std out and prints stack trace if exception passed in
     *
     * @param mesg
     * @param ex
     */
    private void log( String mesg, Throwable ex ) {
        System.out.println( name + " " + new Date() + " - " + mesg );
        if ( ex != null )
            ex.printStackTrace( System.out );
    }

    /**
     * logs a debug mesg
     *
     * @param mesg
     * @param ex
     */
    public void debug( String mesg, Throwable ex ) {
        if ( this.level > LogLevel.DEBUG.ordinal() )
            return;

        log( mesg, ex );
    }

    public void debug( String mesg ) {
        debug( mesg, null );
    }

    public boolean isDebugEnabled() {
        return this.level <= LogLevel.DEBUG.ordinal();
    }

    /**
     * logs info mesg
     *
     * @param mesg
     * @param ex
     */
    public void info( String mesg, Throwable ex ) {
        if ( this.level > LogLevel.INFO.ordinal() )
            return;

        log( mesg, ex );
    }

    public void info( String mesg ) {
        info( mesg, null );
    }

    public boolean isInfoEnabled() {
        return this.level <= LogLevel.INFO.ordinal();
    }

    /**
     * logs warn mesg
     *
     * @param mesg
     * @param ex
     */
    public void warn( String mesg, Throwable ex ) {
        if ( this.level > LogLevel.WARN.ordinal() )
            return;

        log( mesg, ex );
    }

    public void warn( String mesg ) {
        warn( mesg, null );
    }

    /**
     * logs error mesg
     *
     * @param mesg
     * @param ex
     */
    public void error( String mesg, Throwable ex ) {
        if ( this.level > LogLevel.ERROR.ordinal() )
            return;

        log( mesg, ex );
    }

    public void error( String mesg ) {
        error( mesg, null );
    }

    /**
     * logs fatal mesg
     *
     * @param mesg
     * @param ex
     */
    public void fatal( String mesg, Throwable ex ) {
        if ( this.level > LogLevel.FATAL.ordinal() )
            return;

        log( mesg, ex );
    }

    public void fatal( String mesg ) {
        fatal( mesg, null );
    }
}

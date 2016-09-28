import com.github.drizzleme.logger.Logger;
import com.github.drizzleme.memcached.MemcachedClient;
import com.github.drizzleme.memcached.SockIOPool;

/**
 * Created with jmemcache
 *
 * @author ; DRIZZLEME
 *         DATE : 2016/9/26
 */
public class TestMemcached {
    public static void main(String[] args) {
        // memcached should be running on port 11211 but NOT on 11212

        //BasicConfigurator.configure();
        String[] servers = { "192.168.1.1:1624", "192.168.1.1:1625" };
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers( servers );
        pool.setFailover( true );
        pool.setInitConn( 10 );
        pool.setMinConn( 5 );
        pool.setMaxConn( 250 );
        pool.setMaintSleep( 30 );
        pool.setNagle( false );
        pool.setSocketTO( 3000 );
        pool.setAliveCheck( true );
        pool.initialize();

        MemcachedClient mcc = new MemcachedClient();

        // turn off most memcached client logging:
        Logger.getLogger( MemcachedClient.class.getName() ).setLevel( Logger.LogLevel.WARN.ordinal() );

        for ( int i = 0; i < 10; i++ ) {
            boolean success = mcc.set( "" + i, "Hello!" );
            String result = (String)mcc.get( "" + i );
            System.out.println( String.format( "set( %d ): %s", i, success ) );
            System.out.println( String.format( "get( %d ): %s", i, result ) );
        }

        System.out.println( "\n\t -- sleeping --\n" );
        try { Thread.sleep( 10000 ); } catch ( Exception ex ) { }

        for ( int i = 0; i < 10; i++ ) {
            boolean success = mcc.set( "" + i, "Hello!" );
            String result = (String)mcc.get( "" + i );
            System.out.println( String.format( "set( %d ): %s", i, success ) );
            System.out.println( String.format( "get( %d ): %s", i, result ) );
        }
    }
}

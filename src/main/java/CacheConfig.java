import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Arrays;

public class CacheConfig {
    public  static CacheConfiguration createCfg() {
        CacheConfiguration cfg = new CacheConfiguration();
        cfg.setCacheMode(CacheMode.PARTITIONED);
        cfg.setName("#CLIENT");
        return cfg;
    }
}

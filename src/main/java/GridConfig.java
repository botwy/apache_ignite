import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Arrays;

/**
 * создание конфигурации сервера и клиента
 */
public class GridConfig {
    /**
     *
     * @param nodeName
     * @param clientMode
     * @return если clientMode = false, то возвращает конфигурацию сервера (Ноды)
     */
    public  static IgniteConfiguration createCfg(String nodeName, boolean clientMode) {
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();

        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder tcpDiscoveryVmIpFinder = new TcpDiscoveryVmIpFinder();
        tcpDiscoveryVmIpFinder.setAddresses(Arrays.asList("127.0.0.1:47500..47509"));
        discoverySpi.setIpFinder(tcpDiscoveryVmIpFinder);
        igniteConfiguration.setDiscoverySpi(discoverySpi);
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setClientMode(clientMode);

        if(!clientMode)
        igniteConfiguration.setIgniteInstanceName(nodeName);

        return  igniteConfiguration;
    }
}

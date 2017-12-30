import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.internal.processors.cache.IgniteCacheProxy;

/**
 * Запускаем клиента и пишем данные в кеш. И затем читаем данные из кеша
 */
public class MainClientStart {
    public static void main(String... args) {
        try (Ignite ignClient = Ignition.start(GridConfig.createCfg("", true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());
            for (int i = 0; i < 5; i++) {
                cache.put(i, new Client("Victor", i + "111"));
            }

            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

/**
 * Выполняем распределенные вычисления на Нодах: affinityCall с замыканием
 * И читаем из кеша список записей
 */
public class MainClientStart6 {
    public static void main(String... args) {
        try (Ignite ignClient = Ignition.start(GridConfig.createCfg("", true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());

            IgniteCompute igniteCompute = ignClient.compute();
            for (int i = 0; i < 5; i++) {
                int t = i;
                Client res = igniteCompute.affinityCall("#CLIENT", t,
                        () -> {
                            Client c = (Client) cache.get(t);
                            System.out.println("call with t=" + t + " " + c);
                            return c;

                        }
                );
                System.out.println("IgniteClosure " + res);
            }


            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

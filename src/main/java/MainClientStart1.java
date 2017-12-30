import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Выполняем распределенные вычисления на Нодах: apply (замыкания)
 * И читаем из кеша список записей
 */
public class MainClientStart1 {
    public static void main(String... args) {
        try (Ignite ignClient = Ignition.start(GridConfig.createCfg("", true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());

            IgniteCompute igniteCompute = ignClient.compute();
            List<String> list = Arrays.asList("How many characters".split(" "));
            Collection<Integer> res = igniteCompute.apply(
                    String::length,
                    list

            );
            int total = res.stream().mapToInt(Integer::intValue).sum();
            System.out.println(total);
            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

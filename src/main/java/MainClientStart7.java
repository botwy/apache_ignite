import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

/**
 * Выполняем распределенные вычисления на Нодах: broadcast и run
 */
public class MainClientStart7 {
    public  static void main(String...args) {
       try(Ignite ignClient =Ignition.start(GridConfig.createCfg("",true))) {
           IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());

            IgniteCompute igniteCompute = ignClient.compute();
            igniteCompute.broadcast(()->System.out.println(cache.get(0)));

           for (String word:"Each word on node".split(" "))
               igniteCompute.run(()->System.out.println(word));


       }
    }
}

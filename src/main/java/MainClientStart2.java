import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

public class MainClientStart2 {
    public  static void main(String...args) {
        try(Ignite ignClient = Ignition.start(GridConfig.createCfg("",true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());
          /* for (int i = 0; i < 5; i++) {
               cache.put(i,new Client("Victor",i+"111"));
           }
*/
            IgniteCompute igniteCompute = ignClient.compute();

       String inn = igniteCompute.call(()->{
            System.out.println("request");
            return ((Client)cache.get(2)).getInn();
        }) ;
            System.out.println("inn "+inn);
            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

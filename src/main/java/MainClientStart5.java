import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

public class MainClientStart5 {
    public  static void main(String...args) {
        try(Ignite ignClient = Ignition.start(GridConfig.createCfg("",true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());
          /* for (int i = 0; i < 5; i++) {
               cache.put(i,new Client("Victor",i+"111"));
           }
*/
            IgniteCompute igniteCompute = ignClient.compute();
            for (int i = 0; i < 5 ; i++) {
                int t=i;
                Client res = igniteCompute.call(
                        ()->{
                            Client c =(Client)cache.get(t);
                            System.out.println("call with t="+t+" "+c);
                            return c;

                            }
                );
                System.out.println("IgniteClosure "+res);
            }


            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

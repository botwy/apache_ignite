import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MainClientStart4 {
    public  static void main(String...args) {
        try(Ignite ignClient = Ignition.start(GridConfig.createCfg("",true))) {
            IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());
          /* for (int i = 0; i < 5; i++) {
               cache.put(i,new Client("Victor",i+"111"));
           }
*/
            IgniteCompute igniteCompute = ignClient.compute();
            for (int i = 0; i < 5 ; i++) {
                Client res = igniteCompute.apply(
                        t->{
                            Client c =(Client)cache.get(t);
                            System.out.println("apply "+c);
                            return c;

                            },
                        i
                );
                System.out.println("IgniteClosure "+res);
            }


            for (int i = 0; i < 5; i++)
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


        }
    }
}

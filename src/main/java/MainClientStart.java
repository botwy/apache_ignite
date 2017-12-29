import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.Ignition;
import org.apache.ignite.internal.processors.cache.IgniteCacheProxy;

public class MainClientStart {
    public  static void main(String...args) {
       try(Ignite ignClient =Ignition.start(GridConfig.createCfg("",true))) {
           IgniteCache cache = ignClient.getOrCreateCache(CacheConfig.createCfg());
          /* for (int i = 0; i < 5; i++) {
               cache.put(i,new Client("Victor",i+"111"));
           }
*/
             IgniteCompute igniteCompute = ignClient.compute();
            igniteCompute.broadcast(()->System.out.println(cache.get(0)));
        //    System.out.println(ignClient.cluster().);

           for (String word:"Each word on node".split(" ")
                ) {
               igniteCompute.run(()->System.out.println(word));
              /* ignClient.compute(ignClient.cluster()
                       .forAttribute("name","node2")
               .forServers())
               .broadcast(()->System.out.println(cache.get(0)));*/
           }

            /* igniteCompute.broadcast(()->System.out.println("broadcast"));

           for (int i = 0; i <5 ; i++) {

               igniteCompute.affinityRun(Client.class.getName(), i, ()->System.out.println("privet"));

           }*/

           for (int i = 0; i < 5; i++)
               System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');


       }
    }
}

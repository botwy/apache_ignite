import org.apache.ignite.Ignition;

public class MainStartNode {

    public  static void main(String...args) {
        Ignition.start(GridConfig.createCfg("node1",false));
    }
}

import org.apache.ignite.Ignition;

public class MainStartNode2 {
    public  static void main(String...args) {
        Ignition.start(GridConfig.createCfg("node2",false));
    }
}


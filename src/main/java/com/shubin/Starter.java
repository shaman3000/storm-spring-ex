package com.shubin;

import com.shubin.example.GenerationTopologyBuilder;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by sshubin on 10.11.2016.
 */

public class Starter {


    public static void main(String[] args) throws Exception {

        TopologyBuilder stormBuilder = GenerationTopologyBuilder.getStormBuilder();
        // stormBuilder.addWorkerHook(SpringContextInitializer.getDefaultInitializer()); // <<-- Hook for Spring initialization

        /*
        stormBuilder = new TopologyBuilder();
        stormBuilder.setSpout(GenerationSpout.COMPONENT_ID, new BSpout());
        stormBuilder.setBolt(GenerationBolt.COMPONENT_ID, new BBolt()).shuffleGrouping(GenerationSpout.COMPONENT_ID);
        */
        StormTopology topology = stormBuilder.createTopology();

        // LocalCluster startup
        Config config = new Config();
        config.setDebug(false);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("storm-spring-err5", config, topology);

        System.in.read(); // await

        cluster.deactivate("storm-spring-err5");
        try {
            cluster.killTopology("storm-spring-err5");
            cluster.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

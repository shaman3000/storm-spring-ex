package com.shubin;

import com.shubin.example.GenerationTopologyBuilder;
import com.shubin.integration.SpringContextInitializer;
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
        stormBuilder.addWorkerHook(SpringContextInitializer.getDefaultInitializer()); // <<-- Hook for Spring initialization
        StormTopology topology = stormBuilder.createTopology();

        // LocalCluster startup
        Config config = new Config();
        config.put(SpringContextInitializer.SPRING_CONFIGURATION_FILE, "services.xml");
        config.setDebug(false);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("storm-spring-ex", config, topology);

        System.in.read(); // await

        cluster.killTopology("storm-spring-ex");
        cluster.shutdown();


    }


}

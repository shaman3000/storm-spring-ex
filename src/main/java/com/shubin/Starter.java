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
        StormTopology topology = stormBuilder.createTopology();

        // LocalCluster startup
        Config config = new Config();
        config.setDebug(false);
        config.setNumWorkers(1);

        String topologyName = "storm-spring-ex";
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topologyName, config, topology);
        // StormSubmitter.submitTopology(topologyName, config, topology);

        System.in.read(); // await
        cluster.killTopology(topologyName);
        cluster.shutdown();

    }


}

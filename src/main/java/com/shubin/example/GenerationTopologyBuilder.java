package com.shubin.example;

import com.shubin.integration.BasicBoltContext;
import com.shubin.integration.RichSpoutContext;
import org.apache.storm.topology.TopologyBuilder;

/**
 * Created by sshubin on 10.11.2016.
 */

public class GenerationTopologyBuilder {


    public static TopologyBuilder getStormBuilder() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout(GenerationSpout.COMPONENT_ID, new RichSpoutContext(new GenerationSpout()) );
        builder.setBolt(GenerationBolt.COMPONENT_ID, new BasicBoltContext(new GenerationBolt()) )
               .shuffleGrouping(GenerationSpout.COMPONENT_ID);
        return builder;
    }



}

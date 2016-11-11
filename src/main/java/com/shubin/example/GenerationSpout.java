package com.shubin.example;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class GenerationSpout extends BaseRichSpout {

    public static final String COMPONENT_ID = "generation-spout";

    private long lastGeneratedTime = System.currentTimeMillis();

    private SpoutOutputCollector collector;

    //@Autowired
    //ExpressionEvaluatorService evaluatorService;


    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        //System.out.println(evaluatorService);
    }

    @Override
    public void close() {

    }

    @Override
    public void nextTuple() {
        long current = System.currentTimeMillis();
        if (lastGeneratedTime < current - 5000) {
            lastGeneratedTime = current;
            collector.emit(new Values(String.valueOf(current)), "msgId");
        }
    }

    // design

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("tick"));
    }

}

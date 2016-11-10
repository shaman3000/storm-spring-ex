package com.shubin.example;

import com.shubin.services.ExpressionEvaluatorService;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class GenerationBolt extends BaseBasicBolt {

    public static final String COMPONENT_ID = "generation-bolt";

    @Autowired
    private ExpressionEvaluatorService expressionEvaluator;

    @PostConstruct
    protected void postConstruct() {
        System.out.println("PostConstruct annotated method fired...");
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String script = "print('Bolt execute: scripted tick [" + input.getStringByField("tick") + "]');";
        try {
            if (expressionEvaluator != null)
                expressionEvaluator.runScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

    // TODO: delegate all methods


}

package com.shubin.example;

import com.shubin.api.ProcessingService;
import com.shubin.services.ExpressionEvaluatorService;
import com.shubin.services.LockManager;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class GenerationSpout extends BaseRichSpout {

    public static final String COMPONENT_ID = "generation-spout";

    private long lastGeneratedTime = System.currentTimeMillis();

    private SpoutOutputCollector collector;

    @Autowired
    private ExpressionEvaluatorService evaluatorService;

    @Autowired
    private LockManager lockManager;

    private ProcessingService processingService;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        System.out.println(evaluatorService);
        lockManager.acquire(0, "KEY_ACQ");
    }

    @Override
    public void close() {

    }

    @Override
    public void nextTuple() {
        long current = System.currentTimeMillis();
        if (lastGeneratedTime < current - 5000) {
            lastGeneratedTime = current;
            String msgId = String.valueOf(current);
            collector.emit(new Values(msgId), msgId);
        }
    }

    // design

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("tick"));
    }

}

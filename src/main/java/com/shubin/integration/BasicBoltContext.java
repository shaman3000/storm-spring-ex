package com.shubin.integration;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;

import java.io.IOException;
import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class BasicBoltContext extends BaseBasicBolt {

    private SpringDelegatorHolder<BaseBasicBolt> delegate;

    public BasicBoltContext(BaseBasicBolt wrappingBolt) throws NullPointerException {
        if (wrappingBolt == null)
            throw new NullPointerException();
        this.delegate = new SpringDelegatorHolder<>(wrappingBolt, null);
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context) {
        System.out.println("-------------------------- PREPARE BOLT --------------------------");
        delegate.springifyComponent(stormConf, context, context.getThisComponentId());
        delegate.get().prepare(stormConf, context);
    }

    @Override
    public void cleanup() {
        System.out.println("-------------------------- CLEANUP BOLT --------------------------");
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        delegate.get().declareOutputFields(declarer);
    }

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        delegate.get().execute(input, collector);
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        System.out.println("-------------------------- WRITE BOLT --------------------------");
        stream.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        System.out.println("-------------------------- READ BOLT --------------------------");
        stream.defaultReadObject();
    }


}

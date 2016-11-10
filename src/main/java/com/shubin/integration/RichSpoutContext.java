package com.shubin.integration;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;

import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class RichSpoutContext extends BaseRichSpout {

    private SpringDelegatorHolder<BaseRichSpout> delegate;

    public RichSpoutContext(BaseRichSpout wrappingSpout) throws NullPointerException {
        if (wrappingSpout == null)
            throw new NullPointerException();
        delegate = new SpringDelegatorHolder<>(wrappingSpout, null);
    }

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        delegate.springifyComponent(context.getThisComponentId());
        delegate.get().open(conf, context, collector);
    }

    @Override
    public void nextTuple() {
        delegate.get().nextTuple();
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        delegate.get().declareOutputFields(declarer);
    }

    @Override
    public void close() {
        delegate.get().close();
    }

    // TODO: delegate all methods

}

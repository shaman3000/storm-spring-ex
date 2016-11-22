package com.shubin.example;

import com.shubin.api.LoggingSpec;
import com.shubin.model.MessageLog;
import com.shubin.services.ExpressionEvaluatorService;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by sshubin on 10.11.2016.
 */

public class GenerationBolt extends BaseBasicBolt {

    public static final String COMPONENT_ID = "generation-bolt";

    @Autowired
    private ExpressionEvaluatorService expressionEvaluator;

    @Autowired
    private SessionFactory sessionFactory;

    @PostConstruct
    protected void postConstruct() {
        System.out.println("PostConstruct annotated method fired...");
    }

    @Override
    public void prepare(Map stormConf, TopologyContext context) {

    }

    @Override
    @LoggingSpec
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(Tuple input, BasicOutputCollector collector) {
        LoggerFactory.getLogger(GenerationBolt.class).info("BOLT-EXECUTE");
        String script = "print('Bolt execute: scripted tick [" + input.getStringByField("tick") + "]');";
        try {
            if (expressionEvaluator != null)
                expressionEvaluator.runScript(script);

            // acquire current session
            Session session = sessionFactory.getCurrentSession();

            // write item
            MessageLog newLog = new MessageLog();
            newLog.setMessageIdentifier("MESSAGE_" + input.getStringByField("tick"));
            newLog.setStatus(0);
            session.save(newLog);

            // read it
            Long id = newLog.getLogId();
            MessageLog log = session.load(MessageLog.class, id);
            System.out.println(log);

        } catch (Exception e) {
            LoggerFactory.getLogger(GenerationBolt.class).error(e.getMessage(), e);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {

    }

}

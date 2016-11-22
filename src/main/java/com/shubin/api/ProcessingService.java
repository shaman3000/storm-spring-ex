package com.shubin.api;

/**
 * Created by sshubin on 21.11.2016.
 */
public interface ProcessingService {

    public interface ProcessingRuntime {

        public String getProcessingId();



    }

    // Lock + DB
    public ProcessingRuntime startMessageProcessing(String messageId) throws AlreadyProcessedException;

    public boolean finishProcessing(String messageId) throws AlreadyProcessedException;



    //

    public boolean start(String processingId);


    public void finalize(String processingId);


}

package com.shubin.api.polling;

/**
 * Created by sshubin on 14.11.2016.
 */

public class StreamOutput implements StreamOutputInterface {

    protected static ThreadLocal<StreamOutputInterface> outputThreadVar = new ThreadLocal<>();

    public static StreamOutputInterface getCurrent() {
        return outputThreadVar.get();
    }


}

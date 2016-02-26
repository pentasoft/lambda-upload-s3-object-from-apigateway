/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.processor;

import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.inject.Inject;


/**
 * @link MessageProcessor} implementation.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class MessageProcessorImpl implements MessageProcessor {

    private final Message message;
    private final Context context;
    
    @Inject
    public MessageProcessorImpl(final Message message, final Context context) {
        this.message = message;
        this.context = context;
    }

    public long process() {
        // TODO Auto-generated method stub
        return 1;
    }

}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.inject;

import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;
import io.pst.lambda.upload.s3.object.from.apigateway.processor.MessageProcessor;
import io.pst.lambda.upload.s3.object.from.apigateway.processor.MessageProcessorImpl;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Guice module containing common objects bindings.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class CommonModule extends AbstractModule {

    private final Message message;
    private final Context context;

    public CommonModule(final Message message, final Context context) {
        this.message = message;
        this.context = context;
    }
    
    @Override
    protected void configure() {
        bind(Message.class).toInstance(message);
        bind(Context.class).toInstance(context);
        bind(MessageProcessor.class).to(MessageProcessorImpl.class).in(Singleton.class);
    }

}

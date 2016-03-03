/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway;

import io.pst.lambda.upload.s3.object.from.apigateway.inject.CommonModule;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;
import io.pst.lambda.upload.s3.object.from.apigateway.processor.MessageProcessor;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * AWS Lambda handler processing messages entries.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class Handler implements RequestHandler<Message, String> {

    private Module[] buildModulesArray(final Message message, final Context context) {
        final List<Module> modules = new ArrayList<Module>(1);
        modules.add(new CommonModule(message, context));
        return modules.toArray(new Module[modules.size()]);
    }
    
    public String handleRequest(Message message, Context context) {
        final Injector injector = Guice.createInjector(buildModulesArray(message, context));
        final MessageProcessor messageProcessor = injector.getInstance(MessageProcessor.class);
        final boolean processed = messageProcessor.process();
        
        return processed ? "Successfully processed " + String.format("%s message", message.getName()) : "No messages to process";
    }

}

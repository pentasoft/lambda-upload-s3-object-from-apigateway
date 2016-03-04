/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.processor;

import io.pst.lambda.upload.s3.object.from.apigateway.cloud.CloudObjectService;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;
import io.pst.lambda.upload.s3.object.from.apigateway.util.FileNameGenerator;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.inject.Inject;
import com.google.inject.name.Named;


/**
 * @link MessageProcessor} implementation.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class MessageProcessorImpl implements MessageProcessor {

    private final Message message;
    private final Context context;
    private final String bucket;
    private final CloudObjectService cloudObjectService;
    
    @Inject
    public MessageProcessorImpl(final Message message, 
            final Context context, 
            final CloudObjectService cloudObjectService, 
            @Named("respository") final String bucket) {
        this.message = message;
        this.context = context;
        this.bucket = bucket;
        this.cloudObjectService = cloudObjectService;
    }

    public boolean process() {        
        String fileName = FileNameGenerator.newName(); 
       System.out.println("The filename will be [" + fileName + "]");
        return cloudObjectService.putObject(this.bucket, fileName, message);
    }

}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.inject;

import io.pst.lambda.upload.s3.object.from.apigateway.cloud.CloudObjectService;
import io.pst.lambda.upload.s3.object.from.apigateway.cloud.S3CloudObjectService;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;
import io.pst.lambda.upload.s3.object.from.apigateway.processor.MessageProcessor;
import io.pst.lambda.upload.s3.object.from.apigateway.processor.MessageProcessorImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

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

        Properties properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");            
            properties.load(inputStream);
            Names.bindProperties(binder(), properties);
            
            bind(Message.class).toInstance(message);
            bind(Context.class).toInstance(context);
            bind(MessageProcessor.class).to(MessageProcessorImpl.class).in(Singleton.class);
            bind(CloudObjectService.class).to(S3CloudObjectService.class).in(Singleton.class);
            
        } catch (FileNotFoundException e) {
            System.out.println("The configuration file config.properties can not be found");
        } catch (IOException e) {
            System.out.println("I/O Exception during loading configuration");
        }
    }

    @Provides
    @Singleton
    AmazonS3 provideAmazonS3() {
        final AmazonS3 s3 = new AmazonS3Client();
        return s3;
    }
    
    @Provides
    @Singleton
    ObjectMapper provideObjectMapper() {
        return new ObjectMapper();
    }
}

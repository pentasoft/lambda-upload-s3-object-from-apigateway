/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.processor;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import io.pst.lambda.upload.s3.object.from.apigateway.cloud.CloudObjectService;
import io.pst.lambda.upload.s3.object.from.apigateway.cloud.S3CloudObjectService;
import io.pst.lambda.upload.s3.object.from.apigateway.exception.CustomException;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 *  {@link MessageProcessorImpl} unit tests.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class MessageProcessorImplTest {
    
    private MessageProcessorImpl messageProcessor;
    private Message message;
    private Context context;
    private CloudObjectService cloudObjectService;
    private String bucket;

    
    @Before
    public void setUp() {
        bucket = "test-bucket";
        message = mock(Message.class);
        context = mock(Context.class);
        cloudObjectService = mock(S3CloudObjectService.class);
    }
    
    
    @Test(expected = CustomException.class)
    public void test_Process_WhenInvoce_ReturnCustomException(){
        when(cloudObjectService.putObject(anyString(), anyString(), any(Message.class))).thenThrow(new CustomException());
        
        messageProcessor = new MessageProcessorImpl(message, context, cloudObjectService, bucket);
        messageProcessor.process();
    }
    
    @Test
    public void test_Process_WhenProcessWorks_ReturnTrue(){
        when(cloudObjectService.putObject(anyString(), anyString(), any(Message.class))).thenReturn(true);
        
        messageProcessor = new MessageProcessorImpl(message, context, cloudObjectService, bucket);
        assertTrue(messageProcessor.process());
    }
}

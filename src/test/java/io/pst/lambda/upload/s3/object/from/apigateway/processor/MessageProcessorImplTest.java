/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.processor;

import static org.mockito.Mockito.mock;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import org.junit.Before;

import com.amazonaws.services.lambda.runtime.Context;

/**
 *  {@link MessageProcessorImpl} unit tests.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class MessageProcessorImplTest {
    
    private MessageProcessorImpl messageProcessor;
    private Context context;
    private Message message;
    
    @Before
    public void setUp() {
        context = mock(Context.class);
        
        message = new Message();
        message.setId("1");
        message.setName("testMessageName");
    }

//    @Test
//    public void testProcessWhenReceiveMessageReturnOne(){
//        messageProcessor = new MessageProcessorImpl(message, context);
//        
//        boolean result = messageProcessor.process();
//        boolean expected = 1;
//        
//        assertTrue("Result is different than 1",result==expected);
//    }
}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * @author Borja Lopez Altarriba
 *
 */
public class HandlerIntegrationTest {

    private Handler handler;
    private Context context;
    private Message message;
    
    @Before
    public void setUp() {
        context = mock(Context.class);
        
        message = new Message();
        message.setId("1");
        message.setName("testMessageName");  
    }
    
    @Test
    public void testHanlderWhenReceiveMessageReturnOkMessage(){
        handler = new Handler();
        String result = handler.handleRequest(message, context);
        
        String expected = "Successfully processed testMessageName message";
        
        assertTrue("Result [" + result + "] is not OK message",result.equals(expected));
    }
}

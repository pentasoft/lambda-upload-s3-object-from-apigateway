/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway;

import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * @author Borja Lopez Altarriba
 *
 */
public class HandlerIntegrationTest {

    private Handler handler;
    private Context context;
    private Message message;
        
//    @Test
//     Only runs with mvn test and environment variables (ACCESS_KEY and SECRET_KEY) filled
//    public void testHanlderWhenReceiveMessageReturnOkMessage(){
//        context = mock(Context.class);
//        
//        message = new Message();
//        message.setId("1");
//        message.setName("testMessageName"); 
//        
//        handler = new Handler();
//        String result = handler.handleRequest(message, context);
//        String expected = "Successfully processed testMessageName message";
//        
//        assertTrue("Result [" + result + "] is not OK message",result.equals(expected));
//    }
}

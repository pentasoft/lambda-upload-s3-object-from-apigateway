/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway;

import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * AWS Lambda handler processing messages entries.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class Handler implements RequestHandler<Message, String> {

    public String handleRequest(Message message, Context context) {
        // TODO Auto-generated method stub
        return null;
    }

}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.processor;

/**
 * Messages processor.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public interface MessageProcessor {
    boolean process();
}

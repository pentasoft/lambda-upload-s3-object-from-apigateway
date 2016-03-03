/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.cloud;

import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

/**
 * Service for cloud objects interaction.
 *
 * @author Borja Lopez Altarriba
 */
public interface CloudObjectService {
    boolean putObject(String bucket, String key, Message message);
}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.cloud;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import io.pst.lambda.upload.s3.object.from.apigateway.exception.CustomException;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import org.junit.Before;
import org.junit.Test;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link S3CloudObjectService} unit tests.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class S3CloudObjectServiceTest {

    private CloudObjectService cloudObjectService;
    private AmazonS3 s3;
    private ObjectMapper mapper;
    private Message message;
        
    private void configureS3MockingBehaviourWithAmazonError(final String bucket, final String key) {
        when(s3.putObject(any(PutObjectRequest.class))).thenThrow(new AmazonClientException("Amazon exception"));
    }
    
    @Before
    public void setUp() {
        s3 = mock(AmazonS3.class);
        mapper = new ObjectMapper();
        cloudObjectService = new S3CloudObjectService(s3, mapper);
        
        message = new Message();
        message.setId("id-test");
        message.setName("name-test");
    }
    
    @Test(expected = CustomException.class)
    public void testsPutObjectAmazonErrorThrowsException() {
        final String bucket = "draft-bucket";
        final String key = "draft-file.json";
        configureS3MockingBehaviourWithAmazonError(bucket, key);

        cloudObjectService.putObject(bucket, key, message);
    }
}

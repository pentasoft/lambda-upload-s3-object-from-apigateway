/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.cloud;

import static org.junit.Assert.assertTrue;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * {@link S3CloudObjectService} unit tests.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class S3CloudObjectServiceTest {
    
    private class CustomJsonProcesingException extends JsonProcessingException {

        private static final long serialVersionUID = 1L;

        CustomJsonProcesingException(String msg) {
            super(msg);
        }
    }
    
    private CloudObjectService cloudObjectService;
    private AmazonS3 s3;
    private ObjectMapper mapper;
    private Message message;
            
    private void configureMapperMockingBehaviourWithJsonProcessingError(Message message) throws JsonProcessingException {
//        try {
            when(mapper.writeValueAsBytes(message)).thenThrow(new CustomJsonProcesingException("Serialize exception"));
//        } catch (JsonProcessingException e) {
//            System.out.println("Exception configuring mapper behavior");
//        }
    }
    
    private void configureS3MockingBehaviourWithAmazonError() {
        when(s3.putObject(any(PutObjectRequest.class))).thenThrow(new AmazonClientException("Amazon exception"));
    }
    
    @Before
    public void setUp() {
        s3 = mock(AmazonS3.class);
        
        
        message = new Message();
        message.setId("id-test");
        message.setName("name-test");
    }
    
    @Test
    public void test_PutObject_WhenMessageIsNull_ReturnTrue(){
        final String bucket = "draft-bucket";
        final String key = "draft-file.json";
        
        mapper = new ObjectMapper();
        cloudObjectService = new S3CloudObjectService(s3, mapper);
        message = null;
        
        boolean result = cloudObjectService.putObject(bucket, key, message);
        
        assertTrue("Everything works.", result);
    }
    
    @Test(expected = CustomException.class)
    public void test_PutObject_WhenMockingAmazonErrorException_ReturnAmazonErrorThrowsException() {
        final String bucket = "draft-bucket";
        final String key = "draft-file.json";
        
        mapper = new ObjectMapper();
        cloudObjectService = new S3CloudObjectService(s3, mapper);
        configureS3MockingBehaviourWithAmazonError();

        cloudObjectService.putObject(bucket, key, message);
    }
    
    @Test
    public void test_PutObject_WhenSerializationFails_ReturnJsonProcesingException(){
        final String bucket = "draft-bucket";
        final String key = "draft-file.json";

        try{
            mapper = mock(ObjectMapper.class);
            when(mapper.writeValueAsBytes(message)).thenThrow(new CustomJsonProcesingException("Serialize exception"));
            cloudObjectService = new S3CloudObjectService(s3, mapper);
            
            cloudObjectService.putObject(bucket, key, message);
        }
        catch (CustomException e) {
            if (e.getStackTrace().getClass().getName().equals("JsonProcessingException")){
                assertTrue(true);
            }
        } 
        catch (JsonProcessingException e) {
            System.out.println("Error during mocking mapper");
            assertTrue(false);
        }
    }
}

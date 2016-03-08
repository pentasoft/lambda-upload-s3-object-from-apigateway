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
    
    private class CustomJsonProcessingException extends JsonProcessingException {

        private static final long serialVersionUID = 1L;

        CustomJsonProcessingException(String msg) {
            super(msg);
        }
    }
    
    private String bucket;
    private String key;
    private CloudObjectService cloudObjectService;
    private AmazonS3 s3;
    private ObjectMapper mapper;
    private Message message;
    
    @Before
    public void setUp() {
        bucket = "draft-bucket";
        key = "draft-file.json";
        
        s3 = mock(AmazonS3.class);
        
        message = new Message();
        message.setId("id-test");
        message.setName("name-test");
    }
    
    @Test
    public void test_PutObject_WhenMessageIsNull_ReturnTrue(){
        mapper = new ObjectMapper();
        cloudObjectService = new S3CloudObjectService(s3, mapper);
        message = null;
        
        boolean result = cloudObjectService.putObject(bucket, key, message);
        
        assertTrue("Everything works.", result);
    }
    
    @Test
    public void test_PutObject_WhenMockingAmazonErrorException_ReturnAmazonErrorThrowsException() {
        boolean testResult = false;
        mapper = new ObjectMapper();
        cloudObjectService = new S3CloudObjectService(s3, mapper);
        when(s3.putObject(any(PutObjectRequest.class))).thenThrow(new AmazonClientException("Amazon exception"));

        try{
            cloudObjectService.putObject(bucket, key, message);
        }
        catch(CustomException e){
            if (e.getCause().getClass().getName().equals(AmazonClientException.class.getName())){ 
                testResult = true;
            }
        }
        assertTrue("Expected exception not thrown",testResult);        
    }
    
    @Test
    public void test_PutObject_WhenSerializationFails_ReturnJsonProcesingException(){
        boolean testResult = false;
        try{
            mapper = mock(ObjectMapper.class);
            when(mapper.writeValueAsBytes(message)).thenThrow(new CustomJsonProcessingException("Serialize exception"));
            cloudObjectService = new S3CloudObjectService(s3, mapper);
            
            cloudObjectService.putObject(bucket, key, message);
        }
        catch (CustomException e) {
            if (e.getCause().getClass().getName().equals(CustomJsonProcessingException.class.getName())){                
                testResult = true;
            }
        } 
        catch (JsonProcessingException e) {
        }
        assertTrue("Expected exception not thrown",testResult);
    }
}

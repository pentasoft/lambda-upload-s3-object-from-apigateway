/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.cloud;

import io.pst.lambda.upload.s3.object.from.apigateway.exception.CustomException;
import io.pst.lambda.upload.s3.object.from.apigateway.model.Message;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

/**
 * {@link CloudObjectService} implementation over AWS S3.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public class S3CloudObjectService implements CloudObjectService {

    private final AmazonS3 s3;
    private final ObjectMapper mapper;
    
    @Inject
    public S3CloudObjectService(final AmazonS3 s3, final ObjectMapper mapper) {
        this.s3 = s3;
        this.mapper = mapper;
    }

    private ObjectMetadata generateMetadaObject(byte[] bytesArray){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytesArray.length);
        return metadata;
    }
    
    public boolean putObject(String bucket, String key, Message message) {
      
      try{   
          byte[] messageBuffer = serializeObjectToBytes(message);
          InputStream inputStream = new ByteArrayInputStream(messageBuffer);
          ObjectMetadata metadata = generateMetadaObject(messageBuffer);
          
          PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, inputStream, metadata);
          s3.putObject(putObjectRequest);
          return true;
      }
      catch(final AmazonServiceException e){
          throw new CustomException("Exception thrown uploading object with key " + key + " to S3 bucket " + bucket, e);
      }
      catch(final AmazonClientException e){
          throw new CustomException("Exception thrown uploading object with key " + key + " to S3 bucket " + bucket, e);
      }
      catch(final JsonProcessingException e){
          throw new CustomException("Exception thrown serializing message object to bytes", e);
      }
      catch(final Exception e){
          throw new CustomException("Exception thrown uploading object with key " + key + " to S3 bucket " + bucket, e);
      }
    }
    
    private byte[] serializeObjectToBytes (Object object) throws JsonProcessingException{
        byte[] messageBuffer = mapper.writeValueAsBytes(object);
        return messageBuffer;
    }
}

/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * {@link FileNameGenerator} units test.
 * 
 * @author Borja Lopez Altarriba
 */
public class FileNameGeneratorTest {
    
    @Test
    public void test_GenerateNewNameFromDate_WhenExecute_ReturnFileNameWhichMatchesNamePattern(){
        DateTime date = new DateTime(2015, 8, 13,10,30,45);
        String fileName = FileNameGenerator.generateNewNameFromDate(date);
     
        assertTrue("File name " + fileName + " doesn´t matched with name pattern",fileName.matches("(\\d{17})_(\\d{8}).json"));
    }
    
    @Test
    public void test_GenerateNewNameFromDate_WhenExecute_ReturnFileNameWhichStartsWithDate(){
        DateTime date = new DateTime(2015, 8, 13,10,30,45);
        String fileName = FileNameGenerator.generateNewNameFromDate(date);
     
        assertEquals("File name doesn´t start with correct date", "20150813103045",  fileName.substring(0, 14));
    }
    
    @Test
    public void test_NewName_WhenExecute_ReturnFileNameWithLength31(){
        assertEquals("Length is different than 31", 31, FileNameGenerator.newName().length());
    }
}


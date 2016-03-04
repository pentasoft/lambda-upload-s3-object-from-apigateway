/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.upload.s3.object.from.apigateway.util;

import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Class to generate file names.
 * 
 * @author Borja Lopez Altarriba
 *
 */
public final class FileNameGenerator {

    static String generateNewNameFromDate(DateTime date){    
        String fileName = null;
        String dateFormatted = date.toString("yyyyMMddHHmmssSSS");
        fileName = dateFormatted + "_" + String.format("%08d", (int)(random.nextDouble()*99999999)) + ".json";
        return fileName;
    }
    
    public static String newName(){
        DateTime date = new DateTime(DateTimeZone.UTC);
      
        return generateNewNameFromDate(date);
    }
         
    private static Random random = new Random();
}

package com.example.courier_tracking.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class JsonUtil {

    public static <T> T json2Java(String fileName, Class<T> classType){

        T t = null;
        File file = new File("src/main/resources/"+fileName);

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            t=mapper.readValue(file, classType);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
}

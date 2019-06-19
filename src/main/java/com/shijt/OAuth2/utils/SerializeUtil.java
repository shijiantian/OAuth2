package com.shijt.OAuth2.utils;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

    public static byte[] serialize(Object object){
        byte[] result=null;
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(object);
            result=bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object deserialize(byte[] bytes){
        Object result=null;
        ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois=new ObjectInputStream(bis);
            result=ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}

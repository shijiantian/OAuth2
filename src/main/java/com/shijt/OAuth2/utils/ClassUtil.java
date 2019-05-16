package com.shijt.OAuth2.utils;

import java.io.*;

public class ClassUtil {
    public static boolean CanonicalNameIsTheSame(Object a,Class<?> b){
        String nameOfA=a.getClass().getCanonicalName();
        String nameOfB=b.getCanonicalName();
        return nameOfA.equals(nameOfB);
    }

    public static <T> T serializableObjectCast(Object object){
        Object result=null;
        try {
            final PipedOutputStream pipedOutputStream=new PipedOutputStream();
            final PipedInputStream  pipedInputStream =new PipedInputStream(pipedOutputStream);
            final ObjectOutputStream objectOutputStream=new ObjectOutputStream(pipedOutputStream);
            final ObjectInputStream  objectInputStream =new ObjectInputStream(pipedInputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            result=objectInputStream.readObject();
        }catch (IOException |ClassNotFoundException e ){
            e.printStackTrace();
        }
        return (T) result;
    }

}

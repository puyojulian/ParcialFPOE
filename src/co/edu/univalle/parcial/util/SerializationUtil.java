/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.univalle.parcial.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @author Julian Puyo
 */
public class SerializationUtil {
    
  public static void serializeObject(Object obj, String filePath) {
    try {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(obj);
        objectOut.close();
        fileOut.close();
//        System.out.println("Object serialized successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
  } 

  public static Object deserializeObject(String filePath) {
    try {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object obj = objectIn.readObject();
        objectIn.close();
        fileIn.close();
//        System.out.println("Object deserialized successfully.");
        return obj;
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
  }
  
  public static boolean isSerializedObjectExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
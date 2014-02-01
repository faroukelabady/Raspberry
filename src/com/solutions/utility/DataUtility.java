/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.utility;

import java.io.File;
import java.io.FileInputStream;
import java.security.CodeSource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farouk
 */
public class DataUtility {

    static Properties properties;

    public static void intializeProperties() {

        properties = new Properties();

        try {

            FileInputStream file;
            CodeSource codeSource = DataUtility.class.getProtectionDomain().getCodeSource();
            File jarDir = new File(codeSource.getLocation().toURI().getPath());
            String path = jarDir + "/Config.properties";

            file = new FileInputStream(path);
            properties.load(file);
            
            file.close();

        } catch (Exception ex) {
            Logger.getLogger(DataUtility.class.getName()).log(Level.SEVERE, "Can't find property File");
            System.out.println("Can't find property File");
        }
    }
    
    public static String getPropertyValue(String propertyName) {    
        return properties.getProperty(propertyName);
    }
}

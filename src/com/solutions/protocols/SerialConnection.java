/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.protocols;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.solutions.utility.DataUtility;
import com.solutions.utility.Utilities;
import java.io.IOException;

/**
 *
 * @author farouk
 */
public class SerialConnection implements ConnectionProtocol {

    final Serial serial = null;

    public SerialConnection(String comPort, int baudrate) {
//        serial = SerialFactory.createInstance();
//        serial.open(comPort, baudrate);
    }

    @Override
    public boolean closeConnection() {
        serial.close();
        return true;
    }

    @Override
    public boolean sendData(String data) {
        try {
            if (!Utilities.isSeek(data)) {
                DataUtility.intializeProperties();
                DataUtility.getPropertyValue(data);
//            Utilities.hexStringToByteArray(DataUtility.getPropertyValue(data).substring(2));
                // write a formatted string to the serial transmit buffer
                System.out.println("Before Send" + Utilities.hexStringToByteArray(DataUtility.getPropertyValue(data).substring(2)));
                serial.write(Utilities.hexStringToByteArray(DataUtility.getPropertyValue(data).substring(2)));
            } else {
                
                String[] seekData = data.split("-");
                
                String devNum = seekData[0].substring(4);
                
                byte dev = Byte.valueOf(devNum);
                
                byte byteData = (byte) ((0x80 | (dev << 5)) | (Byte.valueOf(seekData[1]))); 
                
                System.out.println(Integer.toBinaryString(byteData));
                
                serial.write(byteData);
            }

        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void recieveData() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getLocalPort() {

        return 0;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.protocols;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import com.solutions.utility.DataUtility;
import com.solutions.utility.Utilities;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author farouk
 */
public class SerialConnection implements ConnectionProtocol {

    final Serial serial;
    Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SerialConnection(String comPort, int baudrate, ServerSocket tcpSocket) {
        serial = SerialFactory.createInstance();
        // create and register the serial data listener
        serial.addListener(new SerialDataListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                // print out the data received to the console
                System.out.println("recieved from uC data: ");
                if (socket != null) {
                    try {
                        System.out.println("remote IP" + socket.getRemoteSocketAddress().toString().split(":")[0]);
                        Socket client = new Socket(socket.getRemoteSocketAddress().toString().split(":")[0], 5001);
                        
                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                        pw.printf(event.getData());
                        pw.flush();
                        pw.close();
                        client.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SerialConnection.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                }

            }
        });
        serial.open(comPort, baudrate);
    }

    @Override
    public boolean closeConnection() {
        serial.close();
        return true;
    }

    @Override
    public boolean sendData(String data) {
        try {
//            if(serial.isClosed()) {
//                serial.open(Serial.DEFAULT_COM_PORT, 19200);
//            }

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

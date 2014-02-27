/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.protocols;

import java.io.*;
import java.net.*;
import com.pi4j.io.serial.Serial;
import com.solutions.utility.DataUtility;
import com.solutions.utility.Utilities;

/**
 *
 * @author farouk TCPConnection implements ConnectionPRoctocol interface it
 * provides the basic functionality to send and receive data through using TCP
 * protocol
 */
public class TCPConnection implements ConnectionProtocol {

    // private parameters
    private Socket TCPSocket; // client connection
    private ServerSocket TCPServerSocket; // server connection
    private PrintWriter pw;
    String line1 = "";
    ConnectionProtocol serial;
    static boolean[] syncData = null;
    // end of parameters

    public TCPConnection(String hostname, int port) throws IOException {
        //try {

        // Get a Socket for TCPConnection
//        TCPSocket = new Socket(hostname, port);
        DataUtility.intializeProperties();
        TCPServerSocket = new ServerSocket(port);
        serial = new SerialConnection(Serial.DEFAULT_COM_PORT, Integer.valueOf(DataUtility.getPropertyValue("baudrate")), TCPServerSocket);
        // create printwriter object and assign the socket output
        // stream to it using outputstreamwriter class
//        pw = new PrintWriter(new OutputStreamWriter(TCPSocket.getOutputStream()));
        System.out.println("Connection Open");
        //}
    }

    // send data to the server the data more likely will be bytes
    // it returns an integer for
    public boolean sendData(String data) {

        // send the data to the server
//        pw.print(format);
        pw.print(data);
        pw.flush();
        //pw.close();
        // System.out.println("data send");
        return true;

    }

    public boolean closeConnection() {
        try {
            pw.close();
            TCPSocket.close();
            TCPServerSocket.close();
            return true;
        } catch (IOException ex) {
            //Logger.getLogger(TCPConnection.class.getName()).log(Level.SEVERE,
            //null, ex);
            return false;
        } catch (NullPointerException npe) {
            return false;
        }
        //return true;
    }

    public void recieveData() throws IOException {
        //throw new UnsupportedOperationException("Not supported yet.");
        String line = "";
        for (;;) {
            System.out.println("begin data receiving waiting for input.......");

            try {
                // get the next tcp client
                Socket client = TCPServerSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Data Received");
                    if (client.isClosed()) {
                        continue;
                    }
                    line = reader.readLine();
                    System.out.println("process Data");
                    // Check for end of data
                    if (line == null) {
                        break;
                    } else if (line.equalsIgnoreCase("sync")) {

                        InetAddress serverAddr = InetAddress.getByName(client.getInetAddress().getHostAddress());
                        Socket socket = new Socket(serverAddr, 7650);
//                        client.connect(socket.getRemoteSocketAddress());
                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//                        byte b = (byte) 0b00000000;
//                        boolean[] syncData = Utilities.booleanArrayFromByte(b);

                        String data = "";
                        if (syncData != null && syncData.length > 0) {
                            for (int i = 0; i < TCPConnection.syncData.length; i++) {
                                data += (i + 1) + "=" + TCPConnection.syncData[i] + "&";
                            }
                        }

                        pw.println(data);
                        pw.flush();
                        pw.close();
                    } else {
                        System.out.println("Send to serial port");
                        System.out.println(line);
                        ((SerialConnection) serial).setSocket(client);
                        line1 = line;
//                        char[] data = line1.toCharArray();
                        serial.sendData(line1);

//                        serial.closeConnection();
                    }
                }
                reader.close();

//                client.close();
//                client = null;
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int getLocalPort() {
        if (TCPServerSocket != null) {
            return TCPServerSocket.getLocalPort();
        }
        return 0;
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.Test;

import com.solutions.protocols.ConnectionProtocol;
import com.solutions.protocols.TCPConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 *
 * @author farouk
 */
public class ClientTest {

    public static void main(String[] args) throws IOException {

        // TODO code application logic here
//        String ip = null;
//        int port;
//        String protocol = null;
//        String data = "";
//        ConnectionProtocol conn = null;
//        
//         byte dev = Byte.valueOf("01");
//                
//                byte h = (byte) (dev << 5);
//                
//                System.out.println(Integer.toBinaryString(dev));
//                System.out.println(Integer.toBinaryString(h));
//                System.out.println(Integer.toBinaryString(0x80));
//                
//                int t = 0x0080 | h;
//                byte g = Byte.valueOf((byte)(t & 0xFF));
//                
//                ByteBuffer b =  ByteBuffer.allocate(4);
//                b.putInt(t);
//                
//                System.out.println(b.array()[0]);
//                System.out.println(Byte.toString(g));
//        
//        Scanner input = new Scanner(System.in);
//        System.out.println("please provide the following information.");
//        System.out.println("Communication Protocol(TCP|UDP):");
//        protocol =  input.nextLine();
//        System.out.println("IP Address:");
//        ip = input.nextLine();
//        System.out.println("Port:");
//        port = input.nextInt();
//        if("tcp".equalsIgnoreCase(protocol)){
//            System.out.println("connecting through TCP IP protocols");
//            conn = new TCPConnection("192.168.1.115", 5000);
//            System.out.println("Insert Data:");
//            
////            data = input.nextLine();
//                conn.sendData("TestHere\n");
//        } else {
//            System.out.println("not supported protocols");
//        }
//        System.out.println("your Ip:" + ip + ":Port" + port);
        try {

            InetAddress serverAddr = InetAddress.getByName("192.168.1.149");

            Socket socket = new Socket(serverAddr, 7650);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.print("Test");
            pw.flush();
            pw.close();
            socket.close();

        } catch (UnknownHostException e1) {

            e1.printStackTrace();

        } catch (IOException e1) {

            e1.printStackTrace();

        }
    }
}

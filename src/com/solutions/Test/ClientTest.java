/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.Test;

import com.solutions.protocols.ConnectionProtocol;
import com.solutions.protocols.TCPConnection;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author farouk
 */
public class ClientTest {
    
    public  static  void main(String[] args) throws IOException {
        
                // TODO code application logic here
        String ip = null;
        int port;
        String protocol = null;
        String data = "";
        ConnectionProtocol conn = null;
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
            System.out.println("connecting through TCP IP protocols");
            conn = new TCPConnection("192.168.1.105", 5000);
            System.out.println("Insert Data:");
            
//            data = input.nextLine();
                conn.sendData("TestHere\n");
            
//        } else {
//            System.out.println("not supported protocols");
//        }
        
//        System.out.println("your Ip:" + ip + ":Port" + port);
    }
}

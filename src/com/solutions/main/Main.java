/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solutions.main;

import com.solutions.protocols.ConnectionProtocol;
import com.solutions.protocols.TCPConnection;
import com.solutions.utility.Utilities;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author farouk
 */
public class Main {

    public final static String REMOTE_TYPE = "_7elol-remote._tcp.local.";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        boolean log = false;
        if (log) {
            ConsoleHandler handler = new ConsoleHandler();
            handler.setLevel(Level.FINEST);
            for (Enumeration<String> enumerator = LogManager.getLogManager().getLoggerNames(); enumerator.hasMoreElements();) {
                String loggerName = enumerator.nextElement();
                Logger logger = Logger.getLogger(loggerName);
                logger.addHandler(handler);
                logger.setLevel(Level.FINEST);
            }
        }
        // TODO code application logic here
        String ip = null;
        int port;
        String protocol = null;
        ConnectionProtocol conn = null;

        Scanner input = new Scanner(System.in);
        System.out.println("please provide the following information.");
        System.out.println("Communication Protocol(TCP|UDP):");
        protocol = input.nextLine();
        System.out.println("IP Address:");
        ip = input.nextLine();
        System.out.println("Port:");
        port = input.nextInt();

        // jmdns service creation and registeration
        System.out.println("Opening JmDNS...");

        if ("tcp".equalsIgnoreCase(protocol)) {
            System.out.println("connecting through TCP IP protocols");
            conn = new TCPConnection(ip, port);

            conn.recieveData();
        } else {
            System.out.println("not supported protocols");
        }
        

//        System.out.println("your Ip:" + ip + ":Port" + port);
    }
}

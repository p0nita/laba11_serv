package com.suai;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket(6788);
        InetAddress IPAddress = InetAddress.getByName("localhost");
        Thread ww = new Thread(new WorkerWriter(clientSocket, IPAddress, 6789));
        Thread wr = new Thread(new WorkerReader(clientSocket));
        ww.start();
        wr.start();
    }
}
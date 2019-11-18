package com.suai;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class WorkerReader implements Runnable{

    private DatagramSocket serverSocket;
    private byte[] receiveData = new byte[1024];

    public WorkerReader(DatagramSocket ss){
        serverSocket = ss;
    }

    public void run(){
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        int i = 0;
        while (true) {
            try {
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println(sentence);
                while (receiveData[i] != 0)
                    receiveData[i++] = 0;
                i = 0;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

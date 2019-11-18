package com.suai;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class WorkerWriter implements Runnable {

    private BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    private DatagramSocket serverSocket;
    private InetAddress IPAddress;
    private int port;
    private String str;
    private String name = "Anonimous";
    private byte[] sendData = new byte[1024];

    public WorkerWriter(DatagramSocket ss, InetAddress ia, int p){
        serverSocket = ss;
        IPAddress = ia;
        port = p;
    }

    public void run(){
        while (true) {
            try {
                str = inFromUser.readLine();
                if (str.startsWith("@name")){
                    name = str.substring(6);
                    continue;
                }
                if (str.equals("@quit")){
                    System.exit(0);
                }
                if (str.equals("@game")){
                    startGame();
                    continue;
                }
                str = name + ": " + str;
                sendData = str.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void startGame(){
        try {
            System.out.println("Input right border: ");
            int right = Integer.parseInt(inFromUser.readLine());
            Random rand = new Random();
            int count = rand.nextInt(right);
            System.out.println("Okay, your number is guessed! Start guessing!");
            while(true){
                try {
                    int number = Integer.parseInt(inFromUser.readLine());
                    if (number == count){
                        System.out.println("Yes! Congratulations!");
                     //   System.exit(0);
                        break;
                    }
                    if(number < count){
                        System.out.println("Wrong! The hidden number is greater than the entered one!");
                        continue;
                    }
                    if(number > count){
                        System.out.println("Wrong! The hidden number is less than the entered one!");
                        continue;
                    }
                    if (str.equals("@quit")){
                        System.exit(0);
                    }
                    str = name + ": " + str;
                    sendData = str.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                    serverSocket.send(sendPacket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
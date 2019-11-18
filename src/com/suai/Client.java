
package com.suai;

        import java.net.DatagramSocket;
        import java.net.InetAddress;

public class Client {

    public static void main(String[] args) throws Exception{
        DatagramSocket clientSocket = new DatagramSocket(6789);
        InetAddress IPAddress = InetAddress.getByName("localhost");
        Thread ww = new Thread(new WorkerWriter(clientSocket, IPAddress, 6788));
        Thread wr = new Thread(new WorkerReader(clientSocket));
        ww.start();
        wr.start();
    }
}
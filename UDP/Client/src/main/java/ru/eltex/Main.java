package ru.eltex;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

class Main {
    public static void main(String[] args) {
        try
        {
            byte[] data = "Hello".getBytes();
            InetAddress addr = InetAddress.getByName("172.21.0.134");
            DatagramPacket pack = new DatagramPacket(data,data.length,addr,1025);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}

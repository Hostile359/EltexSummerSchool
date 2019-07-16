package ru.eltex;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Main
{
    public static void main(String[] args) {
        try
        {
            DatagramSocket ds = new DatagramSocket(1025);
            while (true)
            {
                DatagramPacket pack = new DatagramPacket(new byte[1024],1024);
                ds.receive(pack);
                System.out.println(new String(pack.getData()));
            }
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}

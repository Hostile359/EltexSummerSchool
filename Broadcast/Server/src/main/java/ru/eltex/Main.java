package ru.eltex;


import java.io.*;
import java.net.*;


class Main {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(2973);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        while (true) {
            try {
                socket.receive(packet);
                System.out.println("Received from: " + packet.getAddress() + ":" + packet.getPort());
            } catch (IOException error) {
                error.getMessage();
            }
        }
    }
}
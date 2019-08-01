package ru.eltex;


import java.io.IOException;
import java.net.*;


class Main {
    private static DatagramSocket socket = null;
    public static void main(String[] args) throws IOException {
        broadcast("Test",InetAddress.getByName("255.255.255.255"));
    }

    public static void broadcast(String broadcastMessage, InetAddress address) throws IOException {
        socket = new DatagramSocket();
        socket.setBroadcast(true);
        byte[] buffer = broadcastMessage.getBytes();

        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,2973);
        socket.send(packet);
        socket.close();
    }
}
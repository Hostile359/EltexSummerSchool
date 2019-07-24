package ru.eltex;


import java.io.*;
import java.net.*;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try {
            String path = "src/resources/index.html";
            File file = new File(path);
            Scanner sc = new Scanner(file).useDelimiter("\0");
            String html = sc.next();
            String output = "HTTP/1.0: 200 OK\nContent-Length:" + html.length() + "\n\n" + html;
            System.out.print(output);
            ServerSocket s = new ServerSocket(2490);
            System.out.print("\nServer started\n");
            while(true) {
                Socket client  = s.accept();
                new Thread(() -> {
                    try {
                        System.out.println("Started thread: " + Thread.currentThread().getName());
                        OutputStream outStream = client.getOutputStream();
                        PrintWriter out = new PrintWriter(outStream);
                        out.write(output);
                        out.flush();
                    } catch (IOException exx) {
                        System.err.println(exx.getMessage());
                    }
                }).start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

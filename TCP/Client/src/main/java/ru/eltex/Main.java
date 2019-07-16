package ru.eltex;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try(Socket s = new Socket("172.21.0.134",1026))
        {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();
            Scanner sc = new Scanner(inStream);
            Scanner sw = new Scanner(System.in);
            PrintWriter out = new PrintWriter(outStream);
            
            String line = sc.nextLine();
            System.out.println("Server: " + line);
            line = sw.nextLine();
            System.out.println("Sendend: " + line);
            out.write(line + "\n");
            out.flush();
            line = sc.nextLine();
            System.out.println("Server: " + line);
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
}

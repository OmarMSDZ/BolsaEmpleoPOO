package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Servidor extends Thread {
    private ServerSocket serverSocket;
    private volatile boolean running = false;

    public void run() {
        try {
            serverSocket = new ServerSocket(7000);
            running = true;
            System.out.println("Servidor iniciado en puerto 7000");
            while (running) {
                Socket nsfd = serverSocket.accept();
                System.out.println("Conexion aceptada de: " + nsfd.getInetAddress());
                DataInputStream oos = new DataInputStream(nsfd.getInputStream());

                Date fecha = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String formattedDate = dateFormat.format(fecha);

                DataOutputStream escritor = new DataOutputStream(
                        new FileOutputStream(new File("BdLaboreaBckp_" + formattedDate + ".dat")));
                int unByte;
                try {
                    while ((unByte = oos.read()) != -1) {
                        escritor.write(unByte);
                    }
                    oos.close();
                    escritor.close();
                    nsfd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            if (running) {
                System.out.println("Error en el servidor: " + ioe);
            } else {
                System.out.println("Servidor detenido.");
            }
        }
    }

    public void detenerServidor() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

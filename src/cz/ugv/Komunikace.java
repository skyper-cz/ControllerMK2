package cz.ugv;

import java.io.*;
import java.net.*;

public class Komunikace {
    public static void Communication(String adresa, int port, String smerJizdy, String autentifikace) throws UnknownHostException, SocketException {

        InetAddress address = InetAddress.getByName(adresa);
        DatagramSocket socket = new DatagramSocket();
        System.out.println("Odesílám na: " + adresa + ":" + port + " A jedeme: " + smerJizdy);

        String json = "{"
                + "\"smerJizdy\":\"" + smerJizdy + "\","
                + "\"autentifikace\":\"" + autentifikace + "\""
                + "}";

        try {
            byte[] buffer = json.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);
        }
        catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void Authentification(String adresa, int port, String Klic) throws UnknownHostException, SocketException {

        InetAddress address = InetAddress.getByName(adresa);
        DatagramSocket socket = new DatagramSocket();
        System.out.println("Kod na: " + adresa + ":" + port + " A jedeme: " + Klic);

        try {
            byte[] buffer = Klic.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, (port + 20));
            socket.send(request);
        }
        catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

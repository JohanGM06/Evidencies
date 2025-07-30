package second;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientNumbers {

    public static void main(String[] args) {

        final String HOST = "127.0.0.1";
        final int PORT = 5445;

        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Connect to server");

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream entry = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < 10; i++) {
                System.out.println("Enter the number #" + (i + 1) + ": ");
                int number = scanner.nextInt();
                out.writeInt(number);
            }

            String answer = entry.readUTF();
            System.out.println("Server Response");
            System.out.println(answer);

            out.close();
            entry.close();
            scanner.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package second;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerNumbers {

    public static void main(String[] args) {
        final int PORT = 5445;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server wait to connect in the port " + PORT + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            DataInputStream entry = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ArrayList<Integer> numbers = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                int number = entry.readInt();
                System.out.println("Number recived: " + number);
                numbers.add(number);
            }

            // Calcular mayor, menos y suma
            int largest = numbers.get(0);
            int less = numbers.get(0);
            int sum = 0;

            for (int n : numbers) {
                if (n > largest)
                    largest = n;
                if (n < less)
                    less = n;
                sum += n;
            }

            // Aquí envía el resultado al cliente
            String message = "The largest number is " + largest + ", the smallest number is " + less
                    + ", and the sum of all the numbers entered is " + sum + ".";

            out.writeUTF(message);
            System.out.println("Message sent to client");

            entry.close();
            out.close();
            socket.close();
            System.out.println("Server closed");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

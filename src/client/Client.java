package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
  public static void main(String args[]) throws IOException {
    if (args.length < 2) {
      System.out.println("Usage: java Client <IPAddress> <Port> <Type>");
    }

    String host = args[0];
    int port = Integer.parseInt(args[1]);
    
    try {
      Scanner scanner = new Scanner(System.in);
      Socket socket = new Socket(host, port);

      System.out.print("Enter Text: ");
      String str = scanner.nextLine();

      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      out.println(str);
      System.out.println("Response from the server: " + in.readLine());

      in.close();
      out.close();
      socket.close();
    }
    catch (Exception e) {
      System.out.println("No port found at IP and port given");
    }
  }
}
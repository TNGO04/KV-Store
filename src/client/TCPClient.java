package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient implements IClient {
  private Socket socket;
  public TCPClient(int port, String host) throws IOException {
    this.socket = new Socket(host, port);
    System.out.println("Client initiated on port " + port + " at host " + host);
  }

  @Override
  public void start() throws IOException {
      while (true) {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter Command: ");
         String str = scanner.nextLine();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(str);
        System.out.println(in.readLine());
      }
  }
}

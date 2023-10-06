package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient implements IClient {
  private Socket socket;
  private Logger logger;
  public TCPClient(int port, String host, Logger logger) throws IOException {
    this.socket = new Socket(host, port);
    this.logger= logger;
    this.logger.log(Level.INFO, "Client initiated on port " + port + " at host " + host);
  }

  @Override
  public void start() throws IOException {
    long start;
    while (true) {
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter Command: ");
         String str = scanner.nextLine();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(str);
        this.logger.log(Level.INFO, "Request sent: " + str);
        start = System.currentTimeMillis();

        while ((System.currentTimeMillis() - start < Client.TIMEOUT) && !in.ready()){
          ;
        }

        if (in.ready()) {
          String response = in.readLine();
          System.out.println(response);
          this.logger.log(Level.INFO, "Response received: " + response);
        }
        else {
          System.out.println("Request timed out");
          this.logger.log(Level.INFO, "Request timed out");
        }
      }
  }
}

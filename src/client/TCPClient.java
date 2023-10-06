package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient implements IClient {
  private Socket socket;
  private Logger logger;
  private String host;
  private int port;

  public TCPClient(int port, String host, Logger logger) throws IOException {
    this.host = host;
    this.port = port;
    this.socket = new Socket(host, port);
    this.socket.setSoTimeout(Client.TIMEOUT);

    this.logger= logger;
    this.logger.log(Level.INFO, "Client initiated on port " + port + " at host " + host);
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
        this.logger.log(Level.INFO, "Request sent to host " + host + " at port " + port
                + ": " + str);


        try {
          String response = in.readLine();
          System.out.println(response);
          this.logger.log(Level.INFO, "Response received: " + response);
        }
        catch (SocketTimeoutException e)  {
          System.out.println("Request timed out");
          this.logger.log(Level.INFO, "Request timed out");
        }
        catch (SocketException e) {
          System.out.println("Connection reset. Closing client.");
          this.logger.log(Level.INFO, "Connection Reset");
          break;
        }

      }
  }
}

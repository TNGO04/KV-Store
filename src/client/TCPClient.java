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

/*
  TCPClient class implements IClient interface and is used to create a TCP client
 */
public class TCPClient implements IClient {
  private Socket socket;
  private Logger logger;
  private String host;
  private int port;

  /*
   Constructor for TCP CLient, taking in port, host, and logger
   */
  public TCPClient(int port, String host, Logger logger) throws IOException {
    this.host = host;
    this.port = port;
//    create socket and set timeout for socket
    this.socket = new Socket(host, port);
    this.socket.setSoTimeout(Client.TIMEOUT);

    this.logger= logger;
    this.logger.log(Level.INFO, "Client connected to socket at port " + port + " at host " + host);
  }

  /*
    takes in user input and sends to server, receiving response
   */
  @Override
  public void start() throws IOException {
    while (true) {
//         scan command input
         Scanner scanner = new Scanner(System.in);
         System.out.print("Enter Command: ");
         String str = scanner.nextLine();

//         create input and output stream
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println(str); // send message through stream
        this.logger.log(Level.INFO, "Request sent to host " + host + " at port " + port
                + ": " + str);

        // read response, if timeout exceeded print message and log to serverlog
        try {
          String response = in.readLine();
          System.out.println(response);
          this.logger.log(Level.INFO, "Response received: " + response);
        }
        catch (SocketTimeoutException e)  {
          System.out.println("Request timed out");
          this.logger.log(Level.INFO, "Request timed out");
        }
//        if connection is reset, exit client
        catch (SocketException e) {
          System.out.println("Connection reset. Closing client.");
          this.logger.log(Level.INFO, "Connection Reset");
          break;
        }

      }
  }
}

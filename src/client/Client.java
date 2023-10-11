package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import server.LogFormatter;

/*
  Client class that is responsible for creating the client based on the type
  of client (TCP or UDP)
 */
public class Client {
  public static final int TIMEOUT = 5000;
  private static IClient client;
  private static Logger logger =  Logger.getLogger("Client Log");
  private static FileHandler f;

/*
  Initialize the file handler for the logger
 */
  private static void initializeFileHandler() throws IOException {
    f = new FileHandler("client.log");
    logger.addHandler(f);
    LogFormatter formatter = new LogFormatter();
    f.setFormatter(formatter);
  }

  public static void main(String args[]) throws IOException {
    if (args.length < 3) {
      System.out.println("Usage: java Client <IPAddress> <Port> <Type>");
    }

    // Parse the arguments
    int port = Integer.parseInt(args[0]);
    String host = args[1];
    String clientType = args[2];

    initializeFileHandler();

    // Create the client based on UDP or TCP
    if (clientType.toLowerCase().equals("tcp")) {
      client = new TCPClient(port, host, logger);
    }
    else if (clientType.toLowerCase().equals("udp")) {
      client = new UDPClient(port, host, logger);
    }
    else {
      System.out.println("Invalid client type");
      return;
    }
    client.start();
  }
}
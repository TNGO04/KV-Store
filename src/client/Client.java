package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import server.LogFormatter;


public class Client {
  public static final int TIMEOUT = 5000;
  private static IClient client;
  private static Logger logger =  Logger.getLogger("Client Log");
  private static FileHandler f;

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

    int port = Integer.parseInt(args[0]);
    String host = args[1];
    String clientType = args[2];

    initializeFileHandler();

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
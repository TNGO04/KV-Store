package client;

import java.net.*;
import java.io.*;
import java.util.Scanner;


public class Client {
  static IClient client;
  public static void main(String args[]) throws IOException {
    if (args.length < 3) {
      System.out.println("Usage: java Client <IPAddress> <Port> <Type>");
    }

    int port = Integer.parseInt(args[0]);
    String host = args[1];
    String clientType = args[2];

    if (clientType.toLowerCase().equals("tcp")) {
      client = new TCPClient(port, host);
    }
    else if (clientType.toLowerCase().equals("udp")) {
      client = new UDPClient(port, host);
    }
    else {
      System.out.println("Invalid client type");
      return;
    }

    client.start();
  }
}
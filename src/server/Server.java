package server;// Code guided by tutorial: https://www.baeldung.com/a-guide-to-java-sockets
import java.net.*;
import java.io.*;

import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Server {
  static IServer server;


  public static void main(String args[]) throws IOException {
    if (args.length < 3) {
      System.out.println("Usage: java <Port> <IPAddress> <Server type>");
    }

    int port = Integer.parseInt(args[0]);
    String host = args[1];
    String serverType = args[2];

    if (serverType.toLowerCase() == "tcp") {
      server = new TCPServer(port, host);
    }
    else if (serverType.toLowerCase() == "udp") {
      server = new UDPServer(port, host);
    }
    else {
      System.out.println("Invalid server type");
      return;
    }

    server.start();


}
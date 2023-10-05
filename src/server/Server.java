package server;
import java.net.*;
import java.io.*;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Server {
  private static IServer server;
  private static Logger logger =  Logger.getLogger("Server Log");
  private static FileHandler f;

  public Server() {
  }

  private static void initializeFileHandler() throws IOException {
    f = new FileHandler("server.log");
    logger.addHandler(f);
    LogFormatter formatter = new LogFormatter();
    f.setFormatter(formatter);
  }
  public static void main(String args[]) throws Exception {
    if (args.length < 2) {
      System.out.println("Usage: java <Port> <Server type>");
      return;
    }
    // parse arguments
    int port = Integer.parseInt(args[0]);
    String serverType = args[1];

    initializeFileHandler();

    if (serverType.toLowerCase().equals("tcp")) {
      server = new TCPServer(port, logger);
    } else if (serverType.toLowerCase().equals("udp")) {
    //server = new UDPServer(port);
    } else {
      System.out.println("Invalid server type given: select TCP or UDP");
      return;
    }

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      // Close the FileHandler when the program is shutting down
      f.close();
    }));

    server.start();


  }
}
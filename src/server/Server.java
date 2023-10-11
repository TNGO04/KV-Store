package server;

import java.io.*;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

/*
  * Server class to create server based on server type
 */
public class Server {
  private static IServer server;
  private static Logger logger =  Logger.getLogger("Server Log");
  private static FileHandler f;

//  initialize logger with file handler
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

    // create server based on server type
    if (serverType.toLowerCase().equals("tcp")) {
      server = new TCPServer(port, logger);
    } else if (serverType.toLowerCase().equals("udp")) {
      server = new UDPServer(port, logger);
    } else {
      System.out.println("Invalid server type given: select TCP or UDP");
      return;
    }

    // add shutdown hook to close file handler once server is terminated
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      // Close the FileHandler when the program is shutting down
      f.close();
    }));

    server.start();


  }
}
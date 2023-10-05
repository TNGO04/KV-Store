package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TCPServer extends AbstractServer implements IServer{
  private ServerSocket serverSocket;
  private Logger logger;

  public TCPServer(int port, Logger logger) throws IOException  {
    super(logger);
    this.logger = logger;
    this.serverSocket = new ServerSocket(port);
    super.logger.log(Level.INFO, "Server started at port " + port);
  }

  public void start() throws IOException {
    Socket clientSocket = serverSocket.accept();

    String clientHost = clientSocket.getInetAddress().getHostAddress();
    int clientPort = clientSocket.getPort();

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    while (true) {
      // start accepting client and communication

      // read and parse input from client
      String inputLine = in.readLine();
      super.logger.log(Level.INFO, "Received a command from client at host " + clientHost
              + " port " + clientPort + ": " + inputLine);
      Command command = CommandParser.parseCommand(inputLine);
      String response = executeCommand(command);
      out.println(response);
    }
  }
}


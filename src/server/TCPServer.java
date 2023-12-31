package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
  * TCPServer uses TCP protocol to communicate with clients while serving as a KV Store
 */
public class TCPServer extends AbstractServer implements IServer{
  private ServerSocket serverSocket;

  public TCPServer(int port, Logger logger) throws IOException  {
    super(logger);
    this.serverSocket = new ServerSocket(port);
    super.logger.log(Level.INFO, "Server started at port " + port);
  }

  public void start() throws IOException {
    Socket clientSocket = serverSocket.accept();
    // get client host and port
    String clientHost = clientSocket.getInetAddress().getHostAddress();
    int clientPort = clientSocket.getPort();
    // get input and output stream
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    while (true) {
      // read and parse input from client
      String inputLine = in.readLine();
      super.logger.log(Level.INFO, "Received a command from client at host " + clientHost
              + " port " + clientPort + ": " + inputLine);

      // execute command and send response to client
      String response = executeCommand(inputLine);
      out.println(response);
    }
  }
}


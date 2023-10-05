package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements IServer{
  ServerSocket serverSocket;

  public TCPServer(int port, String host) throws IOException  {
    System.out.println("Server initiated on port " + port + " at host " + host);
    this.serverSocket = new ServerSocket(port);
  }

  public void start() throws IOException {
    while (true) {
      Socket clientSocket = serverSocket.accept();
      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


    }
  }
}

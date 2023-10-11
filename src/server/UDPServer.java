package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * UDPServer uses UDP protocol to communicate with clients while serving as a KV Store
 */
public class UDPServer extends AbstractServer implements IServer{
  private DatagramSocket serverSocket;
  private final int bufferSize = 1024;

  public UDPServer(int port, Logger logger) throws IOException {
    super(logger);
    this.serverSocket = new DatagramSocket(port);
    super.logger.log(Level.INFO, "Server started at port " + port);
  }
  @Override
  public void start() throws Exception {
    byte[] buffer = new byte[bufferSize];
    // receive request from client
    while (true) {
      // create datagram packet to receive request
      DatagramPacket request = new DatagramPacket(buffer, bufferSize);
      serverSocket.receive(request);

      // parse request and execute command
      String message = new String(request.getData(), 0, request.getLength());
      super.logger.log(Level.INFO, "Received a command from client at host " + request.getAddress()
              + " port " + request.getPort() + ": " + message);

      String response = executeCommand(message);
      // send response to client
      byte[] responseByte = response.getBytes();
      DatagramPacket reply = new DatagramPacket(responseByte, responseByte.length,
              request.getAddress(), request.getPort());
      serverSocket.send(reply);
    }
  }
}

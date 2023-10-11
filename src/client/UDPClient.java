package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
  UDPClient class that implements IClient interface and is used to create a UDP client
 */
public class UDPClient implements IClient{
  private Logger logger;
  private DatagramSocket socket;
  private InetAddress address;
  private int port;

  private final int bufferSize = 1024;

  /*
    Constructor for UDPClient
   */
  public UDPClient (int port, String host, Logger logger) throws SocketException, UnknownHostException {
    this.address = InetAddress.getByName(host);
    this.port = port;

    // Create a datagram socket, bound to any available port on the local machine
    this.socket = new DatagramSocket();
    this.socket.setSoTimeout(Client.TIMEOUT);

    this.logger = logger;
    this.logger.log(Level.INFO, "DatagramSocket created");
  }

  /*
  takes in user input and sends to server, receiving response
 */
  @Override
  public void start() throws IOException {
    byte[] receivingBuffer = new byte[bufferSize];

    // Keep sending requests to the server
    while (true) {
      // Get the command from the user
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter Command: ");
      String str = scanner.nextLine();

      byte[] message = str.getBytes();
      // Create a datagram packet for outgoing message
      DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
      socket.send(packet);
      this.logger.log(Level.INFO, "Request sent to " + address
              + " at port " + port + ": " + str);


      packet = new DatagramPacket(receivingBuffer, bufferSize);
      // Receive the response from the server
      try {
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        System.out.println(response);
        this.logger.log(Level.INFO, "Response Received: " + response);

      }
      // Handle timeout
      catch (SocketTimeoutException e) {
        // Handle the timeout condition here
        System.out.println("Request timed out");
        this.logger.log(Level.INFO, "Request timed out");
      }
    }

  }
}

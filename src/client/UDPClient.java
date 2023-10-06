package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPClient implements IClient{
  private Logger logger;
  private DatagramSocket socket;
  private InetAddress address;
  private int port;

  private final int bufferSize = 1024;

  public UDPClient (int port, String host, Logger logger) throws SocketException, UnknownHostException {
    this.address = InetAddress.getByName(host);
    this.socket = new DatagramSocket();
    this.socket.setSoTimeout(Client.TIMEOUT);
    this.port = port;

    this.logger = logger;
    this.logger.log(Level.INFO, "DatagramSocket created");
  }

  @Override
  public void start() throws IOException {
    byte[] receivingBuffer = new byte[bufferSize];

    while (true) {
      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter Command: ");
      String str = scanner.nextLine();

      byte[] message = str.getBytes();

      DatagramPacket packet = new DatagramPacket(message, message.length, address, port);
      socket.send(packet);
      this.logger.log(Level.INFO, "Request sent to " + address
              + " at port " + port + ": " + str);


      packet = new DatagramPacket(receivingBuffer, bufferSize);
      try {
        socket.receive(packet);

        String response = new String(packet.getData(), 0, packet.getLength());
        System.out.println(response);
        this.logger.log(Level.INFO, "Response Received: " + response);

      } catch (SocketTimeoutException e) {
        // Handle the timeout condition here
        System.out.println("Request timed out");
        this.logger.log(Level.INFO, "Request timed out");
      }
      catch (SocketException e) {
        System.out.println("Connection reset. Closing client.");
        this.logger.log(Level.INFO, "Connection Reset");
        break;
      }
    }

  }
}

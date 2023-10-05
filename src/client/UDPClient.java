package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient implements IClient{
  public UDPClient (int port, String host) throws SocketException {
    System.out.println("Client initiated on port " + port + " at host " + host);
    DatagramSocket socket = new DatagramSocket(port);
  }
  @Override
  public void start() {

//    while (true) {
//      Scanner scanner = new Scanner(System.in);
//      System.out.print("Enter Command: ");
//      String str = scanner.nextLine();
//
//      DatagramPacket packet = new DatagramPacket(str.getBytes(), str.length());
//    }

  }
}

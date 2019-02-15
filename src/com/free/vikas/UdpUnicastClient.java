package com.free.vikas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpUnicastClient implements Runnable{
	
	private final int port;
	
	public UdpUnicastClient(int port) {
		this.port = port;
	}
	
	@Override
	public void run() {
		// Bind client to socket port which will get incoming messages
		try(DatagramSocket clientSocket = new DatagramSocket(port)) {
			
			// Byte array buffer to store incoming data
			byte[] buffer = new byte[65507];
			
			// Set timeout of 3000ms for the client
			clientSocket.setSoTimeout(3000);
			
			while(true){
				DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
				clientSocket.receive(datagramPacket);
				String receivedMessage = new String(datagramPacket.getData());
				System.out.println(receivedMessage);
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
			
	} catch (IOException e) {
			System.out.println("Timeout. Client will close now.");
		}
	}

}
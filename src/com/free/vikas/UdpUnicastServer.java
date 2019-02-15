package com.free.vikas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpUnicastServer implements Runnable{
	
	/** Client Listening Port*/
	private final int clientPort;
	
	public UdpUnicastServer(int clientPort) {
		this.clientPort = clientPort;
	}
	
	@Override
	public void run() {
		// Create a new server socket and bind it to a free port.
		try(DatagramSocket serverSocket = new DatagramSocket(50000)) {
			for (int i=0; i<3; i++) {
				String msg =  "Message number: " + i;
				DatagramPacket datagramPacket = new DatagramPacket(
						msg.getBytes(), 
						msg.length(),
						InetAddress.getLocalHost(),
						clientPort);
				serverSocket.send(datagramPacket);
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

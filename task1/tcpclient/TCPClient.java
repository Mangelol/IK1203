package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {
    
	static int BUFFERSIZE = 1024;
	
    public TCPClient() {
    }

    public byte[] askServer(String hostname, int port, byte [] toServerBytes) throws IOException {
    	
    	Socket clientSocket = null;
    	DataOutputStream outServer = null;
    	DataInputStream inServer = null;
    	ByteArrayOutputStream outputStream = null;
    	
    	try {
    		clientSocket = new Socket(hostname, port);
    		outServer = new DataOutputStream(clientSocket.getOutputStream());
    		outServer.write(toServerBytes);
    		
    		inServer = new DataInputStream(clientSocket.getInputStream());
    		outputStream = new ByteArrayOutputStream();
    		byte[] buffer = new byte[BUFFERSIZE];
    		
    		int read;
    		
    		while((read = inServer.read(buffer)) != -1) {
    			outputStream.write(buffer, 0, read);
    		}
    	} catch (Exception e) {
    		throw new IOException("Error: something bad happened");
    	} finally {
    		if(clientSocket != null) {
    			clientSocket.close();
    		}
    		if(outServer != null) {
    			outServer.close();
    		}
    		if(inServer != null) {
    			inServer.close();
    		}
    		if(outputStream != null) {
    			outputStream.close();
    		}
    	}
        return outputStream.toByteArray();
    }
}

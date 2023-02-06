package lectures;
import java.net.*;
import java.nio.charset.StandardCharsets;

/*
 * sequential server
 */
class TCPServer {
	static int BUFFERSIZE = 1024;
	
	public static void main(String argv[]) throws Exception {
		//Calls socket(), bind() and listen() system calls to create a
		// "welcoming socket with port number 6789.
		ServerSocket welcomeSocket = new ServerSocket(6789);
		
		while(true) {
			//Wait for a client to connect. Create a new socket for
			// communication with the client.
			Socket connectionSocket = welcomeSocket.accept();
			byte[] fromClientBuffer = new byte[BUFFERSIZE];
			int fromClientLength = connectionSocket.getInputStream().read(fromClientBuffer);
			
			String clientSentence = new String(fromClientBuffer, 0, 
												fromClientLength, StandardCharsets.UTF_8);
			//capitalize string
			String capitalizedSentence = clientSentence.toUpperCase();
			//compute response for client
			//byte[] toClientBuffer = ...
			byte[] toClientBuffer = capitalizedSentence.getBytes(StandardCharsets.UTF_8);
			
			
			connectionSocket.getOutputStream().write(toClientBuffer);
			connectionSocket.close();
		}
	}
}

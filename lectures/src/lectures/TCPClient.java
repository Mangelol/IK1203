package lectures;
import java.net.*;

class TCPClient {
	private static int BUFFERSIZE = 1024;
	
	public static void main(String argv[]) throws Exception {
		//pre-allocate byte buffers for reading/recieving
		
		byte[] fromUserBuffer = new byte[BUFFERSIZE];
		byte[] fromServerBuffer = new byte[BUFFERSIZE];
		
		/* Call socket() and then connect() system calls to open connection to server	
		 * "hostname" at port 6789
		 */
		Socket clientSocket = new Socket("hostname", 6789);
		
		int fromUserLength = System.in.read(fromUserBuffer); //User input
		//Send bytes on socket
		clientSocket.getOutputStream().write(fromUserBuffer, 0, fromUserLength); 
		//Recieve bytes on socket
		int fromServerLength = clientSocket.getInputStream().read(fromServerBuffer);
		System.out.print("FROM SERVER: "); //Use print method since it is a string
		System.out.write(fromServerBuffer, 0, fromServerLength);
		clientSocket.close();
	}
}

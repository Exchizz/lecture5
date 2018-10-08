import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    protected static int  counter = 0;


    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            
	    final PrintStream printStream = new PrintStream(output);
	    
	    
	    
	    long time = System.currentTimeMillis();
	    String httpResponse = "HTTP/1.1 200 OK\r\nConnection: close\r\nContent-Type: text/html\r\n\r\nHello world: " + counter + "\r\n";
	    counter++;
	    output.write(httpResponse.getBytes("UTF-8"));

	    clientSocket.shutdownOutput();
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
	    try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
	    counter--;
            System.out.println("Thread stopped");
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}

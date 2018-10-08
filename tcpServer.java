public class tcpServer {
    public static void main(String[] args) {
         MultiThreadedServer server = new MultiThreadedServer(9000);
         Thread t1 = new Thread(server);
	 t1.start();
	 try {
           t1.join(); 
	 } catch (InterruptedException e) {
            e.printStackTrace();
        }
         System.out.println("Stopping Server");
         server.stop();


    }
}












import java.io.*;
import java.net.*;

public class DNSHandler
{
    public static final int BUFFER_SIZE = 256;

    // This is invoked by a different thread
    public void process(Socket serverSocket) throws java.io.IOException {
        byte[] buffer = new byte[BUFFER_SIZE];

        BufferedReader fromClient = null;
        PrintWriter out = null;
        String url = null;
        
        try {
            fromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            url = fromClient.readLine();
            System.out.println("Got the url " + url);

            InetAddress address = InetAddress.getByName(url);
            String ip = address.getHostAddress();
            out.println(ip);
        }
        catch (UnknownHostException uhe) {
            out.println("Unknown host: " + url);
        }
        catch (IOException ioe) {
            out.println(ioe);
        }
        finally {
            fromClient.close();
            out.close();
        }
    }
}

import java.io.*;
import java.net.*;

public class DNSHandler
{
    public static final int BUFFER_SIZE = 256;

    // This is invoked by a different thread
    public void process(Socket client) throws java.io.IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        
        try (
            //InputStream fromClient = new BufferedInputStream(client.getInputStream());
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            //OutputStream toClient = new BufferedOutputStream(client.getOutputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        ){
            String url = fromClient.readLine();
            System.out.println("Got the url " + url);
            //out.println(
            //toClient.write("test");
            // Writes the data to the network
            //toClient.flush();
        }
        catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}

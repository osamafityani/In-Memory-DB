import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try(Socket socket = new Socket("localhost", 2000)){

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String userInput = null;

            while (!"quit".equals(userInput)){

                // reading from user
                userInput = scanner.nextLine();

                //sending userInput to the server
                out.println(userInput);

                // printing server response to the screen
                //TODO: Write logic to handle server response (could be db records or an error message
            }
            scanner.close();
        }
        catch (IOException e){
            System.out.println("Error: Server is not running...");
        }

    }
}

import java.io.*;
import java.net.*;

//            if (input.equalsIgnoreCase("Time")) {
//                Date d = new Date();
//                String time = d.toString();
//                out.println(time);
//            } else {
//                out.println("Unknown Command");
//            }


class ServerThread extends Thread {

    Socket clientSocket;

    ServerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try{

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String userInput;

            while((userInput = in.readLine()) != null){

                System.out.println("Sent from the client" + clientSocket);
                System.out.println("Message: "+ userInput);

                //TODO: Create protocol
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
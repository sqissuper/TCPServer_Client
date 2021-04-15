package tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    private static final int port = 9002;
    private static final String ip = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(ip,port);

        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner sc = new Scanner(System.in);
        ) {
            while(true) {
                System.out.println("->");

                String msg = sc.nextLine();
                writer.write(msg + "\n");
                writer.flush();

                String serMsg = reader.readLine();
                if(serMsg != null && !serMsg.equals("")) {
                    System.out.println("自动回复：" + serMsg);
                }
            }
        }
    }

}

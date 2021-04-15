package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private static final int port = 9002;
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务器已启动");

        //等待客户端连接
        Socket clientSocket = serverSocket.accept();
        System.out.println(String.format("已有客户端连接，IP:%s,端口号:%d",clientSocket.getInetAddress().getHostAddress(),clientSocket.getPort()));


        try(
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
            while(true) {
                String msg = reader.readLine();
                if(msg != null && !msg.equals("")) {
                    System.out.println("接收到客户端消息：" + msg);
                }
                String serMsg = "我收到了";
                writer.write(serMsg + "\n");
                writer.flush();
            }

        }
    }
}

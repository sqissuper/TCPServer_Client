package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyHTTP {
    private static final int port = 9004;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        System.out.println("服务器已启动~");

        //客户端连接
        Socket socket = serverSocket.accept();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()))
        ) {
            //首行信息
            String firstLine = reader.readLine();
            String[] firstLineArr = firstLine.split(" ");

            String method = firstLineArr[0];
            String uri = firstLineArr[1];
            String httpVersion = firstLineArr[2];

            System.out.println(String.format("首行信息->方法类型：%s,URL:%s,HTTP版本号:%s",method,uri,httpVersion));

            //返回内容
            String content = "";
            if(uri.contains("404")) {
                content = "<h1>没有找到此页面</h1>";
            } else if(uri.contains("202")) {
                content = "<h1>Hello,world</h1>";
            }

            writer.write(String.format("%s 200 OK",httpVersion) + "\n");

            writer.write("Content-Type: text/html;charset=utf-8\n");
            writer.write("Content-Length: " + content.getBytes().length + "\n");
            // 输出空行
            writer.write("\n");
            writer.write(content);
            writer.flush();
        }
    }
}

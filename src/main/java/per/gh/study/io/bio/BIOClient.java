package per.gh.study.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class BIOClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket=new Socket("127.0.0.1",8888);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello\n".getBytes());
        outputStream.flush();
        Thread.sleep(100);
        outputStream.write("你好啊！！\n".getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
    }
}

package per.gh.study.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        for (; ; ) {
            Socket socket = ss.accept();

            new Thread(() -> {
                InputStream inputStream = null;
                byte[] buff = new byte[1024];
                try {
                    System.out.println(socket.getRemoteSocketAddress() + "链接成功");
                    inputStream = socket.getInputStream();
                    int length;
                    while ((length = inputStream.read(buff)) > -1) {
                        String s = new String(buff, 0, length);
                        System.out.println("[" + socket.getRemoteSocketAddress() + "]#"+s);
                    }
                    System.out.println(socket.getRemoteSocketAddress() + "链接中断");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }).start();
        }
    }
}

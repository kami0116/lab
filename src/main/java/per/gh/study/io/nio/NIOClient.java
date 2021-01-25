package per.gh.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1",8888));
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        buffer.put("hello".getBytes());
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
        buffer.put("你好啊！！".getBytes());
        buffer.flip();
        channel.write(buffer);
        channel.close();

    }
}

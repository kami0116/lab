package per.gh.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(8888));
        channel.configureBlocking(false);
        Selector sel = Selector.open();
        channel.register(sel, SelectionKey.OP_ACCEPT);


        for (; ; ) {
            sel.select();
            Set<SelectionKey> keys = sel.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel ch = (ServerSocketChannel) key.channel();
                    SocketChannel newCh = ch.accept();
                    newCh.configureBlocking(false);
                    newCh.register(sel, SelectionKey.OP_READ);
                    System.out.println(newCh.getRemoteAddress() + "链接成功");
                } else if (key.isReadable()) {
                    SocketChannel ch = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(4096);
                    StringBuilder sb = new StringBuilder();
                    if (ch.read(buffer) > 0) {
                        buffer.flip();
                        sb.append(new String(buffer.array(),buffer.position(),buffer.remaining()));
                        System.out.println(sb.toString());
                        buffer.clear();
                    }
                }
                it.remove();
            }
        }
    }
}

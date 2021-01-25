package per.gh.study.io.nio;

import org.junit.Test;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ByteBufferTest {
    @Test
    public void test() throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        Field mark = Buffer.class.getDeclaredField("mark");
        mark.setAccessible(true);
        System.out.println(">>>init");
        System.out.println("init mark:\t" + mark.get(buffer));
        System.out.println("init position:\t" + buffer.position());
        System.out.println("init limit:\t" + buffer.limit());
        System.out.println("init remaining:\t" + buffer.remaining());
        System.out.println("init capacity:\t" + buffer.capacity());

        buffer.put((byte) 'h');
        buffer.put((byte) 'e');
        buffer.put((byte) 'l');
        buffer.put((byte) 'l');
        buffer.put((byte) 'o');
        System.out.println("\n>>>write");
        System.out.println("after write mark:\t" + mark.get(buffer));
        System.out.println("after write position:\t" + buffer.position());
        System.out.println("after write limit:\t" + buffer.limit());
        System.out.println("after write remaining:\t" + buffer.remaining());
        System.out.println("after write capacity:\t" + buffer.capacity());

        buffer.flip();
        System.out.println("\n>>>flip");
        System.out.println("after flip mark:\t" + mark.get(buffer));
        System.out.println("after flip position:\t" + buffer.position());
        System.out.println("after flip limit:\t" + buffer.limit());
        System.out.println("after flip remaining:\t" + buffer.remaining());
        System.out.println("after flip capacity:\t" + buffer.capacity());

        buffer.get();
        buffer.get();
        System.out.println("\n>>>read 2");
        System.out.println("after read mark:\t" + mark.get(buffer));
        System.out.println("after read position:\t" + buffer.position());
        System.out.println("after read limit:\t" + buffer.limit());
        System.out.println("after read remaining:\t" + buffer.remaining());
        System.out.println("after read capacity:\t" + buffer.capacity());

        buffer.rewind();
        System.out.println("\n>>>rewind");
        System.out.println("after rewind mark:\t" + mark.get(buffer));
        System.out.println("after rewind position:\t" + buffer.position());
        System.out.println("after rewind limit:\t" + buffer.limit());
        System.out.println("after rewind remaining:\t" + buffer.remaining());
        System.out.println("after rewind capacity:\t" + buffer.capacity());

        buffer.clear();
        System.out.println("\n>>>clear");
        System.out.println("after clear mark:\t" + mark.get(buffer));
        System.out.println("after clear position:\t" + buffer.position());
        System.out.println("after clear limit:\t" + buffer.limit());
        System.out.println("after clear remaining:\t" + buffer.remaining());
        System.out.println("after clear capacity:\t" + buffer.capacity());
    }
}

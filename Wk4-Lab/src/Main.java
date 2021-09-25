import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        final int bufferInitSize = 60;

        Buffer buffer = Buffer.getInstance();
        for (int i = 0; i < bufferInitSize; i++) {
            buffer.insertPkt(new Packet());
        }

        Buffer.getInstance().printBuffer();

        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(producer);
        threadPool.execute(consumer);

        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }

    }
}

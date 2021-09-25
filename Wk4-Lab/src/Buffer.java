import java.util.concurrent.ArrayBlockingQueue;

class Buffer {

    private static ArrayBlockingQueue<Packet> queue;
    private static Buffer instance;

    synchronized static Buffer getInstance() {
        if (instance == null) {
            instance = new Buffer(100);
            return instance;
        } else {
            return instance;
        }
    }

    private Buffer(int capacity) {
        queue = new ArrayBlockingQueue<Packet>(capacity);
    }

    synchronized void insertPkt(Packet pkt) {
        queue.add(pkt);
    }

    synchronized Packet removePkt() {
        try {
            return queue.take();
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
            return null;
        }
    }

    synchronized int getBufferSize() {
        return queue.size();
    }

    synchronized void printBuffer() {
        System.out.println("-- BUFFER READOUT --");
        for (Packet pkt : queue) {
            System.out.println(pkt + " -> " + pkt.payload);
        }
        System.out.println("-- END BUFFER READOUT --\n");
    }
}

import java.util.Random;

class Producer extends Thread {

    private Random rand = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                // sleep:
                int i = rand.nextInt(1000);
                Thread.sleep(i);
                System.out.println("Producer: " + i + "ms sleep");
                // produce:
                i = rand.nextInt(Integer.MAX_VALUE);
                Buffer.getInstance().insertPkt(
                        new Packet(i)
                );
                System.out.println("Producer: inserted Packet in Buffer (val=" + String.format("%x", i) + ")");
                // size:
                System.out.println("Producer: size of queue = " + Buffer.getInstance().getBufferSize());
            } catch (InterruptedException ie) {
                System.out.println("Producer threw exception: " + ie.getMessage());
            }
        }
    }
}

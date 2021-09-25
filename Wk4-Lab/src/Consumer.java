import java.util.Random;

class Consumer extends Thread {

    private Random rand = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                // sleep:
                int i = rand.nextInt(1000);
                Thread.sleep(i);
                System.out.println("\tConsumer: " + i + "ms sleep");
                // consume:
                System.out.println("\tConsumer: removed Packet from Buffer (val=" +
                        Buffer.getInstance().removePkt().payload + ")"
                );
                // size:
                System.out.println("\tConsumer: size of queue = " + Buffer.getInstance().getBufferSize());
            } catch (InterruptedException ie) {
                System.out.println("\tConsumer threw exception: " + ie.getMessage());
            }
        }
    }
}

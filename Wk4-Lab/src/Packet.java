import java.util.Random;

class Packet {

    String payload;

    Packet() {
        Random rand = new Random();
        payload = String.format("%x", rand.nextInt());
    }

    Packet(int data) {
        payload = String.format("%x", data);
    }

}

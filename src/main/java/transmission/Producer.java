package transmission;

public class Producer implements Runnable {
    private final Transmission transmission;
    private int producerId;
    private final int coolDownMilliSec = 250;

    public Producer(Transmission transmission) {
        this.transmission = transmission;
        this.producerId = this.transmission.getProducerCount();
        this.transmission.onProducerAdded();
    }

    @Override
    public void run() {
        int count = 0;
        while (true) {
            try {
                Message.Side side = count % 2 == 0 ? Message.Side.BUY : Message.Side.SELL; // just some dummy code to make the msg dynamic
                Message msg = new Message(count, side, "fixStr", "CustomString", 100000000, 10, true);
                System.out.println("Producer " + producerId + " writing " + msg.toString());
                int ret = this.transmission.write(msg);
                count++;
                if (ret == 0) {
                    Thread.sleep(coolDownMilliSec);
                }
                else {
                    Thread.sleep(coolDownMilliSec * 3);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

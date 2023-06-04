package transmission;

public class Consumer implements Transmission.MessageMuncher, Runnable {
    final Transmission transmission;
    private final int readBatchSize = 10;
    private int consumerId;
    private int consumeMsgIndex = 0;

    public Consumer(Transmission transmission) {
        this.transmission = transmission;
        this.consumerId = this.transmission.getConsumerCount();
        this.consumeMsgIndex = this.consumerId;
        this.transmission.onConsumerAdded();
    }

    @Override
    public boolean on(Message m) {
        System.out.println("Consumer " + consumerId + " read " + m.toString());
        m.setValid(false);
        return true;
    }

    @Override
    public void run() {
        while (true) {
            consumeMsgIndex =transmission.read(consumeMsgIndex, readBatchSize, this);
        }
    }
}

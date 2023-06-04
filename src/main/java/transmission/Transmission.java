package transmission;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Transmission {
    private int initSize;
    private int consumerCount = 0;
    private int producerCount = 0;
    private ArrayList<Message> msgList;
    private final AtomicInteger writeMsgCount = new AtomicInteger();

    public Transmission(int initSize) {
        this.initSize = initSize;
        msgList = new ArrayList<>(initSize);
        for (int i = 0; i < initSize; i++) {
            msgList.add(new Message());
        }
    }

    void onConsumerAdded() {
        consumerCount++;
    }

    int getConsumerCount() {
        return consumerCount;
    }

    void onProducerAdded() {
        producerCount++;
    }

    int getProducerCount() {
        return producerCount;
    }

    interface MessageMuncher {
        boolean on(Message m);
    }

    public int read(int start, int howMany, MessageMuncher m) {
        int i = start;
        while (i < start + howMany * consumerCount) {
            Message message = msgList.get(i%initSize);
            if (!message.valid) {
                Thread.yield();
            } else {
                m.on(message);
                i += consumerCount;
            }
        }
        return i;
    }

    public int write(Message message) {
        Message msg = msgList.get(writeMsgCount.getAndIncrement() % initSize);
        if (!msg.valid) {
            msg.setMessage(message);
            return 0;
        }
        else {
            System.err.println("The list is full, too many messages!");
            return -1;
        }
    }
}

package transmission;

public class TransApi {

	public static void main(String[] args) {
		Transmission transmission = new Transmission(10000);
		Thread producerThread = new Thread(new Producer(transmission));
		Thread consumerThread1 = new Thread(new Consumer(transmission));
		Thread consumerThread2 = new Thread(new Consumer(transmission));

		producerThread.start();
		consumerThread1.start();
		consumerThread2.start();
	}

}

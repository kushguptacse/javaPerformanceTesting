
public class TestMain {
	public static void main(String[] args) throws InterruptedException {
		PrimeChecker check = new PrimeChecker();
		// warm up
		for (int i = 1; i < 20000; i++) {
			check.isPrime1(i);
		}
		Thread.sleep(2000);

		System.out.println("Warm up done,isPrime is natively compiled. Measuring Performance started now");
		long start = System.currentTimeMillis();
		for (int i = 1; i < 50000; i++) {
			check.isPrime1(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("Time taken : " + (end - start) + " ms");
	}

}

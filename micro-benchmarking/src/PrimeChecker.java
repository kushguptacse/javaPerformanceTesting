
public class PrimeChecker {

	public Boolean isPrime1(Integer testNumber) {
		for (int i = 2; i < testNumber; i++) {
			if (testNumber % i == 0)
				return false;
		}
		return true;
	}

	public boolean isPrime2(int testNumber) {
		int range = (int) Math.sqrt(testNumber);
		for (int i = 2; i < range; i++) {
			if (testNumber % i == 0)
				return false;
		}
		return true;
	}

}

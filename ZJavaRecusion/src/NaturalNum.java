
public class NaturalNum {

	static int i = 1;

	static void num() {
		if (i <= 9) {
			System.out.print(i++ + "  ");
			num();

		}
	}

	public static void main(String[] args) {
		num();
	}
}

public class Test {
    static int count = 0;
    static String d = "Devanand";

    public static void main(String[] args) {

        for (char c : d.toCharArray()) {
            
            count++;
            System.out.print(c +"\n");
            System.out.print("\n");

        }
        System.out.println(count + " : Devanand Length");


    }

}
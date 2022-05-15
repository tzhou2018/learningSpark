import java.sql.Array;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Test test = new Test();
        test.longest("abadgab");
    }

    public int longest(String s) {
        int[] cnts = new int[256];
        for (char c : s.toCharArray()) {
            cnts[c]++;
            System.out.println(cnts[c]);

        }
        System.out.println(Arrays.toString(cnts));
        return 0;
    }
}

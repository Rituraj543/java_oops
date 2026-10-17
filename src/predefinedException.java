import java.util.*;
public class predefinedException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
        int[] arr = {1,2,3,4};

        int i,b;
        i = sc.nextInt();
        b = sc.nextInt();
        System.out.println(arr[i]);
        System.out.println(arr[i]/b);
    }
    catch(ArithmeticException e) {
        System.out.println("Exception message : " + e.getMessage());
    }

    catch(ArrayIndexOutOfBoundsException e) {
        System.out.println("Exception message : " + e.getMessage());
    }
    finally {
        sc.close();
        System.out.println("Finally Executed.");
    }
    }
}

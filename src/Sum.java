import java.util.*;
class Sum {
    int a,b,c;

    Sum() {
        System.out.println("Default");
    }

    Sum(int a, int b) {
        this.a = a;
        this.b = b;
    }

    Sum(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    void displaySum() {
        System.out.println("Sum" + " " + (a+b+c));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        Sum obj1 = new Sum(a, b);
        obj1.displaySum();

        Sum obj2 = new Sum(a, b, c);
        obj2.displaySum();

        sc.close();
    }
}


import java.util.*;
public class rectangle1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Rectangle r = new Rectangle();

        r.length = sc.nextInt();
        r.width = sc.nextInt();

        r.area();

        sc.close();
    }
}

class Rectangle {
    int length;
    int width;

    void area() {
        System.out.println(length * width);
    }
}
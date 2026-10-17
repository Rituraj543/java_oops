public class usecase1 {
    public static void main(String[] args) {
        Book b1 = new Book("Java Basics", "J. Author", 350.0, "ISBN001");
        Book b2 = new Book("OOP Concepts", "K. Writer", 420.0, "ISBN002");
 
        System.out.println(b1.getTitle() + " by " + b1.getAuthor());
        System.out.println("Price: " + b2.getPrice());
        System.out.println("Library: " + Book.libraryName);
        System.out.println("Total books added: " + Book.bookCount);
    }
}

class Book {
    private String title;
    private String author;
    private double price;
    private String isbn;

    static int bookCount = 0;
    static final String libraryName = "Sunrise Public Library";

    Book(String title, String author, double price, String isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;

        bookCount++;
    }
    
    public void setTitle() {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }

    public void setAuthor() {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }

    public void setPrice() {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setIsbn() {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

}


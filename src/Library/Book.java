package Library;

public class Book {

    private String name;
    private String author;
    private String publisher;
    private String genre;
    private int qty;
    private double price;
    private int bwcopies;

    public Book() {

    };
    public Book(String name,
                String author, String publisher, String genre,
                int qty, double price, int bwcopies) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.qty = qty;
        this.price = price;
        this.bwcopies = bwcopies;

    }
    public String toString() {
        return "Book Name: " + name+"\n"+
                "Book Author: " + author+"\n"+
                "Book Publisher: " + publisher+"\n"+
                "Book Collection Address: " + genre +"\n"+
                "Qty: " + qty +"\n"+
                "Price: " + price +"\n"+
                "Borrowing Copies: " + String.valueOf(bwcopies);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBwcopies() {
        return bwcopies;
    }

    public void setBwcopies(int bwcopies) {
        this.bwcopies = bwcopies;
    }

    public String toString2() {
        return name+"<N/>"+author+"<N/>"+publisher+"<N/>"+ genre +"<N/>"+ qty +
                "<N/>"+ price +"<N/>"+ bwcopies;
    }

}

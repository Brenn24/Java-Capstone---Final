package Library;

import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> booksnames = new ArrayList<String>();
    private ArrayList<String> booksauthors = new ArrayList<String>();

    private File usersfile = new File("C:\\Users\\tysba\\Desktop\\Class 36\\MOD 3\\CIS 404\\Final Project\\Java Capstone - Final\\src\\data\\Users");
    private File booksfile = new File("C:\\Users\\tysba\\Desktop\\Class 36\\MOD 3\\CIS 404\\Final Project\\Java Capstone - Final\\src\\data\\Books");

    public Database() throws IOException {
        if (!usersfile.exists()) {
            try {
                usersfile.createNewFile();
            } catch (Exception _) {}
        }
        if (!booksfile.exists()) {
            try {
            booksfile.createNewFile();
            } catch (Exception _) {}
        }
        users = new ArrayList<User>();
        usernames = new ArrayList<String>();
        books = new ArrayList<Book>();
        booksnames = new ArrayList<String>();
        getUsers();
        getBooks();
    }

    public void AddUser(User s) {
        users.add(s);
        usernames.add(s.getName());
        saveUsers();
    }

    public int login(String name, String email) {
        int foundAccount = -1;
        for (User s : users) {
            if (s.getName().matches(name) &&
            s.getEmail().matches(email)) {
                foundAccount = users.indexOf(s);
                break;
            }
        }
        return foundAccount;
    }
    public User getUser(int account) {
        return users.get(account);
    }
    public void AddBook(Book book) {
        books.add(book);
        booksnames.add(book.getName());
        booksauthors.add(book.getAuthor());
        saveBooks();
    }
    public ArrayList<User> getUsers() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
            String s1;
            while ((s1 = br1.readLine()) !=null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1) {
                String[] a2 = s.split("<N/>");
                //if (a2[3].matches("Admin")) {
                    User user = new Admin(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());
                //} else {
                    /*
                    User user = new NormalUser(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());

                     */
                    System.out.println("hello");
                //}
            }
        }
        return users;
    }
    private void saveUsers() {
        String text1 = "";
        for (User user : users) {
            text1 = text1 + user.toString() + "<NewUser/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(usersfile);
            pw.print(text1);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    private void saveBooks() {
        String text1 = "";
        for (Book book : books) {
            text1 = text1 + book.toString2()+"<NewBook/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(booksfile);
            pw.print(text1);
            pw.close();
        } catch  (Exception e) {
            System.err.println(e.toString());
        }
    }
    private void getBooks() {
        String text1 = "";
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
            String s1;
            while ((s1 = br1.readLine()) !=null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        if (!text1.matches("") || !text1.isEmpty()) {
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1) {
                Book book = parseBook(s);
                books.add(book);
                booksnames.add(book.getName());
                booksauthors.add(book.getAuthor());
            }
        }
    }

    public Book parseBook(String s) {
        String[] a = s.split("<N/>");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setPublisher(a[2]);
        book.setGenre(a[3]);
        book.setQty(Integer.parseInt(a[4]));
        book.setPrice(Double.parseDouble(a[5]));
        book.setBwcopies(Integer.parseInt(a[6]));
        return book;
    }
    public ArrayList<Book> getAllBooks() {
        return books;
    }
    public int getBook(String bookname, String booksauthor) {
        int i = -1;
        for (Book book : books) {
            if (book.getName().matches(bookname) && book.getAuthor().matches(booksauthor)) {
                i = books.indexOf(book);
            }
        }
        return i;
    }
    public Book getBook(int i) {
        return books.get(i);
    }
    public void deleteBook(int i) {
        books.remove(i);
        booksnames.remove(i);
        booksauthors.remove(i);
        saveBooks();
    }
    public void deleteAllData() {
        if (usersfile.exists()) {
            try {
                usersfile.delete();
                usersfile.createNewFile();
            } catch (Exception _) {}
        }
        if (booksfile.exists()) {
            try {
                booksfile.delete();
                booksfile.createNewFile();
            } catch (Exception _) {}
        }
    }
}

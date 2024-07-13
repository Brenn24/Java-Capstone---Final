package Library;

import Library.AOperations.*;

import javax.swing.*;

public class Admin extends User {
    public Admin(String name) {
        super(name);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new Exit()
        };
    }
    public Admin(String name, String email, String phonenumber) {
        super(name, email, phonenumber);
        this.operations = new IOOperation[] {
                new ViewBooks(),
                new AddBook(),
                new DeleteBook(),
                new Search(),
                new DeleteAllData(),
                new Exit()
        };
    }

    @Override
    public void menu(Database database, User user) {
        String[] data = new String[6];
        data[0] = "View Books";
        data[1] = "Add Book";
        data[2] = "Delete Book";
        data[3] = "Search";
        data[4] = "Delete all data";
        data[5] = "Exit";

        JFrame frame = this.frame(data, database, user);
        frame.setVisible(true);
    }
    public String toString() {
    return name + "<N/>" + email + "<N/>" + phonenumber + "<N/>" + "Admin";
    }
}

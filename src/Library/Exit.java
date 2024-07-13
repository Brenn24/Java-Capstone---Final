package Library;

import Library.AOperations.IOOperation;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Exit implements IOOperation {
    Database database;
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(500, 300);
        try {
            this.database = new Database();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);

        JLabel title = Main.label("Welcome to Library Management System");
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(Color.blue);
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label1 = Main.label("Name:");
        JLabel label2 = Main.label("Email:");
        JTextField name = Main.textfield();
        JTextField email = Main.textfield();
        JButton login = Main.button("Login");
        JButton newUser = Main.button("New User");

        login.addActionListener(e -> {
            if (name.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
                return;
            }
            if (email.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                return;
            }
            login(name.getText(), email.getText(), frame);
        });
        newUser.addActionListener(e -> {
            newuser();
            frame.dispose();
        });
    }
    private void login(String name, String email, JFrame frame) {
        int n = database.login(name, email);
        if (n != -1) {
            User user = database.getUser(n);
            user.menu(database, user);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
        }
    }
    private void newuser() {

        JFrame frame = Main.frame(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 10));
        panel.setBackground(null);

        JLabel title = Main.label("Create new account");
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setForeground(Color.blue);
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label0 = Main.label("Name:");
        JLabel label1 = Main.label("Phone Number:");
        JLabel label2 = Main.label("Email:");
        JTextField name = Main.textfield();
        JTextField phonenumber = Main.textfield();
        JTextField email = Main.textfield();
        JButton createacc = Main.button("Create Account");
        JButton cancel = Main.button("Cancel");

        panel.add(label0);
        panel.add(name);
        panel.add(label1);
        panel.add(phonenumber);
        panel.add(label2);
        panel.add(email);
        panel.add(createacc);
        panel.add(cancel);

        createacc.addActionListener(e -> {
            if (name.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
                return;
            }
            if (phonenumber.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
                return;
            }
            if (email.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                return;
            }
            User user;
            user = new Admin(name.getText(), email.getText(), phonenumber.getText());
            frame.dispose();
            database.AddUser(user);
            user.menu(database, user);
        });
        cancel.addActionListener(e -> frame.dispose());

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
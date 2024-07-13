package Library;

import java.awt.*;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    static Database database;
    public static void main(String[] args) {
        try {
            database = new Database();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JFrame frame = frame(500, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
        panel.setBackground(null);

        JLabel title = label("Welcome to Library Management System");
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setFont(new Font("Arial", Font.BOLD, 15));
        title.setForeground(Color.blue);
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label1 = label("Name:");
        JLabel label2 = label("Email:");
        JTextField name = textfield();
        JTextField email = textfield();
        JButton login = button("Login");
        JButton newUser = button("New User");

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
        panel.add(label1);
        panel.add(name);
        panel.add(label2);
        panel.add(email);
        panel.add(login);
        panel.add(newUser);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void login(String name, String email, JFrame frame) {
        int index = database.login(name, email);
        if (index != -1) {
            User user = database.getUser(index);
            user.menu(database, user);
            frame.dispose();
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
        }
    }

    private static void newuser() {
        JFrame frame = frame(500, 400);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(null);

        JLabel title = label("Create new account");
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setForeground(Color.blue);
        frame.getContentPane().add(title, BorderLayout.NORTH);

        JLabel label0 = label("Name:");
        JLabel label1 = label("Phone Number:");
        JLabel label2 = label("Email:");
        JTextField name = textfield();
        JTextField phonenumber = textfield();
        JTextField email = textfield();
        JButton createacc = button("Create Account");
        JButton cancel = button("Cancel");

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
            user = new Admin(name.getText(),
                    email.getText(), phonenumber.getText());

            System.out.println("hello");

            frame.dispose();
            database.AddUser(user);
            user.menu(database, user);
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }






    public static JFrame frame(int width, int height) {
        JFrame frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Library Management System");
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);
        frame.getContentPane().setBackground(Color.white);
        return frame;
    }
    public static JLabel label(String text) {
        JLabel label1 = new JLabel(text);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setForeground(Color.black);
        return label1;
    }
    public static JTextField textfield() {
        JTextField textfield1 = new JTextField();
        textfield1.setForeground(Color.black);
        textfield1.setHorizontalAlignment(SwingConstants.CENTER);
        return textfield1;
    }
    public static JButton button(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.white);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBackground(Color.blue);
        button.setBorder(null);
        return button;
    }

    public static JLabel title(String text) {
        JLabel title = Main.label(text);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setForeground(Color.blue);
        return title;
    }
}
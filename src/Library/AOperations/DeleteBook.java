package Library.AOperations;

import Library.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBook implements IOOperation {
    @Override
    public void operation(Database database, User user) {
        JFrame frame = Main.frame(400, 210);
        frame.setLayout(new BorderLayout());
        JLabel title = Main.title("Delete book");
        frame.getContentPane().add(title, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        panel.setBackground(null);
        JLabel label = Main.label("Book Name:");
        JTextField name = Main.textfield();
        JLabel label2 = Main.label("Author Name");
        JTextField author = Main.textfield();
        JButton delete = Main.button("Delete Book");
        JButton cancel = Main.button("Cancel");
        panel.add(label);
        panel.add(name);
        panel.add(label2);
        panel.add(author);
        panel.add(delete);
        panel.add(cancel);
        delete.addActionListener(e -> {
            if (name.getText().matches("")) {
                JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                return;
            }
            int i = database.getBook(name.getText(), author.getText());
            if (i>-1) {
                database.deleteBook(i);
                JOptionPane.showMessageDialog(new JFrame(), "Book deleted successfully!");
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
            }
        });

        cancel.addActionListener(e -> frame.dispose());

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

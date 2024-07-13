package Library.AOperations;

import Library.Database;
import Library.Main;
import Library.User;

import javax.swing.*;
import java.awt.*;


public class Search implements IOOperation {
        @Override
        public void operation(Database database, User user) {
            JFrame frame = Main.frame(400, 210);
            frame.setLayout(new BorderLayout());

            JLabel title = Main.title("Search");
            frame.getContentPane().add(title, BorderLayout.NORTH);

            JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
            panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
            panel.setBackground(null);
            JLabel label = Main.label("Book Name:");
            JTextField name = Main.textfield();
            JLabel label2 = Main.label("Author Name:");
            JTextField author = Main.textfield();
            JButton search = Main.button("Search");
            JButton cancel = Main.button("Cancel");
            panel.add(label);
            panel.add(name);
            panel.add(label2);
            panel.add(author);
            panel.add(search);
            panel.add(cancel);

            search.addActionListener(e -> {
                if (name.getText().matches("")) {
                    JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                    return;
                }
                int i = database.getBook(name.getText(), author.getText());
                if (i>-1) {
                    JOptionPane.showMessageDialog(new JFrame(),database.getBook(i).toString());
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



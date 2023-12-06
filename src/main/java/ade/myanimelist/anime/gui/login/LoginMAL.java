package ade.myanimelist.anime.gui.login;

import ade.myanimelist.anime.database.repository.LoginRepository;
import ade.myanimelist.anime.database.repository.LoginRepositoryImpl;
import ade.myanimelist.anime.gui.home.AnimeListSwing;

import javax.swing.*;
import java.awt.*;

public class LoginMAL extends JFrame {
    Font tableFont = new Font(null, Font.PLAIN, 15);
    public LoginMAL() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(450, 0, 1000, 1080);
        loginPanel.setBackground(Color.BLUE);
        loginPanel.setLayout(null);

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setBounds(450, 0, 1000, 100);
        loginLabel.setFont(new Font(null, Font.PLAIN, 40));

        JLabel username = new JLabel("username");
        username.setBounds(150, 30, 400, 100);
        username.setFont(tableFont);

        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(150, 100, 700, 60);

        JLabel password = new JLabel("password");
        password.setBounds(150, 130, 400, 100);
        password.setFont(tableFont);

        JTextField passTextField = new JTextField();
        passTextField.setBounds(150, 200, 700, 60);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(400, 300, 200, 50);

        JLabel forgetPw = new JLabel("Click Here IF You Forget Password");
        forgetPw.setBounds(400, 350, 1000, 40);

        JButton signUpBtn = new JButton("Sing Up");
        signUpBtn.setBounds(400, 390, 200, 50);

        signUpBtn.addActionListener(e -> {
            System.out.println("mama aku takut");
            dispose();
            new SignUpMAL();
        });

        JPanel divContainer = new JPanel();
        divContainer.setBounds(0, 250 ,1000, 1080);
        divContainer.setLayout(null);
        divContainer.setBackground(Color.BLUE);

        divContainer.add(forgetPw);
        divContainer.add(signUpBtn);
        divContainer.add(loginLabel);
        divContainer.add(loginBtn);
        divContainer.add(password);
        divContainer.add(passTextField);
        divContainer.add(username);
        divContainer.add(usernameTextField);

        // listener or event
        loginBtn.addActionListener(e -> {
            LoginRepository login = new LoginRepositoryImpl();
            String usernameListener = usernameTextField.getText();
            String passwordListener = passTextField.getText();
            boolean isValid = login.isUsernameAndPasswordValid(
              usernameListener, passwordListener
            );
            System.out.println("login is valid = " + isValid);
            if (isValid) {
                JOptionPane.showMessageDialog(null, "succees login", "notification", JOptionPane.PLAIN_MESSAGE);
                dispose();
                new AnimeListSwing();
            } else {
                JOptionPane.showMessageDialog(null, "invalid username or password", "notification", JOptionPane.WARNING_MESSAGE);
            }
        });

        loginPanel.add(divContainer);
        add(loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1920, 1080);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginMAL();
        });
    }
}

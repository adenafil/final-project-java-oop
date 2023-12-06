package ade.myanimelist.anime.gui.login;

import ade.myanimelist.anime.database.entity.User;
import ade.myanimelist.anime.database.repository.SignUpRepository;
import ade.myanimelist.anime.database.repository.SignUpRepositoryImpl;

import javax.swing.*;
import java.awt.*;

public class SignUpMAL extends JFrame {
    Font tableFont = new Font(null, Font.PLAIN, 15);
    public SignUpMAL() {
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(450, 0, 1000, 1080);
        loginPanel.setBackground(Color.BLUE);
        loginPanel.setLayout(null);

        JLabel loginLabel = new JLabel("SIGN UP");
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

        JLabel idTele = new JLabel("Id Telegram");
        idTele.setBounds(150, 230, 400, 100);
        idTele.setFont(tableFont);

        JTextField idTextField = new JTextField();
        idTextField.setBounds(150, 300, 700, 60);

        JLabel usernameTele = new JLabel("Username Telegram");
        usernameTele.setBounds(150, 330, 400, 100);
        usernameTele.setFont(tableFont);

        JTextField userTeleTextField = new JTextField();
        userTeleTextField.setBounds(150, 400, 700, 60);

        JButton createAccount = new JButton("Create Account");
        createAccount.setBounds(400, 500, 200, 50);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(400, 600, 200, 50);

        loginBtn.addActionListener(e -> {
            System.out.println("mama aku takut");
            dispose();
            new LoginMAL();
        });

        JLabel hasAccount = new JLabel("Already have an account ?");
        hasAccount.setBounds(420, 570, 200, 20);
        hasAccount.setBackground(Color.YELLOW);

        JPanel divContainer = new JPanel();
        divContainer.setBounds(0, 200 ,1000, 1080);
        divContainer.setLayout(null);
        divContainer.setBackground(Color.BLUE);

        divContainer.add(loginLabel);
        divContainer.add(hasAccount);
        divContainer.add(loginBtn);
        divContainer.add(createAccount);
        divContainer.add(usernameTele);
        divContainer.add(userTeleTextField);
        divContainer.add(idTele);
        divContainer.add(idTextField);
        divContainer.add(password);
        divContainer.add(passTextField);
        divContainer.add(username);
        divContainer.add(usernameTextField);

        // event or listener
        createAccount.addActionListener(e -> {
            SignUpRepository signUp = new SignUpRepositoryImpl();
            String usernameListener = usernameTextField.getText();
            String passwordListener = passTextField.getText();
            String idTeleListener = idTextField.getText();
            String usernameTeleListener = userTeleTextField.getText();
            User newUser = new User();
            newUser.setUsername(usernameListener);
            newUser.setPassword(passwordListener);
            newUser.setIdTele(idTeleListener);
            newUser.setUserNameTele(usernameTeleListener);

            if (usernameListener.isBlank() || passwordListener.isBlank() || idTeleListener.isBlank()
                || usernameListener.isBlank()
            ) {
                System.out.println("kosong");
                JOptionPane.showMessageDialog(null, "field have to be filled", "notification", JOptionPane.WARNING_MESSAGE);
            } else {
                signUp.insert(newUser);
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
            new SignUpMAL();
        });
    }


}

package ade.animelist.components.hd;

import ade.animelist.controller.Controller;
import ade.animelist.database.entity.User;
import ade.animelist.database.repository.SignUpRepository;
import ade.animelist.database.repository.SignUpRepositoryImpl;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class GUI SignUP
 */
public class SignUp {
    public static JPanel container = new JPanel();

    /**
     * Method untuk mendapakan sign up panel
     * @return sign up panel
     */
    public static JPanel getSignUp() {

        GridBagConstraints gbc = new GridBagConstraints();

        container.setLayout(new GridBagLayout());
        container.setOpaque(true);
        container.setPreferredSize(new Dimension(1366, 768));
        container.setMaximumSize(new Dimension(1366, 768));
        container.setBackground(Color.decode("#121212"));

        // container register block
        JPanel containerRegister = new JPanel();
        containerRegister.setLayout(new FlowLayout(FlowLayout.CENTER));
        containerRegister.setOpaque(true);
        containerRegister.setPreferredSize(new Dimension(720, 550));
        containerRegister.setMaximumSize(new Dimension(720, 550));
        containerRegister.setBackground(Color.decode("#222222"));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);

        JPanel registerTextDiv = new JPanel();

        GridBagConstraints regisGbc = new GridBagConstraints();
        regisGbc.gridx = 0;
        regisGbc.gridy = 0;
        registerTextDiv.setLayout(new GridBagLayout());
        registerTextDiv.setOpaque(true);
        registerTextDiv.setPreferredSize(new Dimension(700, 100));
        registerTextDiv.setMaximumSize(new Dimension(700, 100));
        registerTextDiv.setBackground(Color.decode("#222222"));

        JLabel textRegister = new JLabel("Sign Up");
        textRegister.setOpaque(true);
        textRegister.setForeground(Color.WHITE);
        textRegister.setBackground(Color.decode("#222222"));
        textRegister.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));

        registerTextDiv.add(textRegister, regisGbc);

        JPanel usernameContainer = new JPanel();
        usernameContainer.setOpaque(true);
        usernameContainer.setPreferredSize(new Dimension(700, 130));
        usernameContainer.setMaximumSize(new Dimension(700, 130));
        usernameContainer.setBackground(Color.decode("#222222"));

        JLabel textUsername = new JLabel("Username");
        textUsername.setOpaque(true);
        textUsername.setPreferredSize(new Dimension(700, 30));
        textUsername.setMaximumSize(new Dimension(700, 30));
        textUsername.setBackground(Color.decode("#222222"));
        textUsername.setForeground(Color.WHITE);
        textUsername.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        JTextField textFieldUsername = new JTextField();
        textFieldUsername.setOpaque(true);
        textFieldUsername.setPreferredSize(new Dimension(700, 60));
        textFieldUsername.setMaximumSize(new Dimension(700, 60));
        textFieldUsername.setForeground(Color.WHITE);
        textFieldUsername.setBackground(Color.decode("#333333"));
        textFieldUsername.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        usernameContainer.add(textUsername);
        usernameContainer.add(textFieldUsername);

        JPanel passwordContainer = new JPanel();
        passwordContainer.setOpaque(true);
        passwordContainer.setPreferredSize(new Dimension(700, 150));
        passwordContainer.setMaximumSize(new Dimension(700, 150));
        passwordContainer.setBackground(Color.decode("#222222"));

        JLabel textPassword = new JLabel("Password");
        textPassword.setOpaque(true);
        textPassword.setPreferredSize(new Dimension(700, 30));
        textPassword.setMaximumSize(new Dimension(700, 30));
        textPassword.setBackground(Color.decode("#222222"));
        textPassword.setForeground(Color.WHITE);
        textPassword.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));


        JPasswordField textPasswordField = new JPasswordField();
        textPasswordField.setOpaque(true);
        textPasswordField.setPreferredSize(new Dimension(700, 60));
        textPasswordField.setMaximumSize(new Dimension(700, 60));
        textPasswordField.setForeground(Color.WHITE);
        textPasswordField.setBackground(Color.decode("#333333"));
        textPasswordField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        JPanel containerPasswordBox = new JPanel();
        containerPasswordBox.setLayout(new FlowLayout(FlowLayout.LEFT));
        containerPasswordBox.setOpaque(true);
        containerPasswordBox.setPreferredSize(new Dimension(700, 40));
        containerPasswordBox.setMaximumSize(new Dimension(700, 40));
        containerPasswordBox.setBackground(Color.decode("#222222"));

        JCheckBox seePasswordBox = new JCheckBox();
        seePasswordBox.setText("Show Password");
        seePasswordBox.setOpaque(true);
        seePasswordBox.setSize(new Dimension(20, 20));
        seePasswordBox.setHorizontalAlignment(SwingConstants.LEFT);
        seePasswordBox.setForeground(Color.WHITE);
        seePasswordBox.setBackground(Color.decode("#222222"));

        seePasswordBox.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            textPasswordField.setEchoChar(checkBox.isSelected() ? '\0' : '•');
        });

        containerPasswordBox.add(seePasswordBox);

        passwordContainer.add(textPassword);
        passwordContainer.add(textPasswordField);
        passwordContainer.add(containerPasswordBox);

        JPanel registerBtnContainer = new JPanel();
        registerBtnContainer.setOpaque(true);
        registerBtnContainer.setPreferredSize(new Dimension(700, 70));
        registerBtnContainer.setMaximumSize(new Dimension(700, 70));
        registerBtnContainer.setBackground(Color.decode("#222222"));

        JButton registerBtn = new JButton("Create Account");
        registerBtn.setOpaque(true);
        registerBtn.setPreferredSize(new Dimension(200, 50));
        registerBtn.setMaximumSize(new Dimension(200, 50));
        registerBtn.setBackground(Color.decode("#222222"));
        registerBtn.setBorder(new LineBorder(Color.decode("#799ef3"), 2));
        registerBtn.setForeground(Color.decode("#799ef3"));
        registerBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        registerBtn.setFocusable(false);

        registerBtn.addActionListener(e -> {

            // handle password kosong dan username kosong
            if (textFieldUsername.getText().isBlank() && textPasswordField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Username dan Password Anda Masih Kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // handle username yang menggunakan spasi
            if (textFieldUsername.getText().contains(" ")) {
                JOptionPane.showMessageDialog(null, "Username Tidak Boleh Ada Spasinya", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // handle username kosong
            if (textFieldUsername.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Username Tidak Boleh Kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // handle password kosong
            if (textPasswordField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "Password Tidak Boleh Kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!(textFieldUsername.getText().isBlank() && textPasswordField.getText().isBlank())) {
                SignUpRepository signUpRepository = new SignUpRepositoryImpl();
                User user = new User(textFieldUsername.getText(), textPasswordField.getText());
                boolean isValid = signUpRepository.insert(user);

                if (isValid) {
                    Controller.removeSignUpHD();
                    Controller.createDasshboardHD();
                    JOptionPane.showMessageDialog(null, "Akun Berhasil Dibuat", "Informasi", JOptionPane.INFORMATION_MESSAGE);
//                    ImageRenderer.runConfig();
                } else {
                    JOptionPane.showMessageDialog(null, "Akun Gagal Dibuat Karena Username dengan nama" +
                            " " + user.getUsername() + " sudah ada" , "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }

            }


        });

        registerBtnContainer.add(registerBtn);

        JPanel havenAccountContainer = new JPanel();
        havenAccountContainer.setOpaque(true);
        havenAccountContainer.setPreferredSize(new Dimension(700, 70));
        havenAccountContainer.setMaximumSize(new Dimension(700, 70));
        havenAccountContainer.setBackground(Color.decode("#222222"));

        JLabel already = new JLabel("Already have an account ?");
        already.setOpaque(true);
        already.setBackground(Color.decode("#222222"));
        already.setForeground(Color.decode("#799ef3"));

        JLabel clickHere = new JLabel("Click here");
        clickHere.setOpaque(true);;
        clickHere.setBackground(Color.decode("#222222"));
        clickHere.setForeground(Color.decode("#ffffff"));

        clickHere.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Controller.removeSignUpHD();
                Controller.createLoginHD();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                clickHere.setForeground(Color.decode("#799ef3"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                clickHere.setForeground(Color.decode("#ffffff"));
            }
        });

        havenAccountContainer.add(already);
        havenAccountContainer.add(clickHere);

        containerRegister.add(registerTextDiv);
        containerRegister.add(usernameContainer);
        containerRegister.add(passwordContainer);
        containerRegister.add(registerBtnContainer);
        containerRegister.add(havenAccountContainer);

        container.add(containerRegister, gbc);

        return container;

    }
}

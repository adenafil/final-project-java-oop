package ade.animelist.components.hd;

import ade.animelist.database.repository.ConfigRepository;
import ade.animelist.database.repository.ConfigRepositoryImpl;
import ade.animelist.database.repository.SettingRepository;
import ade.animelist.database.repository.SettingRepositoryImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

/**
 * Class GUI setting
 */
public class Setting {
    public static boolean isOpened = false;
    public static JPanel panelSetting = null;

    /**
     * Method untuk mendapatkan panel setting
     * @return
     */
    public static JPanel getPanelSetting() {
        isOpened = true;
        return panelSetting = getSetting();
    }

    /**
     * Method untuk menghapus panel setting
     */
    public static void removePanelSetting() {
        isOpened = false;
        panelSetting.removeAll();
        panelSetting.repaint();
        panelSetting.revalidate();
    }

    /**
     * Method untuk mendapatkan panel setting
     * @return
     */
    public static JPanel getSetting() {
        ConfigRepository configRepository = new ConfigRepositoryImpl();
        SettingRepository settingRepository = new SettingRepositoryImpl();

        JPanel settingContainer = new JPanel();
        settingContainer.setOpaque(true);
        settingContainer.setPreferredSize(new Dimension(1366, 1000));
        settingContainer.setMaximumSize(new Dimension(1366, 1000));
        settingContainer.setBackground(Color.decode("#333b48"));

        JPanel kotak = new JPanel();
        kotak.setOpaque(true);
        kotak.setPreferredSize(new Dimension(1000, 700));
        kotak.setMaximumSize(new Dimension(1000, 700));
        kotak.setBackground(Color.decode("#333b48"));

        JLabel setting = new JLabel("Setting Profile");
        setting.setOpaque(true);
        setting.setPreferredSize(new Dimension(1000, 60));
        setting.setMaximumSize(new Dimension(1000, 60));
        setting.setBackground(Color.decode("#333b48"));
        setting.setForeground(Color.WHITE);
        setting.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        setting.setHorizontalAlignment(SwingConstants.CENTER);

        kotak.add(setting);

        JPanel usernameContainer = new JPanel();
        usernameContainer.setOpaque(true);
        usernameContainer.setPreferredSize(new Dimension(1000, 110));
        usernameContainer.setMaximumSize(new Dimension(1000, 110));
        usernameContainer.setBackground(Color.decode("#333b48"));

        JLabel username = new JLabel("Username");
        username.setOpaque(true);
        username.setPreferredSize(new Dimension(900, 40));
        username.setMaximumSize(new Dimension(900, 40));
        username.setBackground(Color.decode("#333b48"));
//        username.setBackground(Color.PINK);
        username.setForeground(Color.WHITE);
        username.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        username.setHorizontalAlignment(SwingConstants.LEFT);

        usernameContainer.add(username);

        JTextField userNameField = new JTextField();
        userNameField.setOpaque(true);
        userNameField.setPreferredSize(new Dimension(900, 50));
        userNameField.setMaximumSize(new Dimension(900, 50));
        userNameField.setBackground(Color.decode("#333b48"));
        userNameField.setBackground(Color.WHITE);
        userNameField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        userNameField.setText(configRepository.getCurrentUsername());

        usernameContainer.add(userNameField);

        JPanel passwordContainer = new JPanel();
        passwordContainer.setOpaque(true);
        passwordContainer.setPreferredSize(new Dimension(1000, 130));
        passwordContainer.setMaximumSize(new Dimension(1000, 130));
        passwordContainer.setBackground(Color.decode("#333b48"));

        JLabel password = new JLabel("Password");
        password.setOpaque(true);
        password.setPreferredSize(new Dimension(900, 30));
        password.setMaximumSize(new Dimension(900, 30));
        password.setBackground(Color.decode("#333b48"));
        password.setForeground(Color.WHITE);
        password.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        password.setHorizontalAlignment(SwingConstants.LEFT);

        passwordContainer.add(password);

        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setOpaque(true);
        passwordTextField.setPreferredSize(new Dimension(900, 50));
        passwordTextField.setMaximumSize(new Dimension(900, 50));
        passwordTextField.setBackground(Color.decode("#333b48"));
        passwordTextField.setBackground(Color.WHITE);
        passwordTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        passwordTextField.setText(settingRepository.getPassword());

        passwordContainer.add(passwordTextField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setOpaque(true);
        showPassword.setPreferredSize(new Dimension(900, 30));
        showPassword.setMaximumSize(new Dimension(900, 30));
        showPassword.setBackground(Color.decode("#333b48"));
        showPassword.setForeground(Color.WHITE);
        showPassword.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        showPassword.addActionListener(e -> {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            passwordTextField.setEchoChar(checkBox.isSelected() ? '\0' : '•');
        });

        passwordContainer.add(showPassword);

        JPanel profileContainer = new JPanel();
        profileContainer.setOpaque(true);
        profileContainer.setPreferredSize(new Dimension(1000, 110));
        profileContainer.setMaximumSize(new Dimension(1000, 110));
        profileContainer.setBackground(Color.decode("#333b48"));
//        profileContainer.setBackground(Color.PINK);

        JLabel profile = new JLabel("Set Your Profile PATH");
        profile.setOpaque(true);
        profile.setPreferredSize(new Dimension(900, 30));
        profile.setMaximumSize(new Dimension(900, 30));
        profile.setBackground(Color.decode("#333b48"));
        profile.setForeground(Color.WHITE);
        profile.setFont(new Font(Font.SERIF, Font.BOLD, 22));
        profile.setHorizontalAlignment(SwingConstants.LEFT);

        profileContainer.add(profile);

        JPanel pathContainer = new JPanel();
        pathContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 6, 0));
        pathContainer.setOpaque(true);
        pathContainer.setPreferredSize(new Dimension(910, 100));
        pathContainer.setMaximumSize(new Dimension(910, 100));
        pathContainer.setBackground(Color.decode("#333b48"));


        JButton browseProfile = new JButton("Select File");
        browseProfile.setOpaque(true);
        browseProfile.setPreferredSize(new Dimension(100, 30));
        browseProfile.setMaximumSize(new Dimension(100, 30));
        browseProfile.setFocusable(false);
        browseProfile.setFocusPainted(false);

        JTextField pathTextField = new JTextField();
        pathTextField.setOpaque(true);
        pathTextField.setPreferredSize(new Dimension(790, 50));
        pathTextField.setMaximumSize(new Dimension(790, 50));
        pathTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        pathTextField.setText(settingRepository.getPath());

        browseProfile.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files (*.GIF,*.PNG,*.JPG, *JPEG)", "GIF","PNG","JPG", "JPEG"));

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                String selectedPath = fileChooser.getSelectedFile().getAbsolutePath().toLowerCase();

                pathTextField.setText(selectedPath);
            }
        });

        pathContainer.add(browseProfile);
        pathContainer.add(pathTextField);
        profileContainer.add(pathContainer);

        JPanel buttonContainer = new JPanel();
        buttonContainer.setOpaque(true);
        buttonContainer.setPreferredSize(new Dimension(1000, 100));
        buttonContainer.setMaximumSize(new Dimension(1000, 100));
        buttonContainer.setBackground(Color.decode("#333b48"));

        JPanel nganggurProfile = new JPanel();
        nganggurProfile.setOpaque(true);
        nganggurProfile.setPreferredSize(new Dimension(1000, 10));
        nganggurProfile.setMaximumSize(new Dimension(1000, 10));
        nganggurProfile.setBackground(Color.decode("#333b48"));

        JButton saveChangesBtn = new JButton("Save Changes");
        saveChangesBtn.setOpaque(true);
        saveChangesBtn.setPreferredSize(new Dimension(150, 40));
        saveChangesBtn.setMaximumSize(new Dimension(150, 40));
        saveChangesBtn.setForeground(Color.WHITE);
        saveChangesBtn.setBackground(Color.ORANGE);
        saveChangesBtn.setFocusable(false);
        saveChangesBtn.setBorderPainted(false);
        saveChangesBtn.setFont(new Font(Font.SERIF, Font.BOLD, 18));

        saveChangesBtn.addActionListener(e -> {

            if (userNameField.getText().isBlank() || passwordTextField.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "TextField Tidak Boleh Kosong", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // PRNYA DISINI GUYS
            boolean result = settingRepository.update(userNameField.getText(), passwordTextField.getText(), pathTextField.getText());
            if (result) {
                JOptionPane.showMessageDialog(null, "Berhasil Melakukan Perubahan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal Melakukan Perubahan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonContainer.add(nganggurProfile);
        buttonContainer.add(saveChangesBtn);

        kotak.add(usernameContainer);
        kotak.add(passwordContainer);
        kotak.add(profileContainer);
        kotak.add(buttonContainer);

        settingContainer.add(kotak);

        return settingContainer;
    }
}

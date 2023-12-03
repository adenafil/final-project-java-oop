package ade.myanimelist.anime.gui;

import javax.swing.*;

public class ProfilePanel {
    public JPanel getProgilePanel() {
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);
        profilePanel.setBounds(600, 10, 1000, 1070);
//        profilePanel.setBackground(Color.YELLOW);

        ImageIcon myProfile = new ImageIcon("src/main/java/ade/myanimelist/anime/gui/assets/profileDummy.jpg");
        JLabel profileIcon = new JLabel(myProfile);
        profileIcon.setBounds(300, 10, 400, 400);

        JLabel username = new JLabel("Username : adeNafil");
        username.setBounds(370, 270, 200, 200);

        JLabel usernameTele = new JLabel("Telegrame Username : batdee");
        usernameTele.setBounds(370, 290, 200, 200);

        JLabel idTele = new JLabel("Telegram Id : 312312312");
        idTele.setBounds(370, 310, 200, 200);

        JLabel settings = new JLabel("Menu For Setting Profile");
        settings.setBounds(370, 330, 200, 200);

        JButton changeProfile = new JButton("Profile Setting");
        changeProfile.setBounds(400, 450, 200, 30);

        changeProfile.addActionListener(e -> {
//            JFileChooser fileChooser = new JFileChooser();
//            FileNameExtensionFilter filter = new FileNameExtensionFilter(
//                    "Images Files", "jpg", "jpeg", "png"
//            );
//            fileChooser.setFileFilter(filter);
//            fileChooser.setCurrentDirectory(new File("c:\\"));
//            int response = fileChooser.showOpenDialog(null);
//
//            if (response == JFileChooser.APPROVE_OPTION) {
//                System.out.println(fileChooser.getSelectedFile());
//                String path = fileChooser.getSelectedFile().getPath();
//                ImageIcon pictureChanger = ResizeImageIcon.setImageIconSize(new ImageIcon(path), 200, 200);
//                profileIcon.setIcon(pictureChanger);
//            }
        });

        profilePanel.add(settings);
        profilePanel.add(changeProfile);
        profilePanel.add(usernameTele);
        profilePanel.add(idTele);
        profilePanel.add(profileIcon);
        profilePanel.add(username);


        return profilePanel;
    }
}

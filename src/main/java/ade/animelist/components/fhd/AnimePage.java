package ade.animelist.components.fhd;

import ade.animelist.api.JikanAPI;
import ade.animelist.controller.Controller;
import ade.animelist.database.repository.AddAnimeToDbRepository;
import ade.animelist.database.repository.AddAnimeToDbRepositoryImpl;
import ade.animelist.database.repository.ConfigRepository;
import ade.animelist.database.repository.ConfigRepositoryImpl;
import ade.animelist.components.utilcomponent.ImageRenderer;
import net.sandrohc.jikan.exception.JikanQueryException;
import net.sandrohc.jikan.model.anime.Anime;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Class Component GUI animePage atau ketika sebuah block kotak diclick
 * maka class inilah yang mengurusnya melalui method-methodnya.
 */
public class AnimePage {
    public static boolean isOpened = false;
    public static JPanel contaienrDiv = null;

    /**
     * Method untuk mendapatkan component based on click block kotak
     * semua logic kotak-kotak dari sebuah gambar anime either di home atau my collection including searching
     * semua masuk kesini sehingga menjadi anime page
     * @param id -> id mal
     * @return sebuah component animePage
     * @throws JikanQueryException
     */
    public static JPanel getAnimePageById(int id) throws JikanQueryException {
        // fetching api
        Anime anime = JikanAPI.getAnimeById(id);

        // check if the value is null or not
        if (anime.episodes == null && anime.synopsis == null) {
            anime.setEpisodes(0);
        }

        // create 3 object untuk menambahkan anime ke database
        // juga mengfetching apakah anime page ini sudah ditambahkan oleh user ke database atau tidak
        AddAnimeToDbRepository addAnimeToDbRepository = new AddAnimeToDbRepositoryImpl();
        boolean didThisAnimeExistInDatabase = addAnimeToDbRepository.doesThisAnimeExistInDatabase(id);
        ConfigRepository configRepository = new ConfigRepositoryImpl();

        // container di bawah navbar
        contaienrDiv = new JPanel();
        contaienrDiv.setOpaque(true);
        contaienrDiv.setLayout(new BoxLayout(contaienrDiv, BoxLayout.Y_AXIS));
//        contaienrDiv.setLayout(new FlowLayout(FlowLayout.LEADING, -8, 0));
        contaienrDiv.setPreferredSize(new Dimension(1920, 1000));
        contaienrDiv.setBackground(Color.decode("#333b48"));

        JPanel pelengkapBg = new JPanel();
        pelengkapBg.setOpaque(true);
        pelengkapBg.setPreferredSize(new Dimension(1920, 50));
        pelengkapBg.setMaximumSize(new Dimension(1920, 50));
        pelengkapBg.setBackground(Color.decode("#333b48"));

        contaienrDiv.add(pelengkapBg);

        // buat judul
        JLabel judul = new JLabel(anime.title);
        judul.setOpaque(true);
//        judul.setPreferredSize(new Dimension(1920, 30));
        judul.setMaximumSize(new Dimension(1920, 30));
        judul.setBackground(Color.decode("#333b48"));
        judul.setForeground(Color.WHITE);
        judul.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        judul.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        judul.setHorizontalAlignment(SwingConstants.CENTER);

        // tambahkan judul ke container
        contaienrDiv.add(judul);

        // buat desc kotak2
        JPanel kotakAnimeDesc = new JPanel();
        kotakAnimeDesc.setLayout(new GridBagLayout());
        kotakAnimeDesc.setOpaque(true);
        kotakAnimeDesc.setMaximumSize(new Dimension(1920, 100));
        kotakAnimeDesc.setBackground(Color.decode("#333b48"));

        GridBagConstraints gbcPeringkat = new GridBagConstraints();

        gbcPeringkat.insets = new Insets(10, 10, 10, 10);

        // kotak peringkat
        kotakAnimeDesc.add(createKotak("PERINGKAT", ""+anime.rank), gbcPeringkat);

        // kotak skor
        kotakAnimeDesc.add(createKotak("SKOR", "" + anime.score), gbcPeringkat);

        // Kotak Rating
        kotakAnimeDesc.add(createKotak("RATING", "" + anime.rating), gbcPeringkat);

        // Kotak Episodes
        kotakAnimeDesc.add(createKotak("TOTAL EPISODES", ""+anime.episodes), gbcPeringkat);

        // masukan kotak ke conatiner
        contaienrDiv.add(kotakAnimeDesc);

        // container
        JPanel imageAndSynopsis = new JPanel();
        imageAndSynopsis.setLayout(new BoxLayout(imageAndSynopsis, BoxLayout.X_AXIS));
        imageAndSynopsis.setMaximumSize(new Dimension(1920, 500));
        imageAndSynopsis.setOpaque(true);
        imageAndSynopsis.setBackground(Color.RED);

        // imageIcon

        ImageIcon animeIcon = ImageRenderer.createImageIconByURL(anime.images.getJpg().largeImageUrl);

        JLabel imageAnime = new JLabel();
        imageAnime.setOpaque(true);
        imageAnime.setPreferredSize(new Dimension(450, 500));
        imageAnime.setMaximumSize(new Dimension(450, 1000));
        imageAnime.setBackground(Color.decode("#333b48"));
        imageAnime.setHorizontalAlignment(JLabel.CENTER);
        imageAnime.setIcon(ImageRenderer.setImageIconSize(animeIcon, 450, 500));
        imageAnime.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // the synopsis
        JLabel synopsis = new JLabel();
        synopsis.setOpaque(true);
        synopsis.setPreferredSize(new Dimension(1500, 500));
        synopsis.setMaximumSize(new Dimension(1500, 1000));
        synopsis.setBackground(Color.decode("#333b48"));
        synopsis.setText(
                "<html>" + "<p>" + anime.synopsis + "</p>" + "</html>"
        );
        synopsis.setFont(new Font(Font.MONOSPACED, Font.TYPE1_FONT, 20));
        synopsis.setForeground(Color.WHITE);
        synopsis.setVerticalAlignment(SwingConstants.TOP);
        synopsis.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        imageAnime.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // adding up into container
        imageAndSynopsis.add(imageAnime);
        imageAndSynopsis.add(synopsis);

        // then adding up into another component
        contaienrDiv.add(imageAndSynopsis);

        // below synopsis and image anime or button adding up and substracting

        GridBagConstraints alignmentSection = new GridBagConstraints();

        alignmentSection.insets = new Insets(10, 10, 10, 10);

        JPanel sectionUserInterface = new JPanel();
        sectionUserInterface.setLayout(new GridBagLayout());
        sectionUserInterface.setOpaque(true);
        sectionUserInterface.setPreferredSize(new Dimension(1920, 80));
        sectionUserInterface.setMaximumSize(new Dimension(1920, 80));
        sectionUserInterface.setBackground(Color.decode("#333b48"));

        // button add to collection
        JButton button = new JButton();
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(180, 40));
        button.setBackground(Color.ORANGE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        button.setVisible(false);

        alignmentSection.gridx = 0;
        sectionUserInterface.add(button, alignmentSection);

        String[] statusOptions = {"WATCHING", "COMPLETED", "PLAN TO WATCH", "DROPPED"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setVisible(false);


        if (didThisAnimeExistInDatabase) {
            if (addAnimeToDbRepository.getStatusByMalId(id).equals("WATCHING")) {
                statusComboBox.setSelectedIndex(0);
            }
            if (addAnimeToDbRepository.getStatusByMalId(id).equals("COMPLETED")) {
                statusComboBox.setSelectedIndex(1);
            }
            if (addAnimeToDbRepository.getStatusByMalId(id).equals("PLAN TO WATCH")) {
                statusComboBox.setSelectedIndex(2);
            }
            if (addAnimeToDbRepository.getStatusByMalId(id).equals("DROPPED")) {
                statusComboBox.setSelectedIndex(3);
            }
        }

        alignmentSection.gridx = 1;
        sectionUserInterface.add(statusComboBox, alignmentSection);


        JLabel episodeIndex = new JLabel("Your Progress Episode Is On : 1");

        episodeIndex.setText("Your Progress Episode Is On : " + addAnimeToDbRepository.getCurrentEpsByMalId(id));

        episodeIndex.setOpaque(true);
        episodeIndex.setPreferredSize(new Dimension(200, 40));
        episodeIndex.setBorder(new LineBorder(Color.WHITE, 1));
        episodeIndex.setForeground(Color.WHITE);
        episodeIndex.setBackground(Color.decode("#333b48"));
        episodeIndex.setHorizontalAlignment(SwingConstants.CENTER);
        episodeIndex.setVisible(false);

        alignmentSection.gridx = 2;
        sectionUserInterface.add(episodeIndex, alignmentSection);

        JButton addAnime = new JButton("Add+");

        addAnime.setOpaque(true);
        addAnime.setPreferredSize(new Dimension(60, 40));
        addAnime.setBackground(Color.ORANGE);
        addAnime.setBorder(BorderFactory.createEmptyBorder());
        addAnime.setFocusPainted(false);
        addAnime.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        addAnime.setVisible(false);

        statusComboBox.addActionListener(e -> {
            if (statusComboBox.getSelectedIndex() == 1) {
                episodeIndex.setText("Your Progress Episode Is On : " + anime.episodes);
                addAnime.setEnabled(false);
            }
        });


        addAnime.addActionListener(e-> {
            int currentEps = getDigitInThisText(episodeIndex.getText());
            if (getDigitInThisText(episodeIndex.getText()) <= anime.episodes - 1) {
                currentEps = getDigitInThisText(episodeIndex.getText());
                episodeIndex.setText("Your Progress Episode Is On : " + ++currentEps);
            }

            if (getDigitInThisText(episodeIndex.getText()) == anime.episodes) {
                addAnime.setEnabled(false);
            } else if (getDigitInThisText(episodeIndex.getText()) < anime.episodes) {
                addAnime.setEnabled(true);
            }
        });

        if (anime.episodes != null) {
            if (getDigitInThisText(episodeIndex.getText()) == anime.episodes) {
                addAnime.setEnabled(false);
            } else if (getDigitInThisText(episodeIndex.getText()) < anime.episodes) {
                addAnime.setEnabled(true);
            }
        }

        alignmentSection.gridx = 3;
        sectionUserInterface.add(addAnime, alignmentSection);

        JButton subtractAnime = new JButton("Subract-");

        subtractAnime.setOpaque(true);
        subtractAnime.setPreferredSize(new Dimension(100, 40));
        subtractAnime.setBackground(Color.ORANGE);
        subtractAnime.setBorder(BorderFactory.createEmptyBorder());
        subtractAnime.setFocusPainted(false);
        subtractAnime.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        subtractAnime.setVisible(false);

        subtractAnime.addActionListener(e-> {
            int currentEps = getDigitInThisText(episodeIndex.getText());
            if (0 <= currentEps - 1) {
                episodeIndex.setText("Your Progress Episode Is On : " + --currentEps);

                if (currentEps == 0) {
                    boolean result = addAnimeToDbRepository.removeDatabaseAnimeUserById(id);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Berhasil Menghapus Anime " + anime.title, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                        statusComboBox.setVisible(false);
                        episodeIndex.setVisible(false);
                        addAnime.setVisible(false);
                        subtractAnime.setVisible(false);
                        button.setVisible(true);
                        button.setText("Add To Collection");

                    }
                    else JOptionPane.showMessageDialog(null, "Gagal Menghapus Anime " + anime.title, "Informasi", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (getDigitInThisText(episodeIndex.getText()) == anime.episodes) {
                addAnime.setEnabled(false);
            } else if (getDigitInThisText(episodeIndex.getText()) < anime.episodes) {
                addAnime.setEnabled(true);
            }

        });

        alignmentSection.gridx = 4;
        sectionUserInterface.add(subtractAnime, alignmentSection);

        if (addAnimeToDbRepository.doesThisAnimeExistInDatabase(id)) {
            button.setText("Save Changes");
            statusComboBox.setVisible(true);
            episodeIndex.setVisible(true);
            addAnime.setVisible(true);
            subtractAnime.setVisible(true);
            addAnime.setVisible(true);
            button.setVisible(true);

        } else {
            button.setText("Add To Collection");
            button.setVisible(true);
            button.addActionListener(e -> {

                statusComboBox.setVisible(true);
                episodeIndex.setVisible(true);
                addAnime.setVisible(true);
                subtractAnime.setVisible(true);

                button.setText("Save Changes");

                addAnime.setVisible(true);

            });
        }

        button.addActionListener(e -> {
            // save ke database
            if (addAnimeToDbRepository.doesThisAnimeExistInDatabase(id)) {
                boolean didSuccessToAddInDatabase = addAnimeToDbRepository.addProgressWatchingAnime(
                        configRepository.getCurrentUserId(),
                        anime.malId,
                        statusComboBox.getSelectedItem().toString(),
                        getDigitInThisText(episodeIndex.getText())
                );


                if (didSuccessToAddInDatabase) JOptionPane.showMessageDialog(null, "Berhasil Menyimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(null, "Gagal Menyimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean didSuccessToAddInDatabase = addAnimeToDbRepository.add(
                        configRepository.getCurrentUserId(),
                        id,
                        statusComboBox.getSelectedItem().toString(),
                        1,
                        anime.episodes,
                        anime.title
                );

                episodeIndex.setText("Your Progress Episode Is On : " + addAnimeToDbRepository.getCurrentEpsByMalId(id));
                if (didSuccessToAddInDatabase) {
                    JOptionPane.showMessageDialog(null, "Berhasil Menambahkan Anime Ke Database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    button.setText("Save Changes");
                    statusComboBox.setVisible(true);
                    episodeIndex.setVisible(true);
                    addAnime.setVisible(true);
                    subtractAnime.setVisible(true);
                    addAnime.setVisible(true);
                    button.setVisible(true);
                }
                else JOptionPane.showMessageDialog(null, "Gagal Menambahkan Anime Ke Database", "Informasi", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        contaienrDiv.add(sectionUserInterface);

        return  contaienrDiv;
    }

    /**
     * Method untuk menghapus panel animePage
     */
    public static void removeContainer() {
        Controller.removeComponent(contaienrDiv);
    }

    /**
     * Method untuk membuat kotak 4 pilar
     * @param field an field
     * @param value value of field
     * @return component kotak yang biasanya terletak ada 4 kotak pada anime page
     */
    public static JPanel createKotak(String field, String value) {
        JPanel kotak = new JPanel();
        kotak.setOpaque(true);
        kotak.setPreferredSize(new Dimension(250, 80));
        kotak.setMaximumSize(new Dimension(Short.MAX_VALUE, 80)); // Menetapkan tinggi maksimum agar tidak dapat melebihi 80 piksel
        kotak.setBackground(Color.decode("#333b48"));
        kotak.setBorder(new LineBorder(Color.WHITE, 1));

        JLabel peringkatText = new JLabel(field);
        peringkatText.setOpaque(true);
        peringkatText.setPreferredSize(new Dimension(230, 25));
        peringkatText.setForeground(Color.WHITE);
        peringkatText.setBackground(Color.decode("#333b48"));
        peringkatText.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        peringkatText.setHorizontalAlignment(SwingConstants.CENTER);
//        peringkatText.setVerticalAlignment(SwingConstants.CENTER);

        JLabel valuePeringkat = new JLabel(value);
        valuePeringkat.setOpaque(true);
        valuePeringkat.setPreferredSize(new Dimension(230, 25));
        valuePeringkat.setForeground(Color.WHITE);
        valuePeringkat.setBackground(Color.decode("#333b48"));
        valuePeringkat.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        valuePeringkat.setHorizontalAlignment(SwingConstants.CENTER);

        kotak.add(peringkatText);
        kotak.add(valuePeringkat);


        return kotak;
    }

    /**
     * Method untuk mendapatkan digit pada text
     * @param text => data dari api
     * @return integer
     */
    public static int getDigitInThisText(String text) {
        String digit = "";
        char[] chars = text.toCharArray();

        for (char searchDigit : chars) {
            if (Character.isDigit(searchDigit)) {
                digit += "" + searchDigit;
            }
        }

        return Integer.parseInt(digit);
    }

}

package at.bbrz.buecherei;

import at.bbrz.buecherei.model.*;
import at.bbrz.buecherei.model.enums.Fsk;
import at.bbrz.buecherei.model.enums.Genere;
import at.bbrz.buecherei.model.enums.Zustand;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BuechereiUI extends JFrame {
    private List<Medium> medienListe;
    private JTable medienTable;
    private DefaultTableModel tableModel;

    public BuechereiUI() {
        medienListe = new ArrayList<>();
        initializeUI();
        createSampleData();
    }

    private void initializeUI() {
        setTitle("Bücherei Verwaltungssystem");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Hauptpanel mit BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Button-Panel oben
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        JButton addButton = new JButton("Medium hinzufügen");
        JButton deleteButton = new JButton("Medium löschen");
        JButton lendButton = new JButton("Ausleihen/Rückgabe");
        JButton showDetailsButton = new JButton("Details anzeigen");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(lendButton);
        buttonPanel.add(showDetailsButton);

        // Tabelle für Medien
        String[] columnNames = {"Inventar-Nr", "Titel", "Typ", "Genre", "Zustand", "Ausgeliehen"};
        tableModel = new DefaultTableModel(columnNames, 0);
        medienTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(medienTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Statusleiste unten
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel statusLabel = new JLabel("Bereit");
        statusPanel.add(statusLabel);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        // Event-Handler
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMediumDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMedium();
            }
        });

        lendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleLendStatus();
            }
        });

        showDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDetails();
            }
        });
    }

    private void addMediumDialog() {
        JDialog dialog = new JDialog(this, "Medium hinzufügen", true);
        dialog.setSize(500, 400);
        dialog.setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Buch-Panel
        JPanel buchPanel = createBuchPanel(dialog);
        tabbedPane.addTab("Buch", buchPanel);
        
        // Magazin-Panel
        JPanel magazinPanel = createMagazinPanel(dialog);
        tabbedPane.addTab("Magazin", magazinPanel);
        
        // DVD-Panel
        JPanel dvdPanel = createDVDPanel(dialog);
        tabbedPane.addTab("DVD", dvdPanel);
        
        // Schallplatte-Panel
        JPanel schallplattePanel = createSchallplattePanel(dialog);
        tabbedPane.addTab("Schallplatte", schallplattePanel);

        dialog.add(tabbedPane, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JPanel createBuchPanel(JDialog dialog) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JTextField invNrField = new JTextField();
        JTextField titleField = new JTextField();
        JComboBox<Genere> genreCombo = new JComboBox<>(Genere.values());
        JComboBox<Zustand> zustandCombo = new JComboBox<>(Zustand.values());
        JTextField isbnField = new JTextField();
        JTextField seitenField = new JTextField();
        JTextField klappenTextField = new JTextField();
        JTextField authorField = new JTextField();
        
        panel.add(new JLabel("Inventar-Nr:"));
        panel.add(invNrField);
        panel.add(new JLabel("Titel:"));
        panel.add(titleField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreCombo);
        panel.add(new JLabel("Zustand:"));
        panel.add(zustandCombo);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Seiten:"));
        panel.add(seitenField);
        panel.add(new JLabel("Klappentext:"));
        panel.add(klappenTextField);
        panel.add(new JLabel("Autor:"));
        panel.add(authorField);
        
        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Buch buch = new Buch(
                        invNrField.getText(),
                        titleField.getText(),
                        (Genere) genreCombo.getSelectedItem(),
                        (Zustand) zustandCombo.getSelectedItem(),
                        isbnField.getText(),
                        Integer.parseInt(seitenField.getText()),
                        klappenTextField.getText(),
                        new Author(authorField.getText())
                    );
                    medienListe.add(buch);
                    updateTable();
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Bitte geben Sie gültige Zahlenwerte ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(new JLabel());
        panel.add(saveButton);
        
        return panel;
    }

    private JPanel createMagazinPanel(JDialog dialog) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JTextField invNrField = new JTextField();
        JTextField titleField = new JTextField();
        JComboBox<Genere> genreCombo = new JComboBox<>(Genere.values());
        JComboBox<Zustand> zustandCombo = new JComboBox<>(Zustand.values());
        JTextField isbnField = new JTextField();
        JTextField seitenField = new JTextField();
        JTextField redaktionField = new JTextField();
        
        panel.add(new JLabel("Inventar-Nr:"));
        panel.add(invNrField);
        panel.add(new JLabel("Titel:"));
        panel.add(titleField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreCombo);
        panel.add(new JLabel("Zustand:"));
        panel.add(zustandCombo);
        panel.add(new JLabel("ISBN:"));
        panel.add(isbnField);
        panel.add(new JLabel("Seiten:"));
        panel.add(seitenField);
        panel.add(new JLabel("Redaktion:"));
        panel.add(redaktionField);
        
        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Magazin magazin = new Magazin(
                        invNrField.getText(),
                        titleField.getText(),
                        (Genere) genreCombo.getSelectedItem(),
                        (Zustand) zustandCombo.getSelectedItem(),
                        isbnField.getText(),
                        Integer.parseInt(seitenField.getText()),
                        redaktionField.getText()
                    );
                    medienListe.add(magazin);
                    updateTable();
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Bitte geben Sie gültige Zahlenwerte ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(new JLabel());
        panel.add(saveButton);
        
        return panel;
    }

    private JPanel createDVDPanel(JDialog dialog) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JTextField invNrField = new JTextField();
        JTextField titleField = new JTextField();
        JComboBox<Genere> genreCombo = new JComboBox<>(Genere.values());
        JComboBox<Zustand> zustandCombo = new JComboBox<>(Zustand.values());
        JTextField spielDauerField = new JTextField();
        JTextField teileField = new JTextField();
        JTextField regiseurField = new JTextField();
        JComboBox<Fsk> fskCombo = new JComboBox<>(Fsk.values());
        
        panel.add(new JLabel("Inventar-Nr:"));
        panel.add(invNrField);
        panel.add(new JLabel("Titel:"));
        panel.add(titleField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreCombo);
        panel.add(new JLabel("Zustand:"));
        panel.add(zustandCombo);
        panel.add(new JLabel("Spieldauer:"));
        panel.add(spielDauerField);
        panel.add(new JLabel("Teile:"));
        panel.add(teileField);
        panel.add(new JLabel("Regisseur:"));
        panel.add(regiseurField);
        panel.add(new JLabel("FSK:"));
        panel.add(fskCombo);
        
        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DVD dvd = new DVD(
                        invNrField.getText(),
                        titleField.getText(),
                        (Genere) genreCombo.getSelectedItem(),
                        (Zustand) zustandCombo.getSelectedItem(),
                        Integer.parseInt(spielDauerField.getText()),
                        Integer.parseInt(teileField.getText()),
                        regiseurField.getText(),
                        (Fsk) fskCombo.getSelectedItem()
                    );
                    medienListe.add(dvd);
                    updateTable();
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Bitte geben Sie gültige Zahlenwerte ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(new JLabel());
        panel.add(saveButton);
        
        return panel;
    }

    private JPanel createSchallplattePanel(JDialog dialog) {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        
        JTextField invNrField = new JTextField();
        JTextField titleField = new JTextField();
        JComboBox<Genere> genreCombo = new JComboBox<>(Genere.values());
        JComboBox<Zustand> zustandCombo = new JComboBox<>(Zustand.values());
        JTextField spielDauerField = new JTextField();
        JTextField teileField = new JTextField();
        JTextField liederField = new JTextField();
        
        panel.add(new JLabel("Inventar-Nr:"));
        panel.add(invNrField);
        panel.add(new JLabel("Titel:"));
        panel.add(titleField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreCombo);
        panel.add(new JLabel("Zustand:"));
        panel.add(zustandCombo);
        panel.add(new JLabel("Spieldauer:"));
        panel.add(spielDauerField);
        panel.add(new JLabel("Teile:"));
        panel.add(teileField);
        panel.add(new JLabel("Anzahl Lieder:"));
        panel.add(liederField);
        
        JButton saveButton = new JButton("Speichern");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Schallplatte schallplatte = new Schallplatte(
                        invNrField.getText(),
                        titleField.getText(),
                        (Genere) genreCombo.getSelectedItem(),
                        (Zustand) zustandCombo.getSelectedItem(),
                        Integer.parseInt(spielDauerField.getText()),
                        Integer.parseInt(teileField.getText()),
                        Integer.parseInt(liederField.getText())
                    );
                    medienListe.add(schallplatte);
                    updateTable();
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Bitte geben Sie gültige Zahlenwerte ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        panel.add(new JLabel());
        panel.add(saveButton);
        
        return panel;
    }

    private void deleteMedium() {
        int selectedRow = medienTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Medium aus.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this, 
                "Möchten Sie dieses Medium wirklich löschen?", 
                "Löschen bestätigen", 
                JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            medienListe.remove(selectedRow);
            updateTable();
        }
    }

    private void toggleLendStatus() {
        int selectedRow = medienTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Medium aus.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Medium medium = medienListe.get(selectedRow);
        if (medium.isAusgeliehen()) {
            // Medium zurückgeben
            medium.rueckgabe();
            JOptionPane.showMessageDialog(this, "Medium wurde zurückgegeben.", "Rückgabe", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Medium ausleihen
            medium.ausleihen();
            JOptionPane.showMessageDialog(this, "Medium wurde ausgeliehen.", "Ausleihe", JOptionPane.INFORMATION_MESSAGE);
        }
        updateTable();
    }

    private void showDetails() {
        int selectedRow = medienTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Bitte wählen Sie ein Medium aus.", "Hinweis", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Medium medium = medienListe.get(selectedRow);
        showDetailsWindow(medium);
    }

    private void showDetailsWindow(Medium medium) {
        JDialog detailsDialog = new JDialog(this, "Medium Details", true);
        detailsDialog.setSize(500, 400);
        detailsDialog.setLayout(new BorderLayout());
        detailsDialog.setLocationRelativeTo(this);

        // Textbereich mit Scroll-Pane
        JTextArea detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsTextArea.setText(medium.toString());

        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        detailsDialog.add(scrollPane, BorderLayout.CENTER);

        // Schließen-Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton closeButton = new JButton("Schließen");
        closeButton.addActionListener(e -> detailsDialog.dispose());
        buttonPanel.add(closeButton);

        detailsDialog.add(buttonPanel, BorderLayout.SOUTH);
        detailsDialog.setVisible(true);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Medium medium : medienListe) {
            tableModel.addRow(new Object[]{
                medium.getInventarNummer(),
                medium.getTitle(),
                medium.getType(),
                medium.getGenre(),
                medium.getZustand(),
                medium.isAusgeliehen() ? "Ja" : "Nein"
            });
        }
    }

    private void createSampleData() {
        // Beispiel-Daten für Demonstration
        Buch buch = new Buch(
            "B001", 
            "Der Herr der Ringe", 
            Genere.FANTASY, 
            Zustand.LEICHT_GEBRAUCHT, 
            "978-3608939812", 
            1216, 
            "Ein episches Fantasy-Abenteuer", 
            new Author("J.R.R. Tolkien")
        );
        
        DVD dvd = new DVD(
            "D001", 
            "Inception", 
            Genere.SCIFI, 
            Zustand.NEU, 
            148, 
            1, 
            "Christopher Nolan", 
            Fsk.FSK_12
        );
        
        medienListe.add(buch);
        medienListe.add(dvd);
        updateTable();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuechereiUI().setVisible(true);
            }
        });
    }
}
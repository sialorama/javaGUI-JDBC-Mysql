package standalone;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaisiePersonneGUI extends JFrame {
	
	private Connection connectDatabase() {
	    Connection connection = null;
	    try {
	        // Load the MySQL JDBC driver
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // Define connection URL
	        String url = "jdbc:mysql://localhost:3306/saisiepersonnedb"; // Replace with your DB name
	        String username = "root"; // Replace with your MySQL username
	        String password = ""; // Replace with your MySQL password

	        // Establish connection
	        connection = DriverManager.getConnection(url, username, password);
	        System.out.println("Database connected successfully!");
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	    return connection;
	}

    // Champs de saisie
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField ageField;

    // Zone d'affichage des informations
    private JTextArea infosArea;
    
    // Composant pour afficher l'image
    private JLabel imageLabel;

    public SaisiePersonneGUI() {
        // Configurer la fenêtre
        setTitle("Saisie de Personne");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Créer les composants de l'interface
        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        ageField = new JTextField(20);
        infosArea = new JTextArea(5, 30);
        infosArea.setEditable(false);
        
        // Composant pour afficher l'image
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 200));
        
        // Bouton pour afficher les informations
        JButton afficherButton = new JButton("Afficher Infos");
        afficherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherInfos();
            }
        });

        // Bouton pour uploader une image
        JButton uploadImageButton = new JButton("Uploader Image");
        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadImage();
            }
        });
        
        // Bouton pour réinitialiser les champs et l'image
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        
        // Bouton pour sauvegarder les champs et l'image
        JButton saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFields();
            }
        });
        
        // Utilisation de GridBagLayout
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Marges pour espacer les composants
        gbc.fill = GridBagConstraints.HORIZONTAL; // Pour que les composants remplissent l'espace horizontal
        
        // Ajouter les composants avec GridBagConstraints
        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 0; // Ligne 0
        panel.add(new JLabel("Nom:"), gbc);

        gbc.gridx = 1; // Colonne 1
        panel.add(nomField, gbc);

        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 1; // Ligne 1
        panel.add(new JLabel("Prénom:"), gbc);

        gbc.gridx = 1; // Colonne 1
        panel.add(prenomField, gbc);

        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 2; // Ligne 2
        panel.add(new JLabel("Âge:"), gbc);

        gbc.gridx = 1; // Colonne 1
        panel.add(ageField, gbc);

        gbc.gridx = 2; // Colonne 0
        gbc.gridy = 3; // Ligne 3
        gbc.gridwidth = 2; // Le bouton prendra deux colonnes
        panel.add(afficherButton, gbc);

        gbc.gridx = 3; // Colonne 2
        gbc.gridy = 4; // Ligne 4
        gbc.gridwidth = 1; // Reset bouton sur une colonne
        panel.add(resetButton, gbc);

        gbc.gridx = 2; // Colonne 2
        gbc.gridy = 4; // Ligne 4
        gbc.gridwidth = 1; // Save bouton sur une colonne
        panel.add(saveButton, gbc);

        gbc.gridx = 0; // Colonne 1
        gbc.gridy = 5; // Ligne 5
        gbc.gridwidth = 1; // Upload bouton sur une colonne
        panel.add(uploadImageButton, gbc);

        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 6; // Ligne 6
        gbc.gridwidth = 2; // La zone de texte prendra deux colonnes
        gbc.fill = GridBagConstraints.BOTH; // Remplit l'espace horizontal et vertical
        gbc.weightx = 1.0; // Donne du poids pour l'extension en largeur
        gbc.weighty = 1.0; // Donne du poids pour l'extension en hauteur
        panel.add(new JScrollPane(infosArea), gbc);

        gbc.gridx = 0; // Colonne 0
        gbc.gridy = 7; // Ligne 7
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(imageLabel, gbc);

        panel.setBorder(BorderFactory.createTitledBorder("Saisie d'informations")); 
        
        // Ajouter le panel à la fenêtre
        add(panel);
    }

    // Méthode pour afficher les informations
    private void afficherInfos() {
        try {
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int age = Integer.parseInt(ageField.getText());

            // Créer un objet Personne
            Personne personne = new Personne(nom, prenom, age);
            
            // Afficher les informations dans la zone de texte
            infosArea.setText("Nom : " + personne.getNom() + "\n" +
                              "Prénom : " + personne.getPrenom() + "\n" +
                              "Âge : " + personne.getAge() + " ans");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un âge valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode pour uploader une image
    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner une image");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filtrer les fichiers pour n'accepter que les images
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "png", "jpeg", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());

            // Redimensionner l'image pour s'adapter à la taille du JLabel
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        }
    }

    // Méthode pour réinitialiser les champs de texte et l'image
    private void resetFields() {
        nomField.setText("");
        prenomField.setText("");
        ageField.setText("");
        infosArea.setText("");
        imageLabel.setIcon(null); // Supprimer l'image
    }

    // Méthode pour sauvegarder les champs et l'image
    private void saveFields() {
        // Get the input data
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String ageStr = ageField.getText();

        // Check if fields are filled
        if (nom.isEmpty() || prenom.isEmpty() || ageStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);

            // Establish the database connection
            Connection conn = connectDatabase();
            if (conn != null) {
                // Create SQL query
                String query = "INSERT INTO personne (nom, prenom, age) VALUES (?, ?, ?)";

                // Use PreparedStatement to avoid SQL Injection
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, nom);
                stmt.setString(2, prenom);
                stmt.setInt(3, age);

                // Execute the insert query
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "Enregistrement réussi!", "Succès", JOptionPane.INFORMATION_MESSAGE);
                }

                // Close the connection
                conn.close();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer un âge valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement des données: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        // Créer et afficher l'interface graphique
        SwingUtilities.invokeLater(() -> {
            SaisiePersonneGUI frame = new SaisiePersonneGUI();
            frame.setVisible(true);
        });
    }
}

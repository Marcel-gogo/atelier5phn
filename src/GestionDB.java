import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class GestionDB {
    private Connection connection;

    public GestionDB() throws SQLException {
        this.connection = DatabaseSingleton.getInstance().getConnection();
    }

    public void createTable() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS livre ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nom VARCHAR(50), "
                + "type VARCHAR(10))" +
                "auteur VARCHAR(10)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'livre' created.");
        }
    }

    public void insertLivre(String nom, String type, String auteur) throws SQLException {
        String insertSQL = "INSERT INTO livre (nom, type, auteur) VALUES (?, ?, ?)" ;
        try (PreparedStatement pstmt =
                     connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, nom);
            pstmt.setString(2, type);
            pstmt.setString(3, auteur);
            pstmt.executeUpdate();
            System.out.println("livre inserted.");
        }
    }


    public void selectLivre() throws SQLException {
        String selectSQL = "SELECT * FROM livre" ;
        String nom;
        String type;
        String auteur;
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                nom = rs.getString("nom");
                type = rs.getString("type");
               auteur = rs.getString("auteur");


                System.out.println("ID: " + id + ", Nom: " + nom + ", type: " + type + ", auteur: "+ auteur );
            }

        }

    }

    public void updateLivre(int id, String nom, String auteur, String type)
            throws SQLException {
        String updateSQL =
                "UPDATE livre SET nom = ?, type = ?WHERE auteur = ?"
        ;
        try (PreparedStatement pstmt =
                     connection.prepareStatement(updateSQL)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, nom);
            pstmt.setString(3, auteur);
            pstmt.setString(4, type);

            pstmt.executeUpdate();
            System.out.println("Livre updated.");
        }
    }



    public void deleteLivre(int id) throws
            SQLException {
        String deleteSQL =
                "DELETE FROM livre WHERE id = ?";
        try (PreparedStatement pstmt =
                     connection.prepareStatement(deleteSQL)) {
            pstmt.setInt(1,
                    id);
            pstmt.executeUpdate();
            System.out.println("livre deleted.");
        }
    }
}


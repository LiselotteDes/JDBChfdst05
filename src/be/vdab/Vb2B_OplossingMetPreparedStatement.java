package be.vdab;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/*
Het probleem van SQL code injection los je op met een PreparedStatement.
Dit verhindert intern SQL code injection.
*/
public class Vb2B_OplossingMetPreparedStatement {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String UPDATE_PRIJS = "update planten set verkoopprijs = verkooopprijs * 1.1 where naam = ?";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(UPDATE_PRIJS)) {
                statement.setString(1, naam);
                System.out.println(statement.executeUpdate());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

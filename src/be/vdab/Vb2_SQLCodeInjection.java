package be.vdab;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
Het is een slechte gewoonte om een SQL statement met parameters te vervangen door 
een SQL statement waarin je stukken SQL concateneert met gebruikersinvoer.
*/
public class Vb2_SQLCodeInjection {
    private static final String URL = "jdbs:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    Statement statement = connection.createStatement()) {
                System.out.println(statement.executeUpdate(
                    "update planten set verkoopprijs = verkoopprijs * 1.1 where naam = '" 
                    // Concateneert stukken SQL met de gebruikersinvoer tot één SQL statement
                    + naam + "'"));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

package be.vdab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Vb1_PreparedStatement {
    private static final String URL = "jdbc:mysql://localhost/tuincentrum?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_LEVERANCIERS_VAN_EEN_WOONPLAATS = 
            "select naam from leveranciers where woonplaats  = ?";
    public static void main(String[] args) {
        /* (1)
        Scanner implementeert ook de interface AutoCloseable.
        Tip: je mag een Scanner(System.in) slechts één keer openen en sluiten. Zoniet krijg je een exception.
        */
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Woonplaats: ");
            String woonplaats = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    /* (2)
                    Geeft aan de Connection method prepareStatement een SQL statement mee.
                    Je krijgt een PreparedStatement object terug.
                    */
                    PreparedStatement statement = connection.prepareStatement(SELECT_LEVERANCIERS_VAN_EEN_WOONPLAATS)) {
                // (3) Vult de eerste (en hier enige) parameter met de waarde die de gebruiker intikte
                statement.setString(1, woonplaats);
                // (4) Voert het SQL statement uit dat je bij (1) meegaf
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getString("naam"));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

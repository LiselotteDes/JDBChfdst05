package be.vdab;
import java.math.BigDecimal;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Taak_BierenVanTotAlcohol {
    private static final String URL = "jdbc:mysql://localhost/bieren?useSSL=false";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SELECT_BIEREN_VAN_TOT_ALCOHOL = 
            "select alcohol, naam from bieren where alcohol between ? and ? order by alcohol, naam";
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Minimum alcohol%: ");
            BigDecimal min = scanner.nextBigDecimal();
            System.out.print("Maximum alcohol%: ");
            BigDecimal max = scanner.nextBigDecimal();
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(SELECT_BIEREN_VAN_TOT_ALCOHOL)) {
                statement.setBigDecimal(1, min);
                statement.setBigDecimal(2, max);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getBigDecimal("alcohol") + "\t" + resultSet.getString("naam"));
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

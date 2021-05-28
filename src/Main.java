import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String sqlCommand = "SELECT * FROM inv";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example", "root", "MaxMac7102")) {

            Statement statement = conn.createStatement();
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT* from Products where Price< ?");
//            preparedStatement.setInt(1,50000);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()){
                int id = resultSet.getInt("idINV");
                int idUser = resultSet.getInt("idUser");
                int price = resultSet.getInt("Cost");

                Products products = new Products();
                products.setId(id);
                products.setIdUser(idUser);
                products.setCost(price);

                System.out.println(products);
            }
//            int rows = statement.executeUpdate("INSERT Products(ProductName , Price) VALUES ('iPhone X',76000),('Samsung',45000)");
//            System.out.println(rows);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

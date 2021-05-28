import java.sql.*;
import java.util.Arrays;

public class Statements {

    public static void main(String[] args) {
        //Drop TABLE

        String sqlDrop = "DROP TABLE employee";

        //Create TABLE with columns(statement.execute)
        String sqlCreate = "CREATE TABLE EMPLOYEE"
                +"("
                +" id INT  NOT NULL AUTO_INCREMENT,"
                +"NAME VARCHAR(40) NOT NULL,"
                +"SALARY INT(15) NOT NULL,"
                +"AGE INT(3) NOT NULL ,"
                +"PRIMARY KEY(id))";


      ClassForStatements firstEmployee = new ClassForStatements("Max", 13000, 22);
      ClassForStatements secondEmployee = new ClassForStatements("Nastya", 8000, 20);
      ClassForStatements newSalary = new ClassForStatements();
      newSalary.setSalary(10000);

        //Insert a row
        String sqlInsert = "INSERT INTO employee (NAME, SALARY, AGE) VALUES ('" + firstEmployee.getName()+ "'," +
                "'"+firstEmployee.getSalary()+"','"+firstEmployee.getAge()+"') ";
        String sqlInsert2 = "INSERT INTO employee (NAME, SALARY, AGE) VALUES ('" + secondEmployee.getName()+ "'," +
                "'"+secondEmployee.getSalary()+"','"+secondEmployee.getAge()+"')";

        //UPDATE a row
        String sqlUpdate = "UPDATE employee SET SALARY='"+newSalary.getSalary()+"'" +
                "WHERE NAME='"+secondEmployee.getName()+"'";

        //Delete a row
        String sqlDelete = "DELETE FROM employee WHERE NAME='"+secondEmployee.getName()+"' ";

        //Select a row
        String sqlSelect = "SELECT * FROM employee";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/statement",
                "root", "MaxMac7102")) {
            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);

            statement.addBatch(sqlDrop);

            statement.addBatch(sqlCreate);

            statement.addBatch(sqlInsert);
            statement.addBatch(sqlInsert2);

            statement.addBatch(sqlUpdate);

            int[] rows = statement.executeBatch();
            System.out.println(Arrays.toString(rows));

            connection.commit();
//           ResultSet resultSet = statement.executeQuery(sqlSelect);
//           while (resultSet.next()){
//               int id = resultSet.getInt("ID");
//               String name = resultSet.getString("NAME");
//               int salary = resultSet.getInt("SALARY");
//               int age = resultSet.getInt("AGE");
//
//               ClassForStatements cfs = new ClassForStatements();
//               cfs.setId(id);
//               cfs.setName(name);
//               cfs.setSalary(salary);
//               cfs.setAge(age);
//
//               System.out.println(cfs);
//           }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

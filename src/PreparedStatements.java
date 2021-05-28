import java.sql.*;


public class PreparedStatements {
    public static void main(String[] args) {
        //Drop TABLE

//        String sqlDrop = "DROP TABLE employee";

        //Create TABLE with columns(statement.execute)
//        String sqlCreate = "CREATE TABLE EMPLOYEE"
//                +"("
//                +" id INT  NOT NULL AUTO_INCREMENT,"
//                +"NAME VARCHAR(40) NOT NULL,"
//                +"SALARY INT(15) NOT NULL,"
//                +"AGE INT(3) NOT NULL ,"
//                +"PRIMARY KEY(id))";


        ClassForStatements firstEmployee = new ClassForStatements();
        ClassForStatements secondEmployee = new ClassForStatements("Nastya", 8000, 20);
        ClassForStatements newSalary = new ClassForStatements();
        newSalary.setSalary(10000);

        //Insert a row
//        String sqlInsert = "INSERT INTO employee (NAME, SALARY, AGE) VALUES (?,?,?) ";
//        String sqlInsert2 = "INSERT INTO employee (NAME, SALARY, AGE) VALUES ('" + secondEmployee.getName() + "'," +
//                "'" + secondEmployee.getSalary() + "','" + secondEmployee.getAge() + "')";

        //UPDATE a row
//        String sqlUpdate = "UPDATE employee SET SALARY=? WHERE NAME=?";

        //Delete a row
//        String sqlDelete = "DELETE FROM employee WHERE ID=? ";

        //Select a row
       String sqlSelect = "SELECT * FROM employee";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/preparedstatement",
                "root", "MaxMac7102")) {
          PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery(sqlSelect);
           while (resultSet.next()){
               int id = resultSet.getInt("ID");
               String name = resultSet.getString("NAME");
               int salary = resultSet.getInt("SALARY");
               int age = resultSet.getInt("AGE");

               ClassForStatements cfs = new ClassForStatements();
               cfs.setId(id);
               cfs.setName(name);
               cfs.setSalary(salary);
               cfs.setAge(age);

               System.out.println(cfs);
           }
//          preparedStatement.setInt(1,3);
//          preparedStatement.executeUpdate();

//            preparedStatement.setInt(1,35000);
//            preparedStatement.setString(2,"Nastya");


//            preparedStatement.executeUpdate(sqlInsert2);
//            preparedStatement.setString(1, "Max");
//            preparedStatement.setInt(3, 21);
//            preparedStatement.setInt(2, 25000);
//
//            int row = preparedStatement.executeUpdate();
//            System.out.println(row);
        } catch (
                SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

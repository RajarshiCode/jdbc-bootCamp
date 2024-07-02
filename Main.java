import java.sql.*;


public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/jdbcplay";
    private static final String username = "root";
    private static final String password = "862086rd";
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }


        try{

            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            // String query = "select * from students";
//            String query = String.format("insert into students(name, age, marks) values('%s', %o, %f)", "Samrat",20, 98.5);
//            String query = String.format("update students set marks = %f where id = %o", 89.5, 3);
//            ResultSet resultSet = statement.executeQuery(query);
            String query = "delete from students where id = 3";
            int rowsAffected = statement.executeUpdate(query);

            if(rowsAffected>0)
                System.out.println("Data Inserted!");
            else
                System.out.println("Data Not Inserted!");
//            while(resultSet.next()){
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");;
//                int age = resultSet.getInt("age");;
//                double marks =   resultSet.getDouble("marks");
//                System.out.println("................");
//                System.out.println("ID : " + id);
//                System.out.println("NAME : " + name);
//                System.out.println("AGE : " + age);
//                System.out.println("MARS : " + marks);
//                System.out.println("................");
//            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

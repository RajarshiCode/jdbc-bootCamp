import java.util.Scanner;

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
            Scanner sc = new Scanner(System.in);
            Connection connection = DriverManager.getConnection(url,username,password);
            String query = "insert into students(name, age, marks) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            Statement statement = connection.createStatement();
//            String query = "insert into students(name, age, marks) values(?,?,?)";
            //String query = "select marks from students where id = ?";
//            String query = "update students set age = ? where id= ?";
            while(true)
            {
                System.out.print("Enter name : ");
                String name = sc.next();
                System.out.print("Enter age : ");
                int age = sc.nextInt();
                System.out.print("Enter marks : ");
                double marks = sc.nextDouble();
                System.out.print("Enter more data ? (Y/N) ");
                String choice = sc.next();
                //String query = String.format("insert into students(name, age, marks) values('%s', %o, %f)", name, age, marks);
                //String query = "insert into students(name, age, marks) values(?,?,?)";
                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);
                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("N"))
                    break;
            }

//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            //String query = "delete from students where id = ?";
//            // preparedStatement.setInt(1, 4); // id o. will get deleted.
//    //          preparedStatement.setInt(1,4);
//                preparedStatement.setInt(1,30);
//                preparedStatement.setInt(2,4);

//            preparedStatement.setString(1, "Archisman");
//            preparedStatement.setInt(2, 24);
//            preparedStatement.setDouble(3,82.6);
            //Statement statement = connection.createStatement();
            // String query = "select * from students";
//            String query = String.format("insert into students(name, age, marks) values('%s', %o, %f)", "Samrat",20, 98.5);
//            String query = String.format("update students set marks = %f where id = %o", 89.5, 3);
//            ResultSet resultSet = statement.executeQuery(query);
//            String query = "delete from students where id = 3";
            //int rowsAffected = preparedStatement.executeUpdate(query);

//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next())
//            {
//                //System.out.println("marks: " + resultSet.getDouble("marks"));
//                double marks = resultSet.getDouble("marks");
//                System.out.println("Marks : "+ marks);
//            }else{
//                System.out.println("Marks not found!");
//            }



            int[] arr =preparedStatement.executeBatch();
            for(int i = 0; i<arr.length; i++)
            {
                if(arr[i] == 0)
                    System.out.println("Query " + i + "not executed Sucessfully");
            }
//            if(arr>0)
//                System.out.println("Data Inserted!");
//            else
//                System.out.println("Data Not Inserted!");
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

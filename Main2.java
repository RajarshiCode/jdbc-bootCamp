import java.util.Scanner;

import java.sql.*;


public class Main2 {
    private static final String url = "jdbc:mysql://localhost:3306/lenden";
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
            connection.setAutoCommit(false);
            String debit_query = "update accounts set balance = balance - ? where account_number = ?" ;
            String credit_query = "update accounts set balance = balance + ? where account_number = ?" ;
            PreparedStatement debitpreparedStatement = connection.prepareStatement(debit_query);
            PreparedStatement creditpreparedStatement = connection.prepareStatement(credit_query);
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter account no. : ");
            int account_number = sc.nextInt();
            System.out.print("Enter amount : ");
            double amount = sc.nextDouble();
            debitpreparedStatement.setDouble(1,amount);
            debitpreparedStatement.setInt(2,account_number);
            creditpreparedStatement.setDouble(1,amount);
            creditpreparedStatement.setInt(2,501);
            debitpreparedStatement.executeUpdate();
            creditpreparedStatement.executeUpdate();


            if(isSufficient(connection, account_number, amount))
            {
//                int affectedRows1 = debitpreparedStatement.executeUpdate();
//                int affectedRows2 = creditpreparedStatement.executeUpdate();
                connection.commit();
                System.out.println("Transaction Successfull");
            }else{
                connection.rollback();

                System.out.println("Transaction Failed");
            }
            debitpreparedStatement.close();
            creditpreparedStatement.close();
            sc.close();
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    static  boolean isSufficient(Connection connection, int account_number, double amount){
        try{
            String query = "select balance from accounts where account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                double current_balance = resultSet.getDouble("balance");
//                return !(amount > current_balance || amount<0); simplfied version
                if(amount>current_balance || amount<0)
                    return false;
                else
                    return true;
            }
            resultSet.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());

        }
        return false;
    }
}

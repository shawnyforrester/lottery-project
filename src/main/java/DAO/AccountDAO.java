package DAO;

import Model.Account;
import Util.ConnectionSingleton;
import java.sql.*;


public class AccountDAO {

        public Account insertAccount(Account account) {
            Connection connection = ConnectionSingleton.getConnection();
            try {
                String sql = "INSERT INTO account(username, password) values ( ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, account.getUsername());
                preparedStatement.setString(2, account.getPassword());
    
                preparedStatement.executeUpdate();
                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if (pkeyResultSet.next()) {
                    int account_id = (int) pkeyResultSet.getLong(1);
                    return new Account(account_id, account.getUsername(), account.getPassword());
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return null; 
        }
    
    


    public Account processUsersLogin(Account account) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            //Write SQL logic here.
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int account_id = resultSet.getInt("account_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new Account(account_id, username, password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account processUserLogin(Account account) {
        return null;
    }
  


    public Account getAccountbyId (int account_id){
        Connection connection = ConnectionSingleton.getConnection();
        try {

            String sql = "SELECT account_id, username, password FROM account WHERE account_id =(?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);




            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),  rs.getString("username"),
                rs.getString("password"));
                return account;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteAccount(int account_id){
            Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "DELETE FROM UserAccounts WHERE account_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id );
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



}

package DAO;

import Model.Message;
import Model.Number;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Util.ConnectionUtil;

public class NumberDAO {

    public Number insertNumber(Number number) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "Insert INTO message(posted_by, message_text, time_posted_epoch) Values (?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, number.getPosted_by());
            preparedStatement.setString(2, number.getMessage_text());
            preparedStatement.setLong(3, number.getTime_posted_epoch());
            preparedStatement.executeUpdate();



            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if (pkeyResultSet.next()) {
                int message_id = (int) pkeyResultSet.getInt("message_id");
                return new Number(message_id, number.getPosted_by(),
                        number.getMessage_text(), number.getTime_posted_epoch());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Number> getAllMessage() {
        Connection connection = ConnectionUtil.getConnection();
        List<Number> numbers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Number number = new Number(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                numbers.add(number);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }

    public Number getMessageById(int message_id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Number numbers =  new Number (resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                return numbers;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }





    public Message deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Number> getAllMessagesByAccountId(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        List<Number> numbers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, posted_by);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Number number = new Number(resultSet.getInt("message_id"), resultSet.getInt("posted_by"),
                        resultSet.getString("message_text"), resultSet.getLong("time_posted_epoch"));
                numbers.add(number);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return numbers;
    }



    public Number updateMessageById(Number number, int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message set message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, number.getMessage_text());
            preparedStatement.setInt(2, message_id);
            preparedStatement.executeUpdate();
            return getMessageById(message_id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}


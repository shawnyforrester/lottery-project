package DAO;

import Model.Ticket;

import java.sql.*;


import Util.ConnectionSingleton;


public class TicketDAO {

    public void insertNumber(Ticket number) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "Insert INTO ticket(powerball) Values (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, number.getPowerball_numbers());
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public void getNewTicket(int account_id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            /*a ticket, represented by a string of numbers should be created here and then
             * inserted into the ticket table under the power ball column */
            PowerBall pb = new PowerBall();
            String newTicket = pb.generateNumbers();

            String sql1 = "INSERT INTO ticket (ticket_id, power ball) values (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql1);
            ps.setInt(1, account_id);
            ps.setString(2, newTicket);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Ticket retrieveTicket(int account_id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {

            String sql2 = "SELECT * from ticket WHERE ticket_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setInt(1, account_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket userTicket = new Ticket(resultSet.getInt("ticket_id"), resultSet.getString("power ball"));
                return userTicket;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteTicketbyId(int ticket_id) {
        Connection connection = ConnectionSingleton.getConnection();
        try {
            String sql = "DELETE FROM ticket WHERE ticket_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticket_id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


}

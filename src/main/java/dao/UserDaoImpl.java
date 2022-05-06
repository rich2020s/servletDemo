package dao;

import dbFactory.ConnectionManager;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    Connection connection;
    public UserDaoImpl() {
        connection = ConnectionManager.getConnection();
    }
    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users (first_name, last_name, email) VALUES (?, ?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirst_name());
            preparedStatement.setString(2, user.getLast_name());
            preparedStatement.setString(3, user.getEmail());
            int success = preparedStatement.executeUpdate();
            if (success == 0) {
                throw new SQLException("create new user failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
        return new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
        } catch (SQLException e) {
            System.out.println("Something went wrong when selecting user");
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public ArrayList<User> getAllUsers(){
        String sql = "SELECT * FROM users";
        ArrayList<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when selecting all users");
        }
        return users;
    }
}

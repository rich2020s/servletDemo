package dao;

import entity.User;

import java.util.ArrayList;

public interface UserDao {
    public boolean create(User user);
    public User getUser(int id);
    public ArrayList<User> getAllUsers();
}

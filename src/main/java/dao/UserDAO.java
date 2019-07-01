package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    public void createUser (User user);
    public void editUser(User user);
    public void removeUser(int id);
    public List<User> listAllUsers();
    public User getUserById (int id);
}

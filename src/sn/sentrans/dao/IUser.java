package sn.sentrans.dao;

import sn.sentrans.entities.TypeLocalite;
import sn.sentrans.entities.User;

import java.util.List;

public interface IUser {

    public int add(User user);
    public List<User> getAll();
    public int update(User user);
    public int delete(int id);
    public User get(int id);

    public User getLogin(String email, String password);
}

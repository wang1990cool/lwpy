package edu.ahut.dao;

import edu.ahut.entity.User;

import java.util.List;

/**
 * Created by win10 on 2017/1/3.
 */
public interface UserDAO {
    public int save(User u);
    public List<User> findAll();
}

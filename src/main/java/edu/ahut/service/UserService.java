package edu.ahut.service;

import edu.ahut.entity.User;

import java.util.List;

/**
 * Created by win10 on 2017/1/3.
 */
public interface UserService {
    public void saveUsers(List<User> us);
    public List<User> getAllUsernames();
}

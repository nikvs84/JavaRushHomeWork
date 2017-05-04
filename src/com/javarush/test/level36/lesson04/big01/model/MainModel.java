package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 30.11.2016.
 */
public class MainModel implements Model {
    ModelData modelData = new ModelData();
    UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        List<User> users = getActiveUsers();
        modelData.setUsers(users);
    }

    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        User user = userService.getUsersById(id);
        user.setName(name);
        user.setLevel(level);
    }

    private List<User> getAllUsers() {
        return userService.getUsersBetweenLevels(1, 100);
    }

    private List<User> getActiveUsers() {
        return userService.filterOnlyActiveUsers(getAllUsers());
    }

}

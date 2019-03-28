package pku.chaoqun.logindemo.service;

import pku.chaoqun.logindemo.model.User;

public interface UserService {
    void saveUser(User user);
    User getUserByName(String username);
}

package pku.chaoqun.logindemo.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pku.chaoqun.logindemo.model.User;
import pku.chaoqun.logindemo.repository.UserRepository;
import pku.chaoqun.logindemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public User getUserByName(String username) {
        return repository.findUserByUsername(username);
    }
}

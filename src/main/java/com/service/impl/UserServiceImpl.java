package com.service.impl;

import com.model.User;
import com.repository.UserRepository;
import com.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ben Yasin
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;

    @Override
    public User findById(ObjectId id) {
        return userRepository.findOne(id);
    }

    @Override
    public User deleteById(ObjectId id) {
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public long countUser() {
        return userRepository.count();
    }
}

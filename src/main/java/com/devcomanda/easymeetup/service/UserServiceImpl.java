package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Aleksandr Beryozkin
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }
}

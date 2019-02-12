package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.model.entity.User;

/**
 * @author Aleksandr Beryozkin
 */

public interface UserService {

    User findUserById(Long id);
}

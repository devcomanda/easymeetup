package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.configs.constants.AuthorityConstants;
import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.entity.User;
import com.devcomanda.easymeetup.entity.exceptions.InvalidPasswordException;
import com.devcomanda.easymeetup.entity.exceptions.UserExistsException;
import com.devcomanda.easymeetup.repository.UserRepository;
import com.devcomanda.easymeetup.repository.security.AuthorityRepository;
import com.devcomanda.easymeetup.utils.RandomUtil;
import com.devcomanda.easymeetup.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public User saveUser(final NewUserRequest userReq, boolean isActive) {
        if (!SecurityUtils.checkPasswordLength(userReq.getPassword())){
            throw new InvalidPasswordException("Invalid Password.");
        }

        this.userRepository.findByEmail(userReq.getEmail())
                .ifPresent(user -> {
                    throw new UserExistsException("User with " +userReq.getEmail() +"already exists");
                });

        final User user = new User();
        user.setEmail(userReq.getEmail());
        user.setPassword(userReq.getPassword());

        this.authorityRepository.findById(AuthorityConstants.ROLE_USER)
                .ifPresent(user::addAuthorities);

        if (userReq.getPassword() == null || userReq.getPassword().isEmpty()){
            final String rawPassword = RandomUtil.generatePassword();
            final String encryptedPassword = this.passwordEncoder.encode(rawPassword);
            user.setPassword(encryptedPassword);
        }
        else {
            final String encryptedPassword = this.passwordEncoder.encode(userReq.getPassword());
            user.setPassword(encryptedPassword);
        }

        if (isActive){
            user.setActivated(true);
        }
        else {
            user.setActivated(false);
            user.setActivationKey(RandomUtil.generateActivationKey());
        }
        return this.userRepository.save(user);
    }
}

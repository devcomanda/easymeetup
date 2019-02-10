package com.devcomanda.easymeetup.repository;

import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.factories.AuthoritiesFactory;
import com.devcomanda.easymeetup.factories.UsersFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql("classpath:/datasets/users/firstUser.sql")
    @Sql("classpath:/datasets/authorities/userAuthority.sql")
    public void shouldReturnUserById() {

        Optional<User> user = this.userRepository.findById(UsersFactory.FIRST_USER_ID);

        assertThat(user).isPresent();

        User actual = user.get();

        assertThat(actual).isEqualTo(
                UsersFactory.firstUser()
        );

        actual.hasAuthority(AuthoritiesFactory.USER_AUTHORITY_NAME);
    }

    @Test
    @Sql("classpath:/datasets/users/firstUser.sql")
    @Sql("classpath:/datasets/authorities/userAuthority.sql")
    public void shouldReturnUserByEmail(){

        Optional<User> user = this.userRepository.findByEmail(UsersFactory.FIRST_USER_EMAIL);

        assertThat(user).isPresent();

        User actual = user.get();

        assertThat(actual).isEqualTo(
                UsersFactory.firstUser()
        );

        actual.hasAuthority(AuthoritiesFactory.USER_AUTHORITY_NAME);
    }
}

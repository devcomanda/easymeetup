package com.devcomanda.easymeetup.repository;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.factories.MeetupsFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MeetupRepositoryIntTest {


    @Autowired
    private MeetupRepository meetupRepository;

    @Test
    @Sql("classpath:/datasets/meetups/twoMeetups.sql")
    public void shouldReturnMeetups() {

        List<Meetup> meetups = meetupRepository.findAll();

        assertThat(meetups)
                .isNotNull()
                .containsSequence(
                        MeetupsFactory.firstMeetup(),
                        MeetupsFactory.secondMeetup()
                );
    }
}

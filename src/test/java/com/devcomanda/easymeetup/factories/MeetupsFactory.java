package com.devcomanda.easymeetup.factories;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public final class MeetupsFactory {
    private MeetupsFactory() {}

    public static final Long FIRST_MEETUP_ID = 1L;

    public static final String FIRST_MEETUP_NAME = "Java for sceptics";
    public static final String FIRST_MEETUP_ADDRESS = "First Meetup Place";
    public static final LocalDateTime FIRST_MEETUP_START_DATE = LocalDateTime.of(2019, 01, 19, 21, 05, 13);
    public static final LocalDateTime FIRST_MEETUP_END_DATE = LocalDateTime.of(2019, 01, 19, 22, 05, 13);
    public static final String FIRST_MEETUP_DESCRIPTION = "This is first meetup.";
    public static final String FIRST_MEETUP_SPEAKER = "First speaker";
    public static final List<User> FIRST_USERS = Arrays.asList(UsersFactory.firstUser(), UsersFactory.secondUser());

    public static final Long SECOND_MEETUP_ID = 2L;
    public static final String SECOND_MEETUP_NAME = "Java approach in Chemistry";
    public static final String SECOND_MEETUP_ADDRESS = "Second Meetup Place";
    public static final LocalDateTime SECOND_MEETUP_START_DATE = LocalDateTime.of(2019, 01, 20, 21, 05, 13);
    public static final LocalDateTime SECOND_MEETUP_END_DATE = LocalDateTime.of(2019, 01, 20, 22, 05, 13);
    public static final String SECOND_MEETUP_DESCRIPTION = "This is second meetup.";
    public static final String SECOND_MEETUP_SPEAKER = "Second speaker";
    public static List<User> SECOND_USERS = Arrays.asList(UsersFactory.secondUser());
    public static ArrayList<User> NEW_SECOND_USERS = new ArrayList<>();


    public static Meetup newFirstMeetup(){
        return Meetup
                .builder()
                .id(FIRST_MEETUP_ID)
                .name(FIRST_MEETUP_NAME)
                .address(FIRST_MEETUP_ADDRESS)
                .startDate(FIRST_MEETUP_START_DATE)
                .endDate(FIRST_MEETUP_END_DATE)
                .description(FIRST_MEETUP_DESCRIPTION)
                .speaker(FIRST_MEETUP_SPEAKER)
                .build();
    }

    public static Meetup firstMeetup() {
        return Meetup
                .builder()
                .id(FIRST_MEETUP_ID)
                .name(FIRST_MEETUP_NAME)
                .address(FIRST_MEETUP_ADDRESS)
                .startDate(FIRST_MEETUP_START_DATE)
                .endDate(FIRST_MEETUP_END_DATE)
                .description(FIRST_MEETUP_DESCRIPTION)
                .speaker(FIRST_MEETUP_SPEAKER)
                .users(FIRST_USERS)
                .build();
    }

    public static Meetup secondMeetup() {
        return Meetup
                .builder()
                .id(SECOND_MEETUP_ID)
                .name(SECOND_MEETUP_NAME)
                .address(SECOND_MEETUP_ADDRESS)
                .startDate(SECOND_MEETUP_START_DATE)
                .endDate(SECOND_MEETUP_END_DATE)
                .description(SECOND_MEETUP_DESCRIPTION)
                .speaker(SECOND_MEETUP_SPEAKER)
                .users(NEW_SECOND_USERS)
                .build();
    }

    public static Meetup newSecondMeetup(){
        NEW_SECOND_USERS.add(UsersFactory.firstUser());
        NEW_SECOND_USERS.add(UsersFactory.secondUser());

        return Meetup
                .builder()
                .id(SECOND_MEETUP_ID)
                .name(SECOND_MEETUP_NAME)
                .address(SECOND_MEETUP_ADDRESS)
                .startDate(SECOND_MEETUP_START_DATE)
                .endDate(SECOND_MEETUP_END_DATE)
                .description(SECOND_MEETUP_DESCRIPTION)
                .speaker(SECOND_MEETUP_SPEAKER)
                .users(NEW_SECOND_USERS)
                .build();
    }

    public static Meetup firstRepoIntTestMeetup(){
        return Meetup
                .builder()
                .id(FIRST_MEETUP_ID)
                .name(FIRST_MEETUP_NAME)
                .address(FIRST_MEETUP_ADDRESS)
                .startDate(FIRST_MEETUP_START_DATE)
                .endDate(FIRST_MEETUP_END_DATE)
                .description(FIRST_MEETUP_DESCRIPTION)
                .speaker(FIRST_MEETUP_SPEAKER)
                .build();
    }

    public static Meetup secondRepoIntTestMeetup(){
        return Meetup
                .builder()
                .id(SECOND_MEETUP_ID)
                .name(SECOND_MEETUP_NAME)
                .address(SECOND_MEETUP_ADDRESS)
                .startDate(SECOND_MEETUP_START_DATE)
                .endDate(SECOND_MEETUP_END_DATE)
                .description(SECOND_MEETUP_DESCRIPTION)
                .speaker(SECOND_MEETUP_SPEAKER)
                .build();
    }

}

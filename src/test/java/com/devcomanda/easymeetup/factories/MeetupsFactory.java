package com.devcomanda.easymeetup.factories;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.enums.Status;

import java.time.LocalDateTime;

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
    public static final Status NEW_MEETUP_STATUS = Status.fromText("NEW");


    public static final Long SECOND_MEETUP_ID = 2L;
    public static final String SECOND_MEETUP_NAME = "Java approach in Chemistry";
    public static final String SECOND_MEETUP_ADDRESS = "Second Meetup Place";
    public static final LocalDateTime SECOND_MEETUP_START_DATE = LocalDateTime.of(2019, 01, 20, 21, 05, 13);
    public static final LocalDateTime SECOND_MEETUP_END_DATE = LocalDateTime.of(2019, 01, 20, 22, 05, 13);
    public static final String SECOND_MEETUP_DESCRIPTION = "This is second meetup.";
    public static final String SECOND_MEETUP_SPEAKER = "Second speaker";
    public static final Status IN_PROGRESS_STATUS = Status.fromText("IN_PROGRESS");


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
                .status(NEW_MEETUP_STATUS)
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
                .status(NEW_MEETUP_STATUS)
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
                .status(NEW_MEETUP_STATUS)
                .build();
    }

}

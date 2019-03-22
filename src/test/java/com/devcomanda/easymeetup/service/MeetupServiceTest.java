package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.factories.MeetupsFactory;
import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.model.entity.exceptions.MeetupNotFoundException;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MeetupServiceTest {

    @Mock
    private MeetupRepository meetupRepository;

    private MeetupService meetupService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        meetupService = new MeetupServiceImpl(meetupRepository);
    }

    @Test
    public void shouldSaveNewMeetup() {
        Meetup firstMeetup = MeetupsFactory.firstMeetup();

        when(meetupRepository.save(any(Meetup.class)))
                .thenReturn(firstMeetup);

        Meetup meetup = meetupService.saveMeetup(MeetupsFactory.newFirstMeetup());

        assertThat(meetup).isNotNull()
                .isEqualTo(firstMeetup)
                .isEqualToComparingFieldByField(firstMeetup);
    }


    @Test
    public void shouldReturnMeetupById() {
        Meetup firstMeetup = MeetupsFactory.firstMeetup();

        when(meetupRepository.findById(MeetupsFactory.FIRST_MEETUP_ID))
                .thenReturn(Optional.of(firstMeetup));

        Meetup meetup = meetupService.loadMeetup(MeetupsFactory.FIRST_MEETUP_ID);

        assertThat(meetup)
                .isEqualTo(firstMeetup)
                .isEqualToComparingFieldByField(firstMeetup);
    }

    @Test(expected = MeetupNotFoundException.class)
    public void shouldThrowExceptionIfMeetupNotFound() {

        when(meetupRepository.findById(MeetupsFactory.FIRST_MEETUP_ID))
                .thenReturn(Optional.empty());

        meetupService.loadMeetup(MeetupsFactory.FIRST_MEETUP_ID);
    }

    @Test
    public void shouldReturnMeetups() {

        final Meetup meetup = MeetupsFactory.firstMeetup();

        when(this.meetupRepository.findAll())
                .thenReturn(Collections.singletonList(meetup));

        List<Meetup> meetups = this.meetupService.loadMeetups();

        assertThat(meetups)
                .isNotNull()
                .hasSize(1)
                .containsExactly(meetup);
    }

    @Test
    public void shouldUpdateMeetup() {

        final Meetup spyMeetup = spy(MeetupsFactory.firstMeetup());

        when(this.meetupRepository.findById(anyLong()))
                .thenReturn(Optional.of(spyMeetup));

        Meetup changedMeetup = MeetupsFactory.secondMeetup();

        Meetup updatedMeetup = this.meetupService.updateMeetup(changedMeetup);

        assertThat(updatedMeetup).isNotNull();

        verify(spyMeetup).setAddress(eq(MeetupsFactory.SECOND_MEETUP_ADDRESS));
        verify(spyMeetup).setName(eq(MeetupsFactory.SECOND_MEETUP_NAME));
        verify(spyMeetup).setDescription(eq(MeetupsFactory.SECOND_MEETUP_DESCRIPTION));

        verify(spyMeetup).setSpeaker(eq(MeetupsFactory.SECOND_MEETUP_SPEAKER));

        verify(spyMeetup).setStartDate(eq(MeetupsFactory.SECOND_MEETUP_START_DATE));
        verify(spyMeetup).setEndDate(eq(MeetupsFactory.SECOND_MEETUP_END_DATE));

    }

    @Test(expected = MeetupNotFoundException.class)
    public void shouldThrowExceptionIfUpdatedMeetupNotFound() {

        when(meetupRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        meetupService.updateMeetup(MeetupsFactory.secondMeetup());
    }

    @Test
    public void shouldReturnUserHistoryMeetups(){
        User firstUser = UsersFactory.firstUser();

        Meetup firstMeetup = MeetupsFactory.firstMeetup();

        List<Meetup> metups = new ArrayList<>();
        metups.add(firstMeetup);

        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);

        Mockito.when(auth.getPrincipal()).thenReturn(firstUser);

        Mockito.when(meetupService.loadUserMeetupHistory())
                .thenReturn(metups);

        List<Meetup> history = meetupService.loadUserMeetupHistory();

        assertThat(history.get(0)).isEqualTo(metups.get(0));
    }

}

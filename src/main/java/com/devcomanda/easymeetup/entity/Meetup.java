package com.devcomanda.easymeetup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Meetup {
    @Id
    @GeneratedValue
    private Long id;
    private String meetupName;
    private String meetupAddress;
    private LocalDateTime meetupDate;
    private String meetupDescription;
    private String meetupSpeaker;

    public Meetup(){

    }

    public Meetup(String meetupName, String meetupAddress, LocalDateTime meetupDate,
                  String meetupDescription, String meetupSpeaker) {
        this.meetupName = meetupName;
        this.meetupAddress = meetupAddress;
        this.meetupDate = meetupDate;
        this.meetupDescription = meetupDescription;
        this.meetupSpeaker = meetupSpeaker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetupName() {
        return meetupName;
    }

    public void setMeetupName(String meetupName) {
        this.meetupName = meetupName;
    }

    public String getMeetupAddress() {
        return meetupAddress;
    }

    public void setMeetupAddress(String meetupAddress) {
        this.meetupAddress = meetupAddress;
    }

    public String getMeetupSpeaker() {
        return meetupSpeaker;
    }

    public void setMeetupSpeaker(String meetupSpeaker) {
        this.meetupSpeaker = meetupSpeaker;
    }

    public LocalDateTime getMeetupDate() {
        return meetupDate;
    }

    public void setMeetupDate(LocalDateTime meetupDate) {
        this.meetupDate = meetupDate;
    }

    public String getMeetupDescription() {
        return meetupDescription;
    }

    public void setMeetupDescription(String meetupDescription) {
        this.meetupDescription = meetupDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meetup)) return false;
        Meetup meetup = (Meetup) o;
        return Objects.equals(id, meetup.id) &&
                Objects.equals(meetupName, meetup.meetupName) &&
                Objects.equals(meetupAddress, meetup.meetupAddress) &&
                Objects.equals(meetupDate, meetup.meetupDate) &&
                Objects.equals(meetupDescription, meetup.meetupDescription) &&
                Objects.equals(meetupSpeaker, meetup.meetupSpeaker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, meetupName, meetupAddress, meetupDate, meetupDescription, meetupSpeaker);
    }

    @Override
    public String toString() {
        return "Meetup{" +
                "id=" + id +
                ", meetupName='" + meetupName + '\'' +
                ", meetupAddress='" + meetupAddress + '\'' +
                ", meetupDate=" + meetupDate +
                ", meetupDescription='" + meetupDescription + '\'' +
                ", meetupSpeaker='" + meetupSpeaker + '\'' +
                '}';
    }
}

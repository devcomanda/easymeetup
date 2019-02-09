package com.devcomanda.easymeetup.repository;

import com.devcomanda.easymeetup.model.entity.Meetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRepository extends JpaRepository<Meetup, Long> {
}

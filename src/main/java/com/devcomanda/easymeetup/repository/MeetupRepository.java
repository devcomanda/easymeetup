package com.devcomanda.easymeetup.repository;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface MeetupRepository extends JpaRepository<Meetup, Long> {

    @Query(value = "select * from meetup p where p.start_date <:date join user_meetup b on p.id = b.meetup_id join user u" +
            "on b.id=b.user_id where b.emai =:email ", nativeQuery = true)
    List<Meetup> findUserMeetupsBeforeCurrentDate(@Param("email") String email,
                                                 @Param("date") LocalDate date);

}

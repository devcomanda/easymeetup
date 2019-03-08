package com.devcomanda.easymeetup.model.entity;

import com.devcomanda.easymeetup.model.entity.enums.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "meetup")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
// TODO We should use custom settings for sequence configuration
// because we use sql files for setting dev data
public class Meetup extends AbstractPersistable<Long> {

    private String name;
    private String address;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;
    private String description;
    private String speaker;

    @Enumerated (EnumType.STRING)
    private Status status;

    @Builder
    private Meetup(
            final Long id,
            final String name,
            final String address,
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final String description,
            final String speaker,
            final Status status
    ) {
        super();
        super.setId(id);

        this.name = name;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.speaker = speaker;
        this.status = status;
    }
}

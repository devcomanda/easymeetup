package com.devcomanda.easymeetup.model.entity;

import com.devcomanda.easymeetup.model.entity.enums.AuthProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@ToString
// TODO We should use custom settings for sequence configuration
// because we use sql files for setting dev data
public class User extends AbstractPersistable<Long> {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;

    @JsonIgnore
    private Boolean activated;

    private String activationKey;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "users_authority",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "authority", referencedColumnName = "name")
    )
    private Set<Authority> authorities = new HashSet<>();

    public User(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    @Builder
    private User(
        final String email,
        final String password,
        final AuthProvider authProvider,
        final String providerId
    ) {

        this.email = email;
        this.password = password;
        this.provider = authProvider;
        this.providerId = providerId;
    }

    public User(String email, String password, AuthProvider provider, String providerId,
                Boolean activated,
                String activationKey) {
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.activated = activated;
        this.activationKey = activationKey;
    }

    // sync methods
    public void addAuthorities(final Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthorities(final Authority authority) {
        this.authorities.remove(authority);
    }

    public boolean hasAuthority(final String name) {
        return this.authorities.contains(new Authority(name));
    }

    public Set<Authority> authorities() {
        return Collections.unmodifiableSet(this.authorities);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return Objects.equals(this.email, user.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (this.email != null ? this.email.hashCode() : 0);
        return result;
    }

}
package com.devcomanda.easymeetup.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(AccessLevel.PACKAGE)
@ToString
public class User extends AbstractPersistable<Long> {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String password;

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

    // sync methods
    public void addAuthorities(Authority authority) {
        this.authorities.add(authority);
    }

    public void removeAuthorities(Authority authority) {
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
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

}

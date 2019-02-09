package com.devcomanda.easymeetup.repository.security;

import com.devcomanda.easymeetup.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}

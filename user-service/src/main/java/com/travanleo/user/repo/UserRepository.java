package com.travanleo.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travanleo.user.domain.User;

/**
 * The interface User repository.
 *
 * @author Saju
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

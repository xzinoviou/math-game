package com.xzinoviou.socialmultiplication.repository;

import com.xzinoviou.socialmultiplication.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Xenofon Zinoviou
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByAlias(final String alias);
}

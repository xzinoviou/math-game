package com.xzinoviou.socialmultiplication.repository;

import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Xenofon Zinoviou
 */
public interface MultiplicationResultAttemptRepository
    extends JpaRepository<MultiplicationResultAttempt, Long> {

  List<MultiplicationResultAttempt> findTop5ByUserAliasOrderByIdDesc(String userAlias);

}

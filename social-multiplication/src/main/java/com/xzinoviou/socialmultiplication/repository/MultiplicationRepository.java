package com.xzinoviou.socialmultiplication.repository;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Xenofon Zinoviou
 */
public interface MultiplicationRepository extends JpaRepository<Multiplication, Long> {
}

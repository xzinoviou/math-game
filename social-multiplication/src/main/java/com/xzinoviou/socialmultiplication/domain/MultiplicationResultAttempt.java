package com.xzinoviou.socialmultiplication.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author : Xenofon Zinoviou
 */

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class MultiplicationResultAttempt {

  @Id
  @GeneratedValue
  private Long id;

  @JoinColumn(name = "USER_ID")
  @ManyToOne(cascade = CascadeType.PERSIST)
  private final User user;

  @JoinColumn(name = "MULTIPLICATION_ID")
  @ManyToOne(cascade = CascadeType.PERSIST)


  private final Multiplication multiplication;
  private final int resultAttempt;
  private final boolean correct;

  //Empty constructor for JSON (de)serialization
  public MultiplicationResultAttempt() {
    user = null;
    multiplication = null;
    resultAttempt = -1;
    correct = false;
  }
}

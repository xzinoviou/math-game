package com.xzinoviou.socialmultiplication.service;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import java.util.List;

/**
 * @author : Xenofon Zinoviou
 */
public interface MultiplicationService {

  /**
   * Creates a random {@link Multiplication} object.
   *
   * @return a multiplication of randomly generated numbers.
   */
  Multiplication createRandomMultiplication();

  /**
   * @param resultAttempt the given resultAttempt.
   * @return return true if the given attempt matches the result of the multiplication,
   * false otherwise.
   */
  boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

  /**
   * Gets the statistics for a given user.
   *
   * @param userAlias the user's alias
   * @return a list of {@link MultiplicationResultAttempt} objects, being the past attempts of the user.
   */
  List<MultiplicationResultAttempt> getStatsForUser(String userAlias);
}

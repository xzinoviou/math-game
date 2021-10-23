package com.xzinoviou.socialmultiplication.service;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import com.xzinoviou.socialmultiplication.domain.User;
import com.xzinoviou.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import com.xzinoviou.socialmultiplication.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author : Xenofon Zinoviou
 */
@Service
public class MultiplicationServiceImpl implements MultiplicationService {

  private final RandomGeneratorService randomGeneratorService;

  private final MultiplicationResultAttemptRepository attemptRepository;

  private final UserRepository userRepository;

  public MultiplicationServiceImpl(
      RandomGeneratorService randomGeneratorService,
      MultiplicationResultAttemptRepository attemptRepository,
      UserRepository userRepository) {
    this.randomGeneratorService = randomGeneratorService;
    this.attemptRepository = attemptRepository;
    this.userRepository = userRepository;
  }

  @Override
  public Multiplication createRandomMultiplication() {
    int a = randomGeneratorService.generateRandomFactor();
    int b = randomGeneratorService.generateRandomFactor();
    return new Multiplication(a, b);
  }

  @Override
  public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
    Assert.isTrue(!resultAttempt.isCorrect(), "Invalid supplied value");

    Optional<User> user = userRepository.findByAlias(resultAttempt.getUser().getAlias());

    boolean correct =
        resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA() *
            resultAttempt.getMultiplication().getFactorB();


    MultiplicationResultAttempt checkedAttempt =
        getCheckedResultAttempt(user.orElse(resultAttempt.getUser()), resultAttempt, correct);
    attemptRepository.save(checkedAttempt);

    return correct;
  }

  @Override
  public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
    return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
  }

  private MultiplicationResultAttempt getCheckedResultAttempt(User user,
                                                              MultiplicationResultAttempt resultAttempt,
                                                              boolean correct) {
    return new MultiplicationResultAttempt(
        user, resultAttempt.getMultiplication(),
        resultAttempt.getResultAttempt(), correct);
  }
}

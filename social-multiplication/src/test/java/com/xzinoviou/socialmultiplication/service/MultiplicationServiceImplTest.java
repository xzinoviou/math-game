package com.xzinoviou.socialmultiplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import com.xzinoviou.socialmultiplication.domain.User;
import com.xzinoviou.socialmultiplication.event.EventDispatcher;
import com.xzinoviou.socialmultiplication.event.MultiplicationSolvedEvent;
import com.xzinoviou.socialmultiplication.repository.MultiplicationResultAttemptRepository;
import com.xzinoviou.socialmultiplication.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author : Xenofon Zinoviou
 */
@ExtendWith(MockitoExtension.class)
class MultiplicationServiceImplTest {

  private final static String USER_ALIAS = "John Dough";

  @Mock
  private RandomGeneratorService randomGeneratorService;

  @Mock
  private MultiplicationResultAttemptRepository attemptRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private EventDispatcher eventDispatcher;

  private MultiplicationServiceImpl testClass;

  @BeforeEach
  void setUp() {
    /**
     * Workaround instead of injecting mocks into class under test.
     */
    MockitoAnnotations.openMocks(this);

    testClass =
        new MultiplicationServiceImpl(
            randomGeneratorService,
            attemptRepository,
            userRepository,
            eventDispatcher);

  }

  @Test
  void testWhenCreateRandomMultiplication_IsNotNull() {
    int a = 5;
    int b = 10;

    when(randomGeneratorService.generateRandomFactor())
        .thenReturn(a).thenReturn(b);

    Multiplication result = testClass.createRandomMultiplication();

    assertNotNull(result);
    assertEquals(a, result.getFactorA());
    assertEquals(b, result.getFactorB());
  }

  @Test
  void testValidAttemptResult() {
    Multiplication multiplication = getMultiplication(50, 60);
    User user = getUser();

    MultiplicationResultAttempt attempt =
        getMultiplicationResultAttempt(multiplication, user, 3000, false);

    MultiplicationResultAttempt verifiedAttempt =
        getMultiplicationResultAttempt(multiplication, user, 3000, true);
    MultiplicationSolvedEvent event = getMultiplicationSolvedEvent(verifiedAttempt);

    when(userRepository.findByAlias(USER_ALIAS)).thenReturn(Optional.of(user));

    boolean result = testClass.checkAttempt(attempt);

    assertTrue(result);
    verify(attemptRepository, times(1)).save(verifiedAttempt);
    verify(eventDispatcher, times(1)).send(event);
  }

  private MultiplicationSolvedEvent getMultiplicationSolvedEvent(
      MultiplicationResultAttempt attempt) {
    return new MultiplicationSolvedEvent(attempt.getId(), attempt.getUser().getId(),
        attempt.isCorrect());
  }

  @Test
  void testInvalidAttemptResult() {
    Multiplication multiplication = getMultiplication(50, 60);
    User user = getUser();

    MultiplicationResultAttempt attempt =
        getMultiplicationResultAttempt(multiplication, user, 1000, false);
    MultiplicationSolvedEvent event = getMultiplicationSolvedEvent(attempt);

    when(userRepository.findByAlias(USER_ALIAS)).thenReturn(Optional.empty());

    boolean result = testClass.checkAttempt(attempt);

    assertFalse(result);
    verify(attemptRepository, times(1)).save(any(MultiplicationResultAttempt.class));
    verify(eventDispatcher, times(1)).send(event);
  }

  @Test
  void retrieveStatsTest() {
    User user = getUser();
    Multiplication multiplication = getMultiplication(50, 60);
    List<MultiplicationResultAttempt> attempts = new ArrayList<>(Arrays.asList(
        getMultiplicationResultAttempt(multiplication, user, 1000, false),
        getMultiplicationResultAttempt(multiplication, user, 1000, true)

    ));

    when(attemptRepository.findTop5ByUserAliasOrderByIdDesc(USER_ALIAS)).thenReturn(attempts);

    List<MultiplicationResultAttempt> results = testClass.getStatsForUser(USER_ALIAS);

    assertEquals(2, results.size());
  }

  private Multiplication getMultiplication(int factorA, int factorB) {
    return new Multiplication(factorA, factorB);
  }

  private User getUser() {
    return new User(USER_ALIAS);
  }

  private MultiplicationResultAttempt getMultiplicationResultAttempt(Multiplication multiplication,
                                                                     User user, int result,
                                                                     boolean isCorrect) {
    return new MultiplicationResultAttempt(user, multiplication, result, isCorrect);
  }
}
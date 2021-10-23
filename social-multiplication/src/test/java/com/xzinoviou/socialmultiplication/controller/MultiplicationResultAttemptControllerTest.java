package com.xzinoviou.socialmultiplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.domain.MultiplicationResultAttempt;
import com.xzinoviou.socialmultiplication.domain.User;
import com.xzinoviou.socialmultiplication.service.MultiplicationService;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author : Xenofon Zinoviou
 */
@WebMvcTest(MultiplicationResultAttemptController.class)
class MultiplicationResultAttemptControllerTest {

  private final static String USER_ALIAS = "John Dough";

  @MockBean
  private MultiplicationService multiplicationService;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  @BeforeEach
  void setup() {
  }

  @Test
  public void postResultReturnCorrect() throws Exception {
    genericParameterizedTest(true);
  }

  @Test
  public void postResultReturnNotCorrect() throws Exception {
    genericParameterizedTest(false);
  }

  void genericParameterizedTest(final boolean correct) throws Exception {
    when(multiplicationService.checkAttempt(any(MultiplicationResultAttempt.class))).thenReturn(
        correct);
    User user = getUser();
    Multiplication multiplication = getMultiplication(50, 60);
    MultiplicationResultAttempt attempt =
        new MultiplicationResultAttempt(user, multiplication, 3000, correct);

    MockHttpServletResponse response = mockMvc.perform(post("/results")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(attempt)))
        .andReturn().getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(objectMapper.writeValueAsString(attempt), response.getContentAsString());
  }

  @Test
  public void getUserStats() throws Exception {
    User user = getUser();
    Multiplication multiplication = getMultiplication(50, 60);
    MultiplicationResultAttempt attempt =
        getMultiplicationResultAttempt(multiplication, user, 3000, true);

    List<MultiplicationResultAttempt> recentAttempts = Lists.newArrayList(attempt, attempt);
    when(multiplicationService.getStatsForUser(USER_ALIAS)).thenReturn(recentAttempts);

    MockHttpServletResponse response = mockMvc.perform(get("/results")
            .param("alias", USER_ALIAS))
        .andReturn().getResponse();

    assertEquals(objectMapper.writeValueAsString(recentAttempts), response.getContentAsString());
  }

  private User getUser() {
    return new User(USER_ALIAS);
  }

  private Multiplication getMultiplication(int factorA, int factorB) {
    return new Multiplication(factorA, factorB);
  }

  private MultiplicationResultAttempt getMultiplicationResultAttempt(Multiplication multiplication,
                                                                     User user, int result,
                                                                     boolean isCorrect) {
    return new MultiplicationResultAttempt(user, multiplication, result, isCorrect);
  }
}
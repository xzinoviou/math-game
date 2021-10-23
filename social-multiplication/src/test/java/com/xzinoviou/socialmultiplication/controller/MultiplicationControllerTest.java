package com.xzinoviou.socialmultiplication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xzinoviou.socialmultiplication.domain.Multiplication;
import com.xzinoviou.socialmultiplication.service.MultiplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author : Xenofon Zinoviou
 */
@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerTest {

  @MockBean
  private MultiplicationService multiplicationService;

  @Autowired
  private MockMvc mockMvc;

  private ObjectMapper objectMapper = new ObjectMapper();

  private JacksonTester<Multiplication> json;

  private MultiplicationController testClass;

  @BeforeEach
  void init() {
    testClass = new MultiplicationController(multiplicationService);
    JacksonTester.initFields(this, objectMapper);
  }

  @Test
  void testGetRandomMultiplication() throws Exception {
    Multiplication multiplication = new Multiplication(70, 20);

    Mockito.when(multiplicationService.createRandomMultiplication()).thenReturn(multiplication);

    MockHttpServletResponse response =
        mockMvc.perform(MockMvcRequestBuilders.get("/multiplications/random")
            .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn().getResponse();

    assertEquals(HttpStatus.OK.value(), response.getStatus());
    assertEquals(json.write(multiplication).getJson(), response.getContentAsString());
    assertEquals(objectMapper.writeValueAsString(multiplication),response.getContentAsString());
    Mockito.verify(multiplicationService, times(1)).createRandomMultiplication();
  }

}

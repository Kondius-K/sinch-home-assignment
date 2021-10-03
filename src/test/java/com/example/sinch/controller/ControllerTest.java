package com.example.sinch.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.sinch.service.PairsByKService;
import com.example.sinch.service.PolishNotationService;
import com.example.sinch.utils.ResourceFile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(Controller.class)
class ControllerTest extends AbstractControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PolishNotationService polishNotationService;

  @MockBean
  private PairsByKService pairsByKService;

  @AfterEach
  void tearDown() {
    Mockito.verifyNoMoreInteractions(polishNotationService, pairsByKService);
  }

  @Test
  public void testPolishExpressionOK() throws Exception {
    when(polishNotationService.evaluateExpressions(any()))
        .thenReturn(new String[0]);

    mockMvc
        .perform(
          get("/polish")
              .content(ResourceFile.readContent("/json/valid-polish-input.json"))
              .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  public void testPolishExpressionEmptyArray() throws Exception {
    when(polishNotationService.evaluateExpressions(any()))
        .thenReturn(new String[0]);

    mockMvc
        .perform(
            get("/polish")
                .content(ResourceFile.readContent("/json/invalid-polish-input.json"))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Expression list is empty"));
  }

  @Test
  public void testPairsCounterOK() throws Exception {
    when(pairsByKService.countPairs(any()))
        .thenReturn(1);

    mockMvc
        .perform(
            get("/pairs")
                .content(ResourceFile.readContent("/json/valid-pair-input.json"))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(1));
  }

  @Test
  public void testPairsCounterInvalidInput() throws Exception {
    when(pairsByKService.countPairs(any()))
        .thenReturn(1);

    mockMvc
        .perform(
            get("/pairs")
                .content(ResourceFile.readContent("/json/invalid-pair-input.json"))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Expression list should have more then 2 int values"));
  }
}
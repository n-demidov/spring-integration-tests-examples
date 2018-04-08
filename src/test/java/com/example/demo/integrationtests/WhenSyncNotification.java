package com.example.demo.integrationtests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.SpringIntegrationTestsDemo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringIntegrationTestsDemo.class })
@WebAppConfiguration(value = "")
public class WhenSyncNotification {

  private static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
  private static final String NOTIFY_URL = "/notify";

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void thenReturnHiMessage() throws Exception {
    String expectedMessage = "hi";

    MvcResult mvcResult = this.mockMvc.perform(get(NOTIFY_URL))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(content().contentType(APPLICATION_JSON_CHARSET_UTF_8))
      .andExpect(jsonPath("$.message").value(expectedMessage))
      .andReturn();

    /* We can extract some values from MvcResult object and verify them "manually". For example:
        Note: assertThat() is a method from 'assertj' library. Import is: "import static org.assertj.core.api.Assertions.assertThat;"
     */
    assertThat(mvcResult.getResponse().getContentType()).isEqualTo(APPLICATION_JSON_CHARSET_UTF_8);
  }

}

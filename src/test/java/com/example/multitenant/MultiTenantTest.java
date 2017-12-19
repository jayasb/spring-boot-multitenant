package com.example.multitenant;

import com.example.multitenant.controller.IndexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MultiTenantTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IndexController indexController;

    @Test
    public void contextLoads() {
        assertThat(indexController).isNotNull();
    }

    @Test
    public void shouldReturnZoneEastData() throws Exception {
        this.mockMvc.perform(get("/employees?zone=east")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Chris Bane")));
    }

    @Test
    public void shouldReturnZoneWestData() throws Exception {
        this.mockMvc.perform(get("/employees?zone=west")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Tom Hanks")));
    }

}

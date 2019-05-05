package com.shijt.OAuth2.controller;

import com.shijt.OAuth2.vo.User;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private MockHttpSession mockHttpSession;

    @Before
    public void setUp() throws Exception {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockHttpSession=new MockHttpSession();
        User user=new User();
        user.setName("daming");
        user.setPassword("123456");
        mockHttpSession.setAttribute("user",user);
    }

    @Test
    public void getHistoryExpense() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getHistoryExpense/1/4")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .session(mockHttpSession)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void setHistoryExpense() {
    }

    @Test
    public void getExcel() {
    }

    @Test
    public void importMeterData() {
    }
}
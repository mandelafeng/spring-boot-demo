package com.cfhui.controller;

import com.cfhui.service.IDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(DemoController.class)
class DemoControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private IDemoService demoService;

    @Test
    void test1() throws Exception {

        when(demoService.getMsg()).thenReturn("hello");

        mvc.perform(MockMvcRequestBuilders.get("/test1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());

        verify(demoService).getMsg();
    }
}

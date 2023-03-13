package com.cfhui.controller;

import com.cfhui.entity.Role;
import com.cfhui.service.IRoleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(RoleController.class)
class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IRoleService roleService;

    @Test
    @DisplayName("测试参数校验")
    void save() throws Exception {
        Role role = new Role();
        role.setId(1);
//        role.setName("hui");
        when(roleService.save(role)).thenReturn(role);

        mvc.perform(MockMvcRequestBuilders.post("/role")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError()).andDo(MockMvcResultHandlers.print());
    }
}

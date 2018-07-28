package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {UserControllerTest.class,MockServletContext.class}) // springboot 测试类
public class UserControllerTest {
//
//    @Autowired
//    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();这种方式老是报404错误
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
//        MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("user").contentType(MediaType.APPLICATION_JSON_UTF8);
//        ResultActions perform = mockMvc.perform(contentType);
//        ResultActions andExpect = perform.andExpect(MockMvcResultMatchers.status().isOk());
//        andExpect.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        String result = mockMvc.perform(get("/user")
                .param("username", "pojo").param("age", "18")
                .param("toAge", "60").param("xxx", "yyy")
//                .param("size", "15").param("page", "1")
//                .param("sort", "age,desc")
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(3))
        .andReturn().getResponse().getContentAsString();
        
        System.out.println(result);
    }
    @Test
    public void whenGetInfoSuccess() throws Exception {
       String result =  mockMvc.perform(get("/user/1").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andExpect(jsonPath("$.username").value("tom"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenGetInfoFail() throws Exception {
        //参数只能专递数字 加了正则的验证
        mockMvc.perform(get("/user/a").contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenCreateSuccess() throws  Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
        .andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"))
        .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}

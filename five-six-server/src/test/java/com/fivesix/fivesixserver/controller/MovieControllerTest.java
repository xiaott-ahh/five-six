package com.fivesix.fivesixserver.controller;

import com.fivesix.fivesixserver.FiveSixServerApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@AutoConfigureMockMvc
public class MovieControllerTest extends FiveSixServerApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetMoviesByPageIndex() throws Exception {
       this.mockMvc.perform(get("/api/movies/page/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

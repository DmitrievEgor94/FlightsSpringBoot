package com.mycompany.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.webapp.FlightsApplication;
import com.mycompany.webapp.models.Passenger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightsApplication.class)
@AutoConfigureMockMvc
public class PassengerControllerTest {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTest() throws Exception {
        mockMvc.perform(get("/passengers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("{\"id\":" + "1")));
    }


    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/passengers/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void postTest() throws Exception {
        Passenger passenger = new Passenger("Stanislav", "Zavgorodni");

        mockMvc.perform(post("/passengers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(passenger)))
                .andExpect(status().isCreated());
    }

    @Test
    public void putTest() throws Exception {
        Passenger passenger = new Passenger("Stanislav", "Zavgorodni");

        passenger.setId(2L);

        mockMvc.perform(
                put("/passengers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(passenger)))
                .andExpect(status().isOk());
    }

    @Test
    public void getPassengersWithSeveralTicketsTest() throws Exception {
        String content = mockMvc.perform(
                get("/passengers/get-with-several-tickets"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Passenger[] passengers = mapper.readValue(content, Passenger[].class);

        assertEquals(1, passengers.length);
    }

    @Test
    public void countByFlightNumberTest() throws Exception {
        String content = mockMvc.perform(
                get("/passengers/count-by-flight-number")
                        .param("flightNumber", "x123xcg"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals("1", content);
    }

}

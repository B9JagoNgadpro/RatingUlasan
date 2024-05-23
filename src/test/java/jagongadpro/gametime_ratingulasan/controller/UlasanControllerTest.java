package jagongadpro.gametime_ratingulasan.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@WebMvcTest(UlasanController.class)
class UlasanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UlasanService ulasanService;

    @Autowired
    private ObjectMapper objectMapper;

    private Ulasan ulasan;

    @BeforeEach
    void setup() {
        ulasan = new Ulasan.Builder()
                .id("ulasan1")
                .idUser("user1")
                .game("game1")
                .rating(4)
                .deskripsi("Great game")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void testCreateUlasan() throws Exception {
        // Mock service method
        when(ulasanService.createUlasan(any(Ulasan.class))).thenReturn(CompletableFuture.completedFuture(ulasan));

        // Request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", "ulasan1");
        requestBody.put("idUser", "user1");
        requestBody.put("game", "game1");
        requestBody.put("rating", 4);
        requestBody.put("deskripsi", "Great game");
        requestBody.put("date", LocalDate.now().toString());

        // Perform the request
        MvcResult mvcResult = mockMvc.perform(post("/ulasan/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestBody)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());

        verify(ulasanService, times(1)).createUlasan(any(Ulasan.class));
    }


    @Test
    void testGetUlasanNotFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    void testGetUlasanFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(ulasan)));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"));

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    void testGetUlasanUser() throws Exception {
        when(ulasanService.findUlasansByUserId("user1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList(ulasan)));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/user/user1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasansByUserId("user1");
    }

    @Test
    void testGetUlasanUserNotFound() throws Exception {
        when(ulasanService.findUlasansByUserId("user1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/user/user1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasansByUserId("user1");
    }

    @Test
    void testGetUlasanGame() throws Exception {
        when(ulasanService.findUlasansByGameId("game1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList(ulasan)));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/game/game1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasansByGameId("game1");
    }

    @Test
    void testGetUlasanGameNotFound() throws Exception {
        when(ulasanService.findUlasansByGameId("game1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/game/game1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasansByGameId("game1");
    }

    @Test
    void testEditUlasan() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(ulasan)));
        when(ulasanService.updateUlasan(any(Ulasan.class))).thenReturn(CompletableFuture.completedFuture(ulasan));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/ulasan/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "deskripsi", "Updated description",
                                "rating", 5
                        ))))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).updateUlasan(any(Ulasan.class));
    }

    @Test
    void testDeleteUlasan() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(ulasan)));
        doNothing().when(ulasanService).deleteUlasan("1");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/ulasan/delete/1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).deleteUlasan("1");
    }
}

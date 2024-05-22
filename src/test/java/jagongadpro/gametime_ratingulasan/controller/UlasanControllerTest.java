package jagongadpro.gametime_ratingulasan.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

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
                .id("1")
                .idUser("user1")
                .game("game1")
                .rating(4)
                .deskripsi("Great game")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void testCreateUlasan() throws Exception {
        when(ulasanService.createUlasan(any(Ulasan.class))).thenReturn(ulasan);

        mockMvc.perform(MockMvcRequestBuilders.post("/ulasan/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "id", "1",
                                "idUser", "user1",
                                "game", "game1",
                                "rating", 4,
                                "deskripsi", "Great game",
                                "date", LocalDate.now().toString()
                        ))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(ulasanService, times(1)).createUlasan(any(Ulasan.class));
    }

    @Test
    void testGetUlasanNotFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1"))
                .andExpect(status().isNotFound());

        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    void testGetUlasanFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(Optional.of(ulasan));

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));

        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    void testGetUlasanUser() throws Exception {
        when(ulasanService.findUlasansByUserId("user1")).thenReturn(Arrays.asList(ulasan));

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/user/user1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idUser").value("user1"));

        verify(ulasanService, times(1)).findUlasansByUserId("user1");
    }

    @Test
    void testGetUlasanUserNotFound() throws Exception {
        when(ulasanService.findUlasansByUserId("user1")).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/user/user1"))
                .andExpect(status().isNotFound());

        verify(ulasanService, times(1)).findUlasansByUserId("user1");
    }

    @Test
    void testGetUlasanGame() throws Exception {
        when(ulasanService.findUlasansByGameId("game1")).thenReturn(Arrays.asList(ulasan));

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/game/game1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].game").value("game1"));

        verify(ulasanService, times(1)).findUlasansByGameId("game1");
    }

    @Test
    void testGetUlasanGameNotFound() throws Exception {
        when(ulasanService.findUlasansByGameId("game1")).thenReturn(Arrays.asList());

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/game/game1"))
                .andExpect(status().isNotFound());

        verify(ulasanService, times(1)).findUlasansByGameId("game1");
    }

    @Test
    void testEditUlasan() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(Optional.of(ulasan));
        when(ulasanService.updateUlasan(any(Ulasan.class))).thenReturn(ulasan);

        mockMvc.perform(MockMvcRequestBuilders.patch("/ulasan/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "deskripsi", "Updated description",
                                "rating", 5
                        ))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ulasan updated successfully")));

        verify(ulasanService, times(1)).updateUlasan(any(Ulasan.class));
    }

    @Test
    void testDeleteUlasan() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(Optional.of(ulasan));
        doNothing().when(ulasanService).deleteUlasan("1");

        mockMvc.perform(MockMvcRequestBuilders.delete("/ulasan/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ulasan deleted successfully")));

        verify(ulasanService, times(1)).deleteUlasan("1");
    }
}

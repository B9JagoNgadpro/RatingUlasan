package jagongadpro.gametime_ratingulasan.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UlasanController.class)
public class UlasanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UlasanService ulasanService;

    @Autowired
    private ObjectMapper objectMapper;

    private Ulasan ulasan;

    @BeforeEach
    public void setup() {
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
    public void testCreateUlasan() throws Exception {
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("ulasan dengan id 1 berhasil dibuat!")));

        verify(ulasanService, times(1)).createUlasan(any(Ulasan.class));
    }

    @Test
    public void testGetUlasanNotFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("tidak ditemukan"));

        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    public void testGetUlasanFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(ulasan);

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("ditemukan idUlasan 1 dibuat oleh user1 utk game game1 dgn deskripsi Great game")));

        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    public void testGetUlasanUser() throws Exception {
        when(ulasanService.findUlasansByUserId("user1")).thenReturn(Arrays.asList(ulasan));

        mockMvc.perform(MockMvcRequestBuilders.get("/ulasan/user/user1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("user user1 punya ulasan sebanyak 1"));

        verify(ulasanService, times(1)).findUlasansByUserId("user1");
    }

    @Test
    public void testEditUlasan() throws Exception {
        // Setup
        when(ulasanService.findUlasanById("1")).thenReturn(ulasan);

        // Execute and verify
        mockMvc.perform(MockMvcRequestBuilders.patch("/ulasan/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "deskripsi", "Updated description",
                                "rating", 5
                        ))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Before: ulasan 1, deskripsi Great game, rating 4\nAfter ulasan 1, deskripsi Updated description, rating 5")));

        verify(ulasanService, times(1)).updateUlasan(any(Ulasan.class));
    }


    @Test
    public void testDeleteUlasan() throws Exception {
        when(ulasanService.deleteUlasan("1")).thenReturn(ulasan);

        mockMvc.perform(MockMvcRequestBuilders.delete("/ulasan/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("ulasan 1 berhasil dihapus"));

        verify(ulasanService, times(1)).deleteUlasan("1");
    }
}

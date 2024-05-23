package jagongadpro.gametime_ratingulasan.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.TanggapanUlasanService;
import jagongadpro.gametime_ratingulasan.service.UlasanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@WebMvcTest(TanggapanUlasanController.class)
class TanggapanUlasanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UlasanService ulasanService;

    @MockBean
    private TanggapanUlasanService tanggapanUlasanService;

    @Autowired
    private ObjectMapper objectMapper;

    private Ulasan mockUlasan;
    private TanggapanUlasan mockTanggapan;

    @BeforeEach
    void setup() {
        mockUlasan = new Ulasan.Builder().id("1").build();
        mockTanggapan = new TanggapanUlasan.Builder()
                .id("1")
                .penjualId("seller1")
                .ulasan(mockUlasan)
                .tanggapan("Response text")
                .date(LocalDate.now())
                .build();
    }

    @Test
    void testCreateTanggapanPost_Success() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(mockUlasan)));
        when(tanggapanUlasanService.createTanggapanUlasan(any(TanggapanUlasan.class))).thenReturn(CompletableFuture.completedFuture(mockTanggapan));

        MvcResult mvcResult = mockMvc.perform(post("/penilaian-produk/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "id", "1",
                                "penjualId", "seller1",
                                "ulasan", "1",
                                "tanggapan", "Response text"
                        ))))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).createTanggapanUlasan(any(TanggapanUlasan.class));
    }

    @Test
    void testCreateTanggapanPost_UlasanNotFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        MvcResult mvcResult = mockMvc.perform(post("/penilaian-produk/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "id", "1",
                                "penjualId", "seller1",
                                "ulasan", "1",
                                "tanggapan", "Response text"
                        ))))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(ulasanService, times(1)).findUlasanById("1");
    }

    @Test
    void testGetTanggapan_Found() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(mockTanggapan)));

        MvcResult mvcResult = mockMvc.perform(get("/penilaian-produk/1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).findTanggapanUlasanById("1");
    }

    @Test
    void testGetTanggapan_NotFound() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        MvcResult mvcResult = mockMvc.perform(get("/penilaian-produk/1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).findTanggapanUlasanById("1");
    }

    @Test
    void testGetAllTanggapanPenjual() throws Exception {
        when(tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("seller1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList(mockTanggapan)));

        MvcResult mvcResult = mockMvc.perform(get("/penilaian-produk/user/seller1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).findAllTanggapanUlasanByPenjualId("seller1");
    }

    @Test
    void testGetAllTanggapanPenjual_NoneFound() throws Exception {
        when(tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("seller1")).thenReturn(CompletableFuture.completedFuture(Arrays.asList()));

        MvcResult mvcResult = mockMvc.perform(get("/penilaian-produk/user/seller1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).findAllTanggapanUlasanByPenjualId("seller1");
    }

    @Test
    void testEditTanggapan_Success() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(mockTanggapan)));
        when(tanggapanUlasanService.updateTanggapanUlasan(any(TanggapanUlasan.class))).thenReturn(CompletableFuture.completedFuture(mockTanggapan));

        MvcResult mvcResult = mockMvc.perform(patch("/penilaian-produk/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("tanggapan", "Updated response"))))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).updateTanggapanUlasan(any(TanggapanUlasan.class));
    }

    @Test
    void testDeleteTanggapan_Success() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(mockTanggapan)));

        MvcResult mvcResult = mockMvc.perform(delete("/penilaian-produk/delete/1"))
                .andReturn();

        mvcResult.getAsyncResult(); // Wait for async result
        assertEquals(200, mvcResult.getResponse().getStatus());
        verify(tanggapanUlasanService, times(1)).deleteTanggapanUlasan("1");
    }
}

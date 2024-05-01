package jagongadpro.gametime_ratingulasan.controller;

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
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

@WebMvcTest(TanggapanUlasanController.class)
public class TanggapanUlasanControllerTest {

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
    public void setup() {
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
    public void testCreateTanggapanPost_Success() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(mockUlasan);
        when(tanggapanUlasanService.createTanggapanUlasan(any(TanggapanUlasan.class))).thenReturn(mockTanggapan);

        mockMvc.perform(post("/penilaian-produk/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "id", "1",
                                "penjualId", "seller1",
                                "ulasan", "1",
                                "tanggapan", "Response text"
                        ))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tanggapan untuk 1 berhasil dibuat dengan id 1")));
    }

    @Test
    public void testCreateTanggapanPost_UlasanNotFound() throws Exception {
        when(ulasanService.findUlasanById("1")).thenReturn(null);

        mockMvc.perform(post("/penilaian-produk/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of(
                                "id", "1",
                                "penjualId", "seller1",
                                "ulasan", "1",
                                "tanggapan", "Response text"
                        ))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ulasan tidak ditemukan")));
    }

    @Test
    public void testGetTanggapan_Found() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(mockTanggapan);

        mockMvc.perform(get("/penilaian-produk/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tanggapan untuk 1ditemukan")));
    }

    @Test
    public void testGetTanggapan_NotFound() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(null);

        mockMvc.perform(get("/penilaian-produk/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tidak ditemukan")));
    }

    @Test
    public void testGetAllTanggapanPenjual() throws Exception {
        when(tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("seller1")).thenReturn(Arrays.asList(mockTanggapan));

        mockMvc.perform(get("/penilaian-produk/user/seller1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("penjual seller1 sudah menanggapi 1 ulasan")));
    }

    @Test
    public void testGetAllTanggapanPenjual_NoneFound() throws Exception {
        when(tanggapanUlasanService.findAllTanggapanUlasanByPenjualId("seller1")).thenReturn(Arrays.asList());

        mockMvc.perform(get("/penilaian-produk/user/seller1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("penjual seller1 belum menanggapi ulasan")));
    }

    @Test
    public void testEditTanggapan_Success() throws Exception {
        when(tanggapanUlasanService.findTanggapanUlasanById("1")).thenReturn(mockTanggapan);

        mockMvc.perform(patch("/penilaian-produk/edit/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Map.of("tanggapan", "Updated response"))))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("After: Updated response")));
    }

    @Test
    public void testDeleteTanggapan_Success() throws Exception {
        doNothing().when(tanggapanUlasanService).deleteTanggapanUlasan("1");

        mockMvc.perform(delete("/penilaian-produk/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tanggapan dengan id 1 berhasil didelete")));
    }
}

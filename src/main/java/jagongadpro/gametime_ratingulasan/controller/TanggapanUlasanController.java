package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.TanggapanUlasanService;

import jagongadpro.gametime_ratingulasan.service.UlasanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/penilaian-produk")
public class TanggapanUlasanController {

    @Autowired
    private UlasanService ulasanService;
    @Autowired
    private TanggapanUlasanService service;

    @PostMapping("/create")
    public String createTanggapanPost(@RequestBody Map<String, Object> data) {
        Ulasan ulasan = ulasanService.findUlasanById(data.get("ulasan").toString());
        if (ulasan == null) {
            return "ulasan tidak ditemukan";
        }
        TanggapanUlasan tanggapanUlasan = new TanggapanUlasan.Builder()
                .id(data.get("id").toString())
                .penjualId(data.get("penjualId").toString())
                .ulasan(ulasan)
                .tanggapan(data.get("tanggapan").toString())
                .date(LocalDate.now())
                .build();

        service.createTanggapanUlasan(tanggapanUlasan);
        return "tanggapan untuk " + ulasan.getId() + " berhasil dibuat dengan id " + tanggapanUlasan.getId();
    }

    @GetMapping("/{idTanggapan}")
    public String getTanggapan(@PathVariable String idTanggapan, Model model) {
        TanggapanUlasan tanggapanFind = service.findTanggapanUlasanById(idTanggapan);
        if (tanggapanFind == null) {
            return "tidak ditemukan";
        }
        return "tanggapan untuk " + tanggapanFind.getId() + "ditemukan";
    }

    @GetMapping("user/{idPenjual}")
    public String getAllTanggapanPenjual(@PathVariable String idPenjual, Model model) {
        List<TanggapanUlasan> listTanggapan = service.findAllTanggapanUlasanByPenjualId(idPenjual);
        if (listTanggapan.isEmpty()) {
            return "penjual " + idPenjual + " belum menanggapi ulasan ";
        } else {
            return "penjual " + idPenjual + " sudah menanggapi " + listTanggapan.size() + " ulasan";
        }
    }

    @PatchMapping("/edit/{idTanggapan}")
    public String editTanggapan(@PathVariable String idTanggapan, @RequestBody Map<String, Object> data) {
        TanggapanUlasan tanggapan = service.findTanggapanUlasanById(idTanggapan);
        String tanggapanSebelum = tanggapan.getTanggapan();

        tanggapan.setTanggapan(data.get("tanggapan").toString());
        tanggapan.setDate(LocalDate.now());

        service.updateTanggapanUlasan(tanggapan);

        return String.format("Before: %s\nAfter: %s", tanggapanSebelum, tanggapan.getTanggapan());
    }

    @DeleteMapping("/delete/{idTanggapan}")
    public String deleteTanggapan(@PathVariable String idTanggapan, Model model) {
        service.deleteTanggapanUlasan(idTanggapan);
        return "tanggapan dengan id " + idTanggapan + " berhasil didelete";
    }


}

package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.TanggapanUlasanService;
import jagongadpro.gametime_ratingulasan.service.UlasanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createTanggapanPost(@RequestBody Map<String, Object> data) {
        return ulasanService.findUlasanById(data.get("ulasan").toString()).map(ulasan -> {
            TanggapanUlasan tanggapanUlasan = new TanggapanUlasan.Builder()
                    .id(data.get("id").toString())
                    .penjualId(data.get("penjualId").toString())
                    .ulasan(ulasan)
                    .tanggapan(data.get("tanggapan").toString())
                    .date(LocalDate.now())
                    .build();
            service.createTanggapanUlasan(tanggapanUlasan);
            return ResponseEntity.ok("Tanggapan created successfully");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Ulasan not found"));
    }

    @GetMapping("/{idTanggapan}")
    public ResponseEntity<?> getTanggapan(@PathVariable String idTanggapan) {
        return service.findTanggapanUlasanById(idTanggapan)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{idPenjual}")
    public ResponseEntity<List<TanggapanUlasan>> getAllTanggapanPenjual(@PathVariable String idPenjual) {
        List<TanggapanUlasan> listTanggapan = service.findAllTanggapanUlasanByPenjualId(idPenjual);
        if (listTanggapan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listTanggapan);
    }

    @PatchMapping("/edit/{idTanggapan}")
    public ResponseEntity<String> editTanggapan(@PathVariable String idTanggapan, @RequestBody Map<String, Object> data) {
        return service.findTanggapanUlasanById(idTanggapan).map(tanggapan -> {
            tanggapan.setTanggapan(data.get("tanggapan").toString());
            tanggapan.setDate(LocalDate.now());
            service.updateTanggapanUlasan(tanggapan);
            return ResponseEntity.ok("Tanggapan updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{idTanggapan}")
    public ResponseEntity<String> deleteTanggapan(@PathVariable String idTanggapan) {
        return service.findTanggapanUlasanById(idTanggapan).map(tanggapan -> {
            service.deleteTanggapanUlasan(idTanggapan);
            return ResponseEntity.ok("Tanggapan deleted successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
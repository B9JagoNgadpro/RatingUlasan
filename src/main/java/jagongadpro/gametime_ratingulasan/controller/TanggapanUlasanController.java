package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.service.TanggapanUlasanService;
import jagongadpro.gametime_ratingulasan.service.UlasanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/penilaian-produk")
public class TanggapanUlasanController {

    private final UlasanService ulasanService;
    private final TanggapanUlasanService service;

    private TanggapanUlasanController(UlasanService ulasanService, TanggapanUlasanService service) {
        this.ulasanService = ulasanService;
        this.service = service;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<String>> createTanggapanPost(@RequestBody Map<String, Object> data) {
        return ulasanService.findUlasanById(data.get("ulasan").toString()).thenApply(ulasanOptional -> ulasanOptional.map(ulasan -> {
            TanggapanUlasan tanggapanUlasan = new TanggapanUlasan.Builder()
                    .id(UUID.randomUUID().toString())
                    .penjualId(data.get("penjualId").toString())
                    .ulasan(ulasan)
                    .tanggapan(data.get("tanggapan").toString())
                    .date(LocalDate.now())
                    .build();
            service.createTanggapanUlasan(tanggapanUlasan);
            return ResponseEntity.ok("Tanggapan created successfully");
        }).orElseGet(() -> ResponseEntity.badRequest().body("Ulasan not found")));
    }

    @GetMapping("/{idTanggapan}")
    public CompletableFuture<ResponseEntity<TanggapanUlasan>> getTanggapan(@PathVariable String idTanggapan) {
        return service.findTanggapanUlasanById(idTanggapan)
                .thenApply(tanggapanUlasan -> tanggapanUlasan.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping("/user/{idPenjual}")
    public CompletableFuture<ResponseEntity<List<TanggapanUlasan>>> getAllTanggapanPenjual(@PathVariable String idPenjual) {
        return service.findAllTanggapanUlasanByPenjualId(idPenjual)
                .thenApply(listTanggapan -> {
                    if (listTanggapan.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(listTanggapan);
                });
    }

    @PatchMapping("/edit/{idTanggapan}")
    public CompletableFuture<ResponseEntity<String>> editTanggapan(@PathVariable String idTanggapan, @RequestBody Map<String, Object> data) {
        return service.findTanggapanUlasanById(idTanggapan).thenApply(tanggapanOptional -> tanggapanOptional.map(tanggapan -> {
            tanggapan.setTanggapan(data.get("tanggapan").toString());
            tanggapan.setDate(LocalDate.now());
            service.updateTanggapanUlasan(tanggapan);
            return ResponseEntity.ok("Tanggapan updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/delete/{idTanggapan}")
    public CompletableFuture<ResponseEntity<String>> deleteTanggapan(@PathVariable String idTanggapan) {
        return service.findTanggapanUlasanById(idTanggapan).thenApply(tanggapanOptional -> tanggapanOptional.map(tanggapan -> {
            service.deleteTanggapanUlasan(idTanggapan);
            return ResponseEntity.ok("Tanggapan deleted successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build()));
    }
}
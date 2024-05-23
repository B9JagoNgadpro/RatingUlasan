package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/ulasan")
public class UlasanController {

    private final UlasanService service;

    private UlasanController(UlasanService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Ulasan>> createUlasanPost(@RequestBody Map<String, Object> data) {
        Ulasan ulasan = new Ulasan.Builder()
                .id(data.get("id").toString())
                .idUser(data.get("idUser").toString())
                .game(data.get("game").toString())
                .rating(Integer.parseInt(data.get("rating").toString()))
                .deskripsi(data.get("deskripsi").toString())
                .date(LocalDate.parse(data.get("date").toString()))
                .build();
        return service.createUlasan(ulasan).thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{idUlasan}")
    public CompletableFuture<ResponseEntity<Ulasan>> getUlasan(@PathVariable String idUlasan) {
        return service.findUlasanById(idUlasan)
                .thenApply(ulasan -> ulasan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping("user/{idUser}")
    public CompletableFuture<ResponseEntity<List<Ulasan>>> getUlasanUser(@PathVariable String idUser) {
        return service.findUlasansByUserId(idUser)
                .thenApply(listUlasan -> {
                    if (listUlasan.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(listUlasan);
                });
    }

    @GetMapping("game/{idGame}")
    public CompletableFuture<ResponseEntity<List<Ulasan>>> getUlasanGame(@PathVariable String idGame) {
        return service.findUlasansByGameId(idGame)
                .thenApply(listUlasan -> {
                    if (listUlasan.isEmpty()) {
                        return ResponseEntity.notFound().build();
                    }
                    return ResponseEntity.ok(listUlasan);
                });
    }

    @PatchMapping("/edit/{idUlasan}")
    public CompletableFuture<ResponseEntity<String>> editUlasanPatch(@PathVariable String idUlasan, @RequestBody Map<String, Object> data) {
        return service.findUlasanById(idUlasan).thenApply(ulasanOptional -> ulasanOptional.map(ulasan -> {
            ulasan.setDeskripsi(data.get("deskripsi").toString());
            ulasan.setRating(Integer.parseInt(data.get("rating").toString()));
            ulasan.setDate(LocalDate.now());
            service.updateUlasan(ulasan);
            return ResponseEntity.ok("Ulasan updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/delete/{idUlasan}")
    public CompletableFuture<ResponseEntity<String>> deleteUlasan(@PathVariable String idUlasan) {
        return service.findUlasanById(idUlasan).thenApply(ulasanOptional -> ulasanOptional.map(ulasan -> {
            service.deleteUlasan(idUlasan);
            return ResponseEntity.ok("Ulasan deleted successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build()));
    }
}
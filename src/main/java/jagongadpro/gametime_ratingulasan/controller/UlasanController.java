package jagongadpro.gametime_ratingulasan.controller;

//import jagongadpro.gametime_ratingulasan.dto.UlasanDTO;
import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ulasan")
public class UlasanController {

    @Autowired
    private UlasanService service;

    @PostMapping("/create")
    public String createUlasanPost(@RequestBody Map<String, Object> data) {
        Ulasan ulasan = new Ulasan.Builder()
                .id(data.get("id").toString())
                .idUser(data.get("idUser").toString())
                .game(data.get("game").toString())
                .rating(Integer.parseInt(data.get("rating").toString()))
                .deskripsi(data.get("deskripsi").toString())
                .date(LocalDate.parse(data.get("date").toString()))
                .build();
        service.createUlasan(ulasan);
        return "ulasan dengan id " + ulasan.getId() + " berhasil dibuat!";
    }

    @GetMapping("/{idUlasan}")
    public String getUlasan(@PathVariable String idUlasan, Model model) {
        Ulasan ulasanFind = service.findUlasanById(idUlasan);
        if(ulasanFind == null) {
            return "tidak ditemukan";
        }
        return "ditemukan idUlasan " + idUlasan + " dibuat oleh " + ulasanFind.getIdUser() + " utk game " + ulasanFind.getGame() + " dgn deskripsi " + ulasanFind.getDeskripsi();
    }

    @GetMapping("user/{idUser}")
    public String getUlasanUser(@PathVariable String idUser, Model model) {
        List<Ulasan> listUlasan = service.findUlasansByUserId(idUser);
        if (listUlasan.isEmpty()) {
            return "user " + idUser + " belum membuat ulasan ";
        } else {
            return "user " + idUser + " punya ulasan sebanyak " + listUlasan.size();
        }
    }

    @GetMapping("game/{idGame}")
    public String getUlasanGame(@PathVariable String idGame, Model model) {
        List<Ulasan> listUlasan = service.findUlasansByGameId(idGame);
        if (listUlasan.isEmpty()) {
            return "game " + idGame + " belum memiliki ulasan ";
        } else {
            return "game " + idGame + " telah diulas sebanyak " + listUlasan.size();
        }
    }

    @PatchMapping("/edit/{idUlasan}")
    public String editUlasanPatch(@PathVariable String idUlasan, @RequestBody Map<String, Object> data) {
        Ulasan ulasan = service.findUlasanById(idUlasan);
        String ulasanSebelum = String.format("Before: ulasan %s, deskripsi %s, rating %d", ulasan.getId(), ulasan.getDeskripsi(), ulasan.getRating());
        ulasan.setDeskripsi(data.get("deskripsi").toString());
        ulasan.setRating(Integer.parseInt(data.get("rating").toString()));
        ulasan.setDate(LocalDate.now());

        String ulasanSesudah = String.format("After ulasan %s, deskripsi %s, rating %d", ulasan.getId(), ulasan.getDeskripsi(), ulasan.getRating());
        service.updateUlasan(ulasan);

        return String.format("%s\n%s", ulasanSebelum, ulasanSesudah);
    }

    @DeleteMapping("/delete/{idUlasan}")
    public String deleteUlasan(@PathVariable String idUlasan, Model model) {
        Ulasan ulasan = service.deleteUlasan(idUlasan);
        return "ulasan " + ulasan.getId() + " berhasil dihapus";
    }
}
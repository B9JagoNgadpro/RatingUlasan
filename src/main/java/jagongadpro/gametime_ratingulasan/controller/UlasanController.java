package jagongadpro.gametime_ratingulasan.controller;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.service.UlasanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Ulasan> createUlasanPost(@RequestBody Map<String, Object> data) {
        Ulasan ulasan = new Ulasan.Builder()
                .id(data.get("id").toString())
                .idUser(data.get("idUser").toString())
                .game(data.get("game").toString())
                .rating(Integer.parseInt(data.get("rating").toString()))
                .deskripsi(data.get("deskripsi").toString())
                .date(LocalDate.parse(data.get("date").toString()))
                .build();
        Ulasan createdUlasan = service.createUlasan(ulasan);
        return ResponseEntity.ok(createdUlasan);
    }

    @GetMapping("/{idUlasan}")
    public ResponseEntity<?> getUlasan(@PathVariable String idUlasan) {
        return service.findUlasanById(idUlasan)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("user/{idUser}")
    public ResponseEntity<List<Ulasan>> getUlasanUser(@PathVariable String idUser) {
        List<Ulasan> listUlasan = service.findUlasansByUserId(idUser);
        if (listUlasan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listUlasan);
    }

    @GetMapping("game/{idGame}")
    public ResponseEntity<List<Ulasan>> getUlasanGame(@PathVariable String idGame) {
        List<Ulasan> listUlasan = service.findUlasansByGameId(idGame);
        if (listUlasan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listUlasan);
    }

    @PatchMapping("/edit/{idUlasan}")
    public ResponseEntity<String> editUlasanPatch(@PathVariable String idUlasan, @RequestBody Map<String, Object> data) {
        return service.findUlasanById(idUlasan).map(ulasan -> {
            ulasan.setDeskripsi(data.get("deskripsi").toString());
            ulasan.setRating(Integer.parseInt(data.get("rating").toString()));
            ulasan.setDate(LocalDate.now());
            service.updateUlasan(ulasan);
            return ResponseEntity.ok("Ulasan updated successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{idUlasan}")
    public ResponseEntity<String> deleteUlasan(@PathVariable String idUlasan) {
        return service.findUlasanById(idUlasan).map(ulasan -> {
            service.deleteUlasan(idUlasan);
            return ResponseEntity.ok("Ulasan deleted successfully");
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}


//package jagongadpro.gametime_ratingulasan.controller;
//
////import jagongadpro.gametime_ratingulasan.dto.UlasanDTO;
//import jagongadpro.gametime_ratingulasan.model.Ulasan;
//import jagongadpro.gametime_ratingulasan.service.UlasanService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/ulasan")
//public class UlasanController {
//
//    @Autowired
//    private UlasanService service;
//
//    @PostMapping("/create")
//    public String createUlasanPost(@RequestBody Map<String, Object> data) {
//        Ulasan ulasan = new Ulasan.Builder()
//                .id(data.get("id").toString())
//                .idUser(data.get("idUser").toString())
//                .game(data.get("game").toString())
//                .rating(Integer.parseInt(data.get("rating").toString()))
//                .deskripsi(data.get("deskripsi").toString())
//                .date(LocalDate.parse(data.get("date").toString()))
//                .build();
//        service.createUlasan(ulasan);
//        return "ulasan dengan id " + ulasan.getId() + " berhasil dibuat!";
//    }
//
////    @GetMapping("/{idUlasan}")
////    public String getUlasan(@PathVariable String idUlasan, Model model) {
////        Ulasan ulasanFind = service.findUlasanById(idUlasan);
////        if(ulasanFind == null) {
////            return "tidak ditemukan";
////        }
////        return "ditemukan idUlasan " + idUlasan + " dibuat oleh " + ulasanFind.getIdUser() + " utk game " + ulasanFind.getGame() + " dgn deskripsi " + ulasanFind.getDeskripsi();
////    }
//
//    @GetMapping("/{idUlasan}")
//    public ResponseEntity<?> getUlasan(@PathVariable String idUlasan, Model model) {
//        Ulasan ulasanFind = service.findUlasanById(idUlasan);
//        if(ulasanFind == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(ulasanFind);
//    }
//
////    @GetMapping("user/{idUser}")
////    public String getUlasanUser(@PathVariable String idUser, Model model) {
////        List<Ulasan> listUlasan = service.findUlasansByUserId(idUser);
////        if (listUlasan.isEmpty()) {
////            return "user " + idUser + " belum membuat ulasan ";
////        } else {
////            return "user " + idUser + " punya ulasan sebanyak " + listUlasan.size();
////        }
////    }
//
//    @GetMapping("user/{idUser}")
//    public ResponseEntity<?> getUlasanUser(@PathVariable String idUser, Model model) {
//        List<Ulasan> listUlasan = service.findUlasansByUserId(idUser);
//        if (listUlasan.isEmpty()) {
//            return ResponseEntity.notFound().build(); // atau ResponseEntity.ok("user " + idUser + " belum membuat ulasan");
//        } else {
//            return ResponseEntity.ok(listUlasan);
//        }
//    }
//
//    @GetMapping("game/{idGame}")
//    public ResponseEntity<?> getUlasanGame(@PathVariable String idGame, Model model) {
//        List<Ulasan> listUlasan = service.findUlasansByGameId(idGame);
//        if (listUlasan.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(listUlasan);
//        }
//    }
//
//    @PatchMapping("/edit/{idUlasan}")
//    public String editUlasanPatch(@PathVariable String idUlasan, @RequestBody Map<String, Object> data) {
//        Ulasan ulasan = service.findUlasanById(idUlasan);
//        String ulasanSebelum = String.format("Before: ulasan %s, deskripsi %s, rating %d", ulasan.getId(), ulasan.getDeskripsi(), ulasan.getRating());
//        ulasan.setDeskripsi(data.get("deskripsi").toString());
//        ulasan.setRating(Integer.parseInt(data.get("rating").toString()));
//        ulasan.setDate(LocalDate.now());
//
//        String ulasanSesudah = String.format("After ulasan %s, deskripsi %s, rating %d", ulasan.getId(), ulasan.getDeskripsi(), ulasan.getRating());
//        service.updateUlasan(ulasan);
//
//        return String.format("%s\n%s", ulasanSebelum, ulasanSesudah);
//    }
//
//    @DeleteMapping("/delete/{idUlasan}")
//    public String deleteUlasan(@PathVariable String idUlasan, Model model) {
//        Ulasan ulasan = service.deleteUlasan(idUlasan);
//        return "ulasan " + ulasan.getId() + " berhasil dihapus";
//    }
//}
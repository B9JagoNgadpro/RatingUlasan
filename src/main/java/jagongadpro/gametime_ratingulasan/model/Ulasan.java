package jagongadpro.gametime_ratingulasan.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ulasan {
    String id;
    String idUser;
    Game game;
    Integer rating;
    String deskripsi;
    LocalDate date;

    public void setDeskripsi(String deskripsi) {
        if (deskripsi != null && !deskripsi.isEmpty()) {
            this.deskripsi = deskripsi;
        } else {
            throw new IllegalArgumentException("Deskripsi tidak boleh kosong");
        }
    }

    public void setRating(Integer rating) {
        if (rating != null) {
            // Memastikan rating berada dalam rentang 1-5
            if (rating < 1) {
                this.rating = 1;
            } else if (rating > 5) {
                this.rating = 5;
            } else {
                this.rating = rating;
            }
        }
    }
}


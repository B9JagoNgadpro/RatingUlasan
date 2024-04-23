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
}

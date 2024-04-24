package jagongadpro.gametime_ratingulasan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game {
    @Id
    String id;
    String idPenjual;
    String nama;
    String deskripsi;
    Integer harga;
    String kategori;
    Integer stok;
}
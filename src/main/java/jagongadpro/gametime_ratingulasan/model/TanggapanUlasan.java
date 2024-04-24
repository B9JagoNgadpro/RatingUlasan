package jagongadpro.gametime_ratingulasan.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TanggapanUlasan {
    String id;
    String penjualId;
    Ulasan ulasan;
    String tanggapan;
    LocalDate date;

    public void setTanggapan(String tanggapan) {
        if (tanggapan != null && !tanggapan.isEmpty()) {
            this.tanggapan = tanggapan;
        } else {
            throw new IllegalArgumentException("Tanggapan tidak boleh kosong");
        }
    }

    public void setUlasan(Ulasan ulasan) {
        if (ulasan != null) {
            this.ulasan = ulasan;
        } else {
            throw new IllegalArgumentException("Ulasan tidak boleh kosong");
        }
    }
}

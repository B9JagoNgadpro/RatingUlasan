package jagongadpro.gametime_ratingulasan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tanggapan_ulasan")
public class TanggapanUlasan {
    @Id
    private String id;

    @Column(name = "penjual_id", nullable = false)
    private String penjualId;

    @OneToOne
    @JoinColumn(name = "ulasan_id", nullable = false)
    private Ulasan ulasan;

    @Column(nullable = false)
    private String tanggapan;

    @Column(nullable = false)
    private LocalDate date;

    private TanggapanUlasan(Builder builder) {
        this.id = builder.id;
        this.penjualId = builder.penjualId;
        this.ulasan = builder.ulasan;
        this.tanggapan = builder.tanggapan;
        this.date = builder.date;
    }

    public TanggapanUlasan() {
    }

    public static class Builder {
        private String id;
        private String penjualId;
        private Ulasan ulasan;
        private String tanggapan;
        private LocalDate date;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder penjualId(String penjualId) {
            this.penjualId = penjualId;
            return this;
        }

        public Builder ulasan(Ulasan ulasan) {
            if (ulasan != null) {
                this.ulasan = ulasan;
            } else {
                throw new IllegalArgumentException("Ulasan tidak boleh kosong");
            }
            return this;
        }

        public Builder tanggapan(String tanggapan) {
            if (tanggapan != null && !tanggapan.isEmpty()) {
                this.tanggapan = tanggapan;
            } else {
                throw new IllegalArgumentException("Tanggapan tidak boleh kosong");
            }
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public TanggapanUlasan build() {
            return new TanggapanUlasan(this);
        }
    }
}

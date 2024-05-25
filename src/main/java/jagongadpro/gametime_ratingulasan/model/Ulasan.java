package jagongadpro.gametime_ratingulasan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ulasan")
public class Ulasan {

    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private String idUser;

    @Column(nullable = false)
    private String game;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private String deskripsi;

    @Column(nullable = false)
    private LocalDate date;

    @OneToOne(mappedBy = "ulasan", cascade = CascadeType.REMOVE)
    private TanggapanUlasan tanggapanUlasan;

    private Ulasan(Builder builder) {
        this.id = builder.id;
        this.idUser = builder.idUser;
        this.game = builder.game;
        this.rating = builder.rating;
        this.deskripsi = builder.deskripsi;
        this.date = builder.date;
    }

    public Ulasan() {

    }

    public static class Builder {
        private String id;
        private String idUser;
        private String game;
        private Integer rating;
        private String deskripsi;
        private LocalDate date;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder idUser(String idUser) {
            this.idUser = idUser;
            return this;
        }

        public Builder game(String game) {
            this.game = game;
            return this;
        }

        public Builder rating(Integer rating) {
            if (rating != null) {
                // Memastikan rating berada dalam rentang 1-5
                if (rating < 1) {
                    this.rating = 1;
                } else if (rating > 5) {
                    this.rating = 5;
                } else {
                    this.rating = rating;
                }
            } else {
                throw new IllegalArgumentException("Rating tidak boleh kosong");
            }
            return this;
        }

        public Builder deskripsi(String deskripsi) {
            if (deskripsi != null && !deskripsi.isEmpty()) {
                this.deskripsi = deskripsi;
            } else {
                this.deskripsi = "";
            }
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Ulasan build() {
            return new Ulasan(this);
        }
    }
}

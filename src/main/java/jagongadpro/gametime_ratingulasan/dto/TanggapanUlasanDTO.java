package jagongadpro.gametime_ratingulasan.dto;

import lombok.Data;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class TanggapanUlasanDTO {
    @NotNull
    private String id;

    @NotNull
    private String penjualId;

    @NotNull
    private UlasanDTO ulasan; // Embedding UlasanDTO

    @NotNull
    private String tanggapan;

    @NotNull
    private LocalDate date;
}

package jagongadpro.gametime_ratingulasan.dto;

import lombok.Data;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UlasanDTO {
    @NotNull
    private String id;

    @NotNull
    private String idUser;

    @NotNull
    private String game;

    @Positive
    private Integer rating;

    @NotBlank
    private String deskripsi;

    @NotNull
    private LocalDate date;
}

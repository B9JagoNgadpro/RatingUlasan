package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import java.util.List;

public interface UlasanService {
    Ulasan createUlasan(Ulasan ulasan);
    List<Ulasan> findAllUlasans();
    List<Ulasan> findUlasansByUserId(String userId);
    List<Ulasan> findUlasansByGameId(String gameId);
    Ulasan findUlasanById(String ulasanId);
    Ulasan updateUlasan(Ulasan ulasan);
    Ulasan deleteUlasan(String ulasanId);
}

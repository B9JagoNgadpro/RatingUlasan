package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface UlasanService {
    CompletableFuture<Ulasan> createUlasan(Ulasan ulasan);
    CompletableFuture<List<Ulasan>> findAllUlasans();
    CompletableFuture<List<Ulasan>> findUlasansByUserId(String userId);
    CompletableFuture<List<Ulasan>> findUlasansByGameId(String gameId);
    CompletableFuture<Optional<Ulasan>> findUlasanById(String ulasanId);
    CompletableFuture<Ulasan> updateUlasan(Ulasan ulasan);
    void deleteUlasan(String ulasanId);
}

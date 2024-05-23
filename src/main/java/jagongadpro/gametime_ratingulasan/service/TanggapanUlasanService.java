package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TanggapanUlasanService {
    CompletableFuture<TanggapanUlasan> createTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
    CompletableFuture<TanggapanUlasan> updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
    CompletableFuture<Void> deleteTanggapanUlasan(String id);
    CompletableFuture<Optional<TanggapanUlasan>> findTanggapanUlasanById(String id);
    CompletableFuture<Optional<TanggapanUlasan>> findTanggapanUlasanByUlasanId(String ulasanId);
    CompletableFuture<List<TanggapanUlasan>> findAllTanggapanUlasan();
    CompletableFuture<List<TanggapanUlasan>> findAllTanggapanUlasanByPenjualId(String penjualId);
}

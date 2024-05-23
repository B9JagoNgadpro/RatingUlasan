package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.repository.TanggapanUlasanRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TanggapanUlasanServiceImpl implements TanggapanUlasanService {

    private final TanggapanUlasanRepository tanggapanUlasanRepository;

    private TanggapanUlasanServiceImpl(TanggapanUlasanRepository tanggapanUlasanRepository) {
        this.tanggapanUlasanRepository = tanggapanUlasanRepository;
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<TanggapanUlasan> createTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return CompletableFuture.completedFuture(tanggapanUlasanRepository.save(tanggapanUlasan));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<TanggapanUlasan> updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return CompletableFuture.completedFuture(tanggapanUlasanRepository.save(tanggapanUlasan));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Void> deleteTanggapanUlasan(String id) {
        tanggapanUlasanRepository.deleteById(id);
        return CompletableFuture.completedFuture(null);
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<TanggapanUlasan>> findTanggapanUlasanById(String id) {
        return CompletableFuture.completedFuture(tanggapanUlasanRepository.findById(id));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<TanggapanUlasan>> findTanggapanUlasanByUlasanId(String ulasanId) {
        return CompletableFuture.completedFuture(Optional.ofNullable(tanggapanUlasanRepository.findByUlasanId(ulasanId)));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<TanggapanUlasan>> findAllTanggapanUlasan() {
        return CompletableFuture.completedFuture(tanggapanUlasanRepository.findAll());
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<TanggapanUlasan>> findAllTanggapanUlasanByPenjualId(String penjualId) {
        return CompletableFuture.completedFuture(tanggapanUlasanRepository.findAllByPenjualId(penjualId));
    }
}
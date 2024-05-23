package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UlasanServiceImpl implements UlasanService {

    private final UlasanRepository ulasanRepository;

    private UlasanServiceImpl(UlasanRepository ulasanRepository) {
        this.ulasanRepository = ulasanRepository;
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Ulasan> createUlasan(Ulasan ulasan) {
        return CompletableFuture.supplyAsync(() -> {
            Ulasan savedUlasan = ulasanRepository.save(ulasan);
            return savedUlasan;
        });
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Ulasan>> findAllUlasans() {
        return CompletableFuture.completedFuture(ulasanRepository.findAll());
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Ulasan>> findUlasansByUserId(String idUser) {
        return CompletableFuture.completedFuture(ulasanRepository.findAllByIdUser(idUser));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<List<Ulasan>> findUlasansByGameId(String game) {
        return CompletableFuture.completedFuture(ulasanRepository.findAllByGame(game));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Optional<Ulasan>> findUlasanById(String ulasanId) {
        return CompletableFuture.completedFuture(ulasanRepository.findById(ulasanId));
    }

    @Async("taskExecutor")
    @Override
    public CompletableFuture<Ulasan> updateUlasan(Ulasan ulasan) {
        return CompletableFuture.completedFuture(ulasanRepository.save(ulasan));
    }

    @Async("taskExecutor")
    @Override
    public void deleteUlasan(String ulasanId) {
        ulasanRepository.deleteById(ulasanId);
    }
}
package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UlasanServiceImpl implements UlasanService {
    @Autowired
    private UlasanRepository ulasanRepository;

    @Override
    public Ulasan createUlasan(Ulasan ulasan) {
        return ulasanRepository.create(ulasan);
    }

    @Override
    public List<Ulasan> findAllUlasans() {
        return (List<Ulasan>) ulasanRepository.findAll();
    }

    @Override
    public List<Ulasan> findUlasansByUserId(String userId) {
        return ulasanRepository.findAllByUserId(userId);
    }

    @Override
    public List<Ulasan> findUlasansByGameId(String gameId) {
        return ulasanRepository.findAllByGameId(gameId);
    }

    @Override
    public Ulasan findUlasanById(String ulasanId) {
        return ulasanRepository.findById(ulasanId);
    }

    @Override
    public Ulasan updateUlasan(Ulasan ulasan) {
        return ulasanRepository.edit(ulasan);
    }

    @Override
    public Ulasan deleteUlasan(String ulasanId) {
        return ulasanRepository.delete(ulasanId);
    }
}

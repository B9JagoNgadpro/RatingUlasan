package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.repository.TanggapanUlasanRepository;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TanggapanUlasanServiceImpl implements TanggapanUlasanService {

    private final TanggapanUlasanRepository tanggapanUlasanRepository;

    private TanggapanUlasanServiceImpl(TanggapanUlasanRepository tanggapanUlasanRepository) {
        this.tanggapanUlasanRepository = tanggapanUlasanRepository;
    }

    @Override
    public TanggapanUlasan createTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return tanggapanUlasanRepository.save(tanggapanUlasan);
    }

    @Override
    public TanggapanUlasan updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return tanggapanUlasanRepository.save(tanggapanUlasan);
    }

    @Override
    public void deleteTanggapanUlasan(String id) {
        tanggapanUlasanRepository.deleteById(id);
    }

    @Override
    public Optional<TanggapanUlasan> findTanggapanUlasanById(String id) {
        return tanggapanUlasanRepository.findById(id);
    }

    @Override
    public Optional<TanggapanUlasan> findTanggapanUlasanByUlasanId(String ulasanId) {
        return Optional.ofNullable(tanggapanUlasanRepository.findByUlasanId(ulasanId));
    }

    @Override
    public List<TanggapanUlasan> findAllTanggapanUlasan() {
        return tanggapanUlasanRepository.findAll();
    }

    @Override
    public List<TanggapanUlasan> findAllTanggapanUlasanByPenjualId(String penjualId) {
        return tanggapanUlasanRepository.findAllByPenjualId(penjualId);
    }
}
package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import jagongadpro.gametime_ratingulasan.repository.TanggapanUlasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TanggapanUlasanServiceImpl implements TanggapanUlasanService {

    private final TanggapanUlasanRepository tanggapanUlasanRepository;

    @Autowired
    public TanggapanUlasanServiceImpl(TanggapanUlasanRepository tanggapanUlasanRepository) {
        this.tanggapanUlasanRepository = tanggapanUlasanRepository;
    }

    @Override
    public TanggapanUlasan createTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return tanggapanUlasanRepository.create(tanggapanUlasan);
    }

    @Override
    public TanggapanUlasan updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan) {
        return tanggapanUlasanRepository.update(tanggapanUlasan);
    }

    @Override
    public void deleteTanggapanUlasan(String id) {
        tanggapanUlasanRepository.delete(id);
    }

    @Override
    public TanggapanUlasan findTanggapanUlasanById(String id) {
        return tanggapanUlasanRepository.findById(id);
    }

    @Override
    public TanggapanUlasan findTanggapanUlasanByUlasanId(String ulasanId) {
        return tanggapanUlasanRepository.findByUlasanId(ulasanId);
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

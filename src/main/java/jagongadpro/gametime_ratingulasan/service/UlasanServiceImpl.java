package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UlasanServiceImpl implements UlasanService {

    @Autowired
    private UlasanRepository ulasanRepository;

    @Override
    public Ulasan createUlasan(Ulasan ulasan) {
        return ulasanRepository.save(ulasan);
    }

    @Override
    public List<Ulasan> findAllUlasans() {
        return ulasanRepository.findAll();
    }

    @Override
    public List<Ulasan> findUlasansByUserId(String idUser) {
        return ulasanRepository.findAllByIdUser(idUser);
    }

    @Override
    public List<Ulasan> findUlasansByGameId(String game) {
        return ulasanRepository.findAllByGame(game);
    }

    @Override
    public Optional<Ulasan> findUlasanById(String ulasanId) {
        return ulasanRepository.findById(ulasanId);
    }

    @Override
    public Ulasan updateUlasan(Ulasan ulasan) {
        return ulasanRepository.save(ulasan);
    }

    @Override
    public void deleteUlasan(String ulasanId) {
        ulasanRepository.deleteById(ulasanId);
    }
}


//package jagongadpro.gametime_ratingulasan.service;
//
//import jagongadpro.gametime_ratingulasan.model.Ulasan;
//import jagongadpro.gametime_ratingulasan.repository.UlasanRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UlasanServiceImpl implements UlasanService {
//    @Autowired
//    private UlasanRepository ulasanRepository;
//
//    @Override
//    public Ulasan createUlasan(Ulasan ulasan) {
//        return ulasanRepository.create(ulasan);
//    }
//
//    @Override
//    public List<Ulasan> findAllUlasans() {
//        return (List<Ulasan>) ulasanRepository.findAll();
//    }
//
//    @Override
//    public List<Ulasan> findUlasansByUserId(String userId) {
//        return ulasanRepository.findAllByUserId(userId);
//    }
//
//    @Override
//    public List<Ulasan> findUlasansByGameId(String gameId) {
//        return ulasanRepository.findAllByGameId(gameId);
//    }
//
//    @Override
//    public Ulasan findUlasanById(String ulasanId) {
//        try {
//            return ulasanRepository.findById(ulasanId);
//        }catch (IllegalArgumentException ex) {
//            return null;
//        }
//    }
//
//    @Override
//    public Ulasan updateUlasan(Ulasan ulasan) {
//        return ulasanRepository.edit(ulasan);
//    }
//
//    @Override
//    public Ulasan deleteUlasan(String ulasanId) {
//        return ulasanRepository.delete(ulasanId);
//    }
//}

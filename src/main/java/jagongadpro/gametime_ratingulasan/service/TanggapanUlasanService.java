package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import java.util.List;
import java.util.Optional;

public interface TanggapanUlasanService {
    TanggapanUlasan createTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
    TanggapanUlasan updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
    void deleteTanggapanUlasan(String id);
    Optional<TanggapanUlasan> findTanggapanUlasanById(String id);
    Optional<TanggapanUlasan> findTanggapanUlasanByUlasanId(String ulasanId);
    List<TanggapanUlasan> findAllTanggapanUlasan();
    List<TanggapanUlasan> findAllTanggapanUlasanByPenjualId(String penjualId);
}


//package jagongadpro.gametime_ratingulasan.service;
//
//import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
//import java.util.List;
//
//public interface TanggapanUlasanService {
//    TanggapanUlasan createTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
//    TanggapanUlasan updateTanggapanUlasan(TanggapanUlasan tanggapanUlasan);
//    void deleteTanggapanUlasan(String id);
//    TanggapanUlasan findTanggapanUlasanById(String id);
//    TanggapanUlasan findTanggapanUlasanByUlasanId(String ulasanId);
//    List<TanggapanUlasan> findAllTanggapanUlasan();
//    List<TanggapanUlasan> findAllTanggapanUlasanByPenjualId(String penjualId);
//}

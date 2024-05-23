package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TanggapanUlasanRepository extends JpaRepository<TanggapanUlasan, String> {
    TanggapanUlasan findByUlasanId(String ulasanId);
    List<TanggapanUlasan> findAllByPenjualId(String penjualId);
}
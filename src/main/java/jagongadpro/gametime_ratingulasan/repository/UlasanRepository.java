package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UlasanRepository extends JpaRepository<Ulasan, String> {
    List<Ulasan> findAllByIdUser(String idUser);
    List<Ulasan> findAllByGame(String game);
}
package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.model.BoughtGameId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoughtGameRepository extends JpaRepository<BoughtGame, BoughtGameId> {
    List<BoughtGame> findAllByIdUser(String idUser);
    BoughtGame findByIdGameAndIdUser(String idGame, String idUser);
}

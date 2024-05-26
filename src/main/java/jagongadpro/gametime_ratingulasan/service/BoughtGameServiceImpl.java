package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;
import jagongadpro.gametime_ratingulasan.repository.BoughtGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoughtGameServiceImpl implements BoughtGameService{

    private final BoughtGameRepository boughtGameRepository;

    private BoughtGameServiceImpl(BoughtGameRepository boughtGameRepository) {
        this.boughtGameRepository = boughtGameRepository;
    }

    public BoughtGame inputBoughtGame(BoughtGame boughtGame){
        return boughtGameRepository.save(boughtGame);
    }

    public List<BoughtGame> getBoughtGamesByUserId(String idUser){
        return boughtGameRepository.findAllByIdUser(idUser);
    }

    public BoughtGame boughtGameReviewed(String idGame, String idUser){
        BoughtGame findBoughtGame = boughtGameRepository.findByIdGameAndIdUser(idGame, idUser);
        findBoughtGame.setReviewed(true);
        return boughtGameRepository.save(findBoughtGame);
    }

    public BoughtGame boughtGameUnReviewed(String idGame, String idUser){
        BoughtGame findBoughtGame = boughtGameRepository.findByIdGameAndIdUser(idGame, idUser);
        findBoughtGame.setReviewed(false);
        return boughtGameRepository.save(findBoughtGame);
    }
}

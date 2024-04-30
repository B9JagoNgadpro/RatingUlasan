package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.Game;
import jagongadpro.gametime_ratingulasan.model.NotifPenjual;
import jagongadpro.gametime_ratingulasan.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService, Subject {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private Observer notifPenjualObserver;


    @Override
    public Game createGame(Game game) {
        return gameRepository.create(game);
    }

    @Override
    public Game updateGame(Game game) {
        return gameRepository.update(game);
    }

    @Override
    public void deleteGame(String id) {
        gameRepository.delete(id);
    }

    @Override
    public Game findGameById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> findGamesByCategoryId(String categoryId) {
        return gameRepository.findByCategoryId(categoryId);
    }

    @Override
    public List<Game> findGamesByUserId(String userId) {
        return gameRepository.findByUserId(userId);
    }

    @Override
    public void notifyObservers(String idPenjual, String notifikasi) { //Akses ke NotifPenjual
        notifPenjualObserver.update(notifikasi, idPenjual);
    }
}

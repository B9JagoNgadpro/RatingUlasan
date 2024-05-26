package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.BoughtGame;

import java.util.List;

public interface BoughtGameService {
    public BoughtGame inputBoughtGame(BoughtGame boughtGame);
    public List<BoughtGame> getBoughtGamesByUserId(String idUser);
    public BoughtGame boughtGameReviewed(String idGame, String idUser);
    public BoughtGame boughtGameUnReviewed(String idGame, String idUser);
}

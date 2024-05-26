package jagongadpro.gametime_ratingulasan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bought_game")
@IdClass(BoughtGameId.class)
public class BoughtGame {

    @Id
    @Column(name = "game_id")
    private String idGame;

    @Id
    @Column(name = "user_id")
    private String idUser;

    @Column(name = "is_reviewed")
    private boolean isReviewed;

    public BoughtGame(String idGame, String idUser) {
        this.idGame = idGame;
        this.idUser = idUser;
        this.isReviewed = false;
    }

    public BoughtGame() {}
}

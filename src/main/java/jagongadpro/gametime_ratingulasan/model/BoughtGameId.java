package jagongadpro.gametime_ratingulasan.model;

import java.io.Serializable;
import java.util.Objects;

public class BoughtGameId implements Serializable {
    private String idGame;
    private String idUser;

    // Default constructor
    public BoughtGameId() {}

    public BoughtGameId(String idGame, String idUser) {
        this.idGame = idGame;
        this.idUser = idUser;
    }

    // Getters, setters, equals, and hashCode methods
    public String getIdGame() {
        return idGame;
    }

    public void setIdGame(String idGame) {
        this.idGame = idGame;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtGameId that = (BoughtGameId) o;
        return Objects.equals(idGame, that.idGame) && Objects.equals(idUser, that.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGame, idUser);
    }
}

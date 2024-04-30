package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.Ulasan;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UlasanRepository {
    private UUID uuid;
    private List<Ulasan> ulasanData = new ArrayList<>();

    public Ulasan create(Ulasan ulasan){
        if (ulasan.getId() == null) {
            ulasan.setId(uuid.randomUUID().toString());
        }
        ulasanData.add(ulasan);
        return ulasan;
    }

    public List<Ulasan> findAll(){
        return new ArrayList<>(ulasanData); // Return a copy of the list to avoid external modifications
    }

    public List<Ulasan> findAllByUserId(String userId) {
        List<Ulasan> ulasanByUser = new ArrayList<>();
        for (Ulasan ulasan : ulasanData) {
            if (ulasan.getIdUser().equals(userId)) {
                ulasanByUser.add(ulasan);
            }
        }
        return ulasanByUser;
    }

    public List<Ulasan> findAllByGameId(String gameId) {
        List<Ulasan> ulasanByGame = new ArrayList<>();
        for (Ulasan ulasan : ulasanData) {
            if (ulasan.getGame().equals(gameId)) {
                ulasanByGame.add(ulasan);
            }
        }
        return ulasanByGame;
    }

    public Ulasan findById(String ulasanId) {
        return ulasanData.stream()
                .filter(ulasan -> ulasan.getId().equals(ulasanId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Invalid ulasan Id:" + ulasanId)
                );
    }

    public Ulasan edit(Ulasan editedUlasan) {
        Ulasan existingUlasan = findById(editedUlasan.getId());
        int indexOfUlasan = ulasanData.indexOf(existingUlasan);

        // Replace existing Ulasan with the edited one
        ulasanData.set(indexOfUlasan, editedUlasan);
        return editedUlasan;
    }

    public Ulasan delete(String ulasanId) {
        Ulasan ulasan = findById(ulasanId);
        ulasanData.remove(ulasan);
        return ulasan;
    }
}

package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.NotifPenjual;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class NotifPenjualRepository {
    private final Map<String, NotifPenjual> notifPenjualMap = new HashMap<>();

    public NotifPenjual save(NotifPenjual notifPenjual) {
        notifPenjualMap.put(notifPenjual.getIdPenjual(), notifPenjual);
        return notifPenjual;
    }

    public NotifPenjual findById(String idPenjual) {
        return notifPenjualMap.get(idPenjual);
    }

    public void deleteById(String idPenjual) {
        notifPenjualMap.remove(idPenjual);
    }

    public boolean existsById(String idPenjual) {
        return notifPenjualMap.containsKey(idPenjual);
    }
}

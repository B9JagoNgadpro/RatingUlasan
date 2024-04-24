package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.NotifPenjual;

public interface NotifPenjualService {
    NotifPenjual saveNotifPenjual(NotifPenjual notifPenjual);
    NotifPenjual findNotifPenjualById(String idPenjual);

    void deleteNotifPenjualById(String idPenjual);
    boolean checkIfNotifPenjualExists(String idPenjual);
}

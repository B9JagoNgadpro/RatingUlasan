package jagongadpro.gametime_ratingulasan.service;

import jagongadpro.gametime_ratingulasan.model.NotifPenjual;
import jagongadpro.gametime_ratingulasan.repository.NotifPenjualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifPenjualServiceImpl implements NotifPenjualService, Observer {
    @Autowired
    private NotifPenjualRepository notifPenjualRepository;

    @Override
    public NotifPenjual saveNotifPenjual(NotifPenjual notifPenjual) {
        return notifPenjualRepository.save(notifPenjual);
    }

    @Override
    public NotifPenjual findNotifPenjualById(String idPenjual) {
        return notifPenjualRepository.findById(idPenjual);
    }

    @Override
    public void deleteNotifPenjualById(String idPenjual) {
        notifPenjualRepository.deleteById(idPenjual);
    }

    @Override
    public boolean checkIfNotifPenjualExists(String idPenjual) {
        return notifPenjualRepository.existsById(idPenjual);
    }

    @Override
    public void update(String notif, String id) {
        NotifPenjual penjualDituju = notifPenjualRepository.findById(id);
        System.out.println(penjualDituju.getIdPenjual());
        penjualDituju.newNotif(notif);
    }
}

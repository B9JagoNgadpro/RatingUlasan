package jagongadpro.gametime_ratingulasan.model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NotifPenjual {
    String idPenjual;
    ArrayList<String> notifs;

    public void newNotif(String notif) {
        if (notifs == null) {
            notifs = new ArrayList<>();
        }
        notifs.add(notif);
    }
}

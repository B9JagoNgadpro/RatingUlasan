package jagongadpro.gametime_ratingulasan.repository;

import jagongadpro.gametime_ratingulasan.model.TanggapanUlasan;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TanggapanUlasanRepository {
    private final List<TanggapanUlasan> tanggapanUlasanList = new ArrayList<>();

    public TanggapanUlasan create(TanggapanUlasan tanggapanUlasan) {
        if (tanggapanUlasan == null){
            throw new IllegalArgumentException("tanggapanUlasan is null");
        }
        if (tanggapanUlasan.getId() == null) {
            tanggapanUlasan.setId(UUID.randomUUID().toString());
        }
        tanggapanUlasan.setDate(LocalDate.now());
        tanggapanUlasanList.add(tanggapanUlasan);
        return tanggapanUlasan;
    }

    public TanggapanUlasan findById(String id) {
        return tanggapanUlasanList.stream()
                .filter(tanggapan -> tanggapan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public TanggapanUlasan findByUlasanId(String ulasanId) {
        return tanggapanUlasanList.stream()
                .filter(tanggapan -> tanggapan.getUlasan().getId().equals(ulasanId))
                .findFirst()
                .orElse(null);
    }

    public List<TanggapanUlasan> findAllByPenjualId(String penjualId) {
        return tanggapanUlasanList.stream()
                .filter(tanggapan -> tanggapan.getPenjualId().equals(penjualId))
                .collect(Collectors.toList());
    }

    public List<TanggapanUlasan> findAll() {
        return new ArrayList<>(tanggapanUlasanList);
    }

    public TanggapanUlasan update(TanggapanUlasan tanggapanUlasan) {
        TanggapanUlasan existingTanggapanUlasan = findById(tanggapanUlasan.getId());
        if (existingTanggapanUlasan != null) {
            int index = tanggapanUlasanList.indexOf(existingTanggapanUlasan);
            tanggapanUlasanList.set(index, tanggapanUlasan);
            return tanggapanUlasan;
        } else {
            return null;
        }
    }

    public void delete(String id) {
        tanggapanUlasanList.removeIf(tanggapan -> tanggapan.getId().equals(id));
    }
}

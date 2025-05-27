import java.util.ArrayList;
import java.util.List;

class Garasi<T extends Kendaraan> {
    private List<T> daftarKendaraan;
    
    public Garasi() {
        this.daftarKendaraan = new ArrayList<>();
    }
    
    public void simpan(T kendaraan) {
        daftarKendaraan.add(kendaraan);
    }
    
    public T ambil(int index) {
        if (index >= 0 && index < daftarKendaraan.size()) {
            return daftarKendaraan.get(index);
        }
        return null;
    }
    
    public List<T> getSemua() {
        return daftarKendaraan;
    }
    
    public int hitungKendaraan() {
        return daftarKendaraan.size();
    }
}

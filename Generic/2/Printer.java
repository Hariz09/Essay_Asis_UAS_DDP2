// Generic Printer class

import java.util.ArrayList;
import java.util.List;

class Printer<T extends Cetakable> {
    private List<T> antreanCetak;
    
    public Printer() {
        this.antreanCetak = new ArrayList<>();
    }
    
    // Method untuk menambah item ke antrean
    public void tambahItem(T item) {
        antreanCetak.add(item);
    }
    
    // Method untuk mendapatkan semua cetakan
    public List<T> getSemuaCetakan() {
        return new ArrayList<>(antreanCetak);
    }
    
    // Generic method untuk mencetak item dari daftar
    public static void cetakItem(List<? extends Cetakable> daftar) {
        for (Cetakable item : daftar) {
            System.out.println(item.getIsiCetakan());
        }
    }
    
    // Method untuk mencetak semua item dalam antrean
    public void cetakSemuaItem() {
        cetakItem(antreanCetak);
    }
    
    // Method untuk mendapatkan jumlah item dalam antrean
    public int getJumlahItem() {
        return antreanCetak.size();
    }
    
    // Method untuk membersihkan antrean
    public void bersihkanAntrean() {
        antreanCetak.clear();
    }
}
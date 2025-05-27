import java.util.*;

// Generic Class Etalase
class Etalase<T> {
    private List<T> daftarBarang;
    
    public Etalase() {
        this.daftarBarang = new ArrayList<>();
    }
    
    // Method untuk menambah barang
    public void tambahBarang(T barang) {
        daftarBarang.add(barang);
    }
    
    // Method untuk mengambil barang berdasarkan indeks
    public T ambilBarang(int indeks) {
        if (indeks >= 0 && indeks < daftarBarang.size()) {
            return daftarBarang.get(indeks);
        }
        return null;
    }
    
    // Method untuk mendapatkan semua barang
    public List<T> getSemuaBarang() {
        return new ArrayList<>(daftarBarang);
    }
    
    // Method untuk menghitung jumlah barang
    public int hitungJumlahBarang() {
        return daftarBarang.size();
    }
}


// ========== MODEL ===========

// Class Elektronik
class Elektronik {
    private String nama;
    private String daya;
    
    public Elektronik(String nama, String daya) {
        this.nama = nama;
        this.daya = daya;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getDaya() {
        return daya;
    }
    
    @Override
    public String toString() {
        return "Nama: " + nama + ", Daya: " + daya;
    }
}

// Class Pakaian
class Pakaian {
    private String nama;
    private String ukuran;
    
    public Pakaian(String nama, String ukuran) {
        this.nama = nama;
        this.ukuran = ukuran;
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getUkuran() {
        return ukuran;
    }
    
    @Override
    public String toString() {
        return "Nama: " + nama + ", Ukuran: " + ukuran;
    }
}

// Class Buku
class Buku {
    private String judul;
    private String penulis;
    
    public Buku(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public String getPenulis() {
        return penulis;
    }
    
    @Override
    public String toString() {
        return "Judul: " + judul + ", Penulis: " + penulis;
    }
}

// ========== MAIN ==========
public class SistemEtalase {
    
    // Generic Method dengan wildcard
    public static void tampilkanInformasiBarang(List<? extends Object> daftarBarang) {
        for (Object barang : daftarBarang) {
            System.out.println(barang.toString());
        }
    }
    
    public static void main(String[] args) {
        // Membuat etalase untuk elektronik
        Etalase<Elektronik> etalaseElektronik = new Etalase<>();
        etalaseElektronik.tambahBarang(new Elektronik("Kipas Angin", "60W"));
        etalaseElektronik.tambahBarang(new Elektronik("TV LED", "150W"));
        
        // Membuat etalase untuk pakaian
        Etalase<Pakaian> etalasePakaian = new Etalase<>();
        etalasePakaian.tambahBarang(new Pakaian("Kaos UI", "M"));
        etalasePakaian.tambahBarang(new Pakaian("Jaket Fasilkom", "L"));
        
        // Membuat etalase untuk buku
        Etalase<Buku> etalaseBuku = new Etalase<>();
        etalaseBuku.tambahBarang(new Buku("DDP2 Mastery", "Kak Burhan"));
        etalaseBuku.tambahBarang(new Buku("OOP in Java", "Budi Santosa"));
        
        // Menampilkan isi setiap etalase
        System.out.println("=== ETALASE ELEKTRONIK ===");
        tampilkanInformasiBarang(etalaseElektronik.getSemuaBarang());
        
        System.out.println("=== ETALASE PAKAIAN ===");
        tampilkanInformasiBarang(etalasePakaian.getSemuaBarang());
        
        System.out.println("=== ETALASE BUKU ===");
        tampilkanInformasiBarang(etalaseBuku.getSemuaBarang());
        
        // Menampilkan informasi tambahan
        System.out.println("\n=== INFORMASI ETALASE ===");
        System.out.println("Jumlah barang elektronik: " + etalaseElektronik.hitungJumlahBarang());
        System.out.println("Jumlah pakaian: " + etalasePakaian.hitungJumlahBarang());
        System.out.println("Jumlah buku: " + etalaseBuku.hitungJumlahBarang());
    }
}
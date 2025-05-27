import java.util.*;

// 1. Class dasar Mahasiswa
class Mahasiswa {
    protected String nama;
    
    public Mahasiswa(String nama) {
        this.nama = nama;
    }
    
    public void tampilkan() {
        System.out.println("Mahasiswa: " + nama);
    }
    
    public String getNama() {
        return nama;
    }
}

// Subclass AsistenPraktikum
class AsistenPraktikum extends Mahasiswa {
    public AsistenPraktikum(String nama) {
        super(nama);
    }
    
    @Override
    public void tampilkan() {
        System.out.println("Asisten Praktikum: " + nama);
    }
}

// Subclass KetuaOrganisasi
class KetuaOrganisasi extends Mahasiswa {
    public KetuaOrganisasi(String nama) {
        super(nama);
    }
    
    @Override
    public void tampilkan() {
        System.out.println("Ketua Organisasi: " + nama);
    }
}

// 2. Generic class Kelompok<T>
class Kelompok<T> {
    private List<T> anggota;
    
    public Kelompok() {
        this.anggota = new ArrayList<>();
    }
    
    public void tambahAnggota(T anggota) {
        this.anggota.add(anggota);
    }
    
    public List<T> getAnggota() {
        return anggota;
    }
}

public class SistemPengelompokanMahasiswa {
    
    // 3. Generic method dengan upper bound wildcard
    public static void tampilkanAnggota(List<? extends Mahasiswa> daftar) {
        for (Mahasiswa mahasiswa : daftar) {
            mahasiswa.tampilkan();
        }
    }
    
    // 4. Method dengan lower bound wildcard
    public static void tambahKeKelompok(List<? super AsistenPraktikum> daftar, AsistenPraktikum a) {
        daftar.add(a);
    }
    
    public static void main(String[] args) {
        // 5. Implementasi pada method main
        
        // Membuat kelompok untuk AsistenPraktikum
        Kelompok<AsistenPraktikum> kelompokAsisten = new Kelompok<>();
        kelompokAsisten.tambahAnggota(new AsistenPraktikum("Ara"));
        kelompokAsisten.tambahAnggota(new AsistenPraktikum("Budi"));
        
        // Membuat kelompok untuk KetuaOrganisasi
        Kelompok<KetuaOrganisasi> kelompokKetua = new Kelompok<>();
        kelompokKetua.tambahAnggota(new KetuaOrganisasi("Citra"));
        kelompokKetua.tambahAnggota(new KetuaOrganisasi("Dino"));
        
        // Menampilkan anggota dari masing-masing kelompok
        System.out.println("=== KELOMPOK ASISTEN ===");
        tampilkanAnggota(kelompokAsisten.getAnggota());
        
        System.out.println("=== KELOMPOK KETUA ===");
        tampilkanAnggota(kelompokKetua.getAnggota());
        
        // Contoh penggunaan ? super
        System.out.println("=== ANGGOTA BARU DARI LIST UMUM MAHASISWA ===");
        
        // List umum mahasiswa (superclass dari AsistenPraktikum)
        List<Mahasiswa> listUmumMahasiswa = new ArrayList<>();
        listUmumMahasiswa.add(new Mahasiswa("Fani"));
        listUmumMahasiswa.add(new KetuaOrganisasi("Gilang"));
        
        // Menambahkan AsistenPraktikum ke list umum mahasiswa
        AsistenPraktikum eka = new AsistenPraktikum("Eka");
        tambahKeKelompok(listUmumMahasiswa, eka);
        System.out.println("Asisten Praktikum: " + eka.getNama());
        // * Eka bisa karena semua anggota adalah Mahasiswa
        
        System.out.println("\n=== ISI LIST UMUM MAHASISWA SETELAH PENAMBAHAN ===");
        tampilkanAnggota(listUmumMahasiswa);
        
        // Demonstrasi fleksibilitas wildcard
        // List Object juga bisa menerima AsistenPraktikum dengan ? super
        List<Object> listObject = new ArrayList<>();
        tambahKeKelompok(listObject, new AsistenPraktikum("Hani"));
        // AsistenPraktikum berhasil ditambahkan ke List<Object>
    }
}

/*
PENJELASAN DETAIL:

1. MENGAPA MENGGUNAKAN ? extends Mahasiswa:
   - List<? extends Mahasiswa> bisa menerima List<AsistenPraktikum>, List<KetuaOrganisasi>, dll
   - List<Mahasiswa> biasa HANYA bisa menerima Mahasiswa saja
   - Memberikan fleksibilitas untuk method yang hanya membaca data
   - Type safety tetap terjaga karena semua element pasti subclass dari Mahasiswa

2. FUNGSI ? super AsistenPraktikum:
   - Memungkinkan method menerima List yang tipe genericnya adalah superclass dari AsistenPraktikum
   - Contoh: List<Mahasiswa>, List<Object> bisa menerima AsistenPraktikum
   - Berguna untuk operasi penulisan/penambahan data

3. KAPAN MENGGUNAKAN LOWER BOUND WILDCARD (? super T):
   - Ketika ingin menambahkan/menulis data ke collection
   - Ketika ingin fleksibilitas untuk menerima collection dengan tipe superclass
   - Mengikuti prinsip PECS (Producer Extends, Consumer Super)
   - Producer (yang menghasilkan data) -> gunakan ? extends
   - Consumer (yang menerima data) -> gunakan ? super

4. KEUNTUNGAN SYSTEM INI:
   - Type safety: Compiler akan mencegah kesalahan tipe
   - Fleksibilitas: Satu method bisa bekerja dengan berbagai tipe collection
   - Reusability: Code bisa digunakan kembali untuk berbagai scenario
   - Maintainability: Mudah dipelihara dan dikembangkan
*/
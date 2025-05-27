import java.util.*;

// Class dasar Hewan
abstract class Hewan {
    protected String nama;
    protected String jenis;
    protected String jadwalMakan;
    
    public Hewan(String nama, String jenis, String jadwalMakan) {
        this.nama = nama;
        this.jenis = jenis;
        this.jadwalMakan = jadwalMakan;
    }
    
    // Method abstract untuk polymorphism
    public abstract String aksiUnik();
    
    // Getter methods
    public String getNama() { return nama; }
    public String getJenis() { return jenis; }
    public String getJadwalMakan() { return jadwalMakan; }
}

// Subclass Mamalia
class Mamalia extends Hewan {
    private double suhuTubuhNormal;
    
    public Mamalia(String nama, String jadwalMakan, double suhuTubuhNormal) {
        super(nama, "Mamalia", jadwalMakan);
        this.suhuTubuhNormal = suhuTubuhNormal;
    }
    
    @Override
    public String aksiUnik() {
        return "menyusui anaknya";
    }
    
    public double getSuhuTubuhNormal() { return suhuTubuhNormal; }
}

// Subclass Burung
class Burung extends Hewan {
    private String jenisParuh;
    
    public Burung(String nama, String jadwalMakan, String jenisParuh) {
        super(nama, "Burung", jadwalMakan);
        this.jenisParuh = jenisParuh;
    }
    
    @Override
    public String aksiUnik() {
        return "terbang tinggi mengelilingi kandang";
    }
    
    public String getJenisParuh() { return jenisParuh; }
}

// Subclass Reptil
class Reptil extends Hewan {
    private String jenisSisik;
    
    public Reptil(String nama, String jadwalMakan, String jenisSisik) {
        super(nama, "Reptil", jadwalMakan);
        this.jenisSisik = jenisSisik;
    }
    
    @Override
    public String aksiUnik() {
        return "berjemur di atas batu hangat";
    }
    
    public String getJenisSisik() { return jenisSisik; }
}

// Class PengelolaZoo
class PengelolaZoo {
    private List<Hewan> daftarHewan;
    
    public PengelolaZoo() {
        this.daftarHewan = new ArrayList<>();
    }
    
    // Method untuk menambahkan hewan
    public void tambahHewan(Hewan hewan) {
        daftarHewan.add(hewan);
    }
    
    // Method untuk menampilkan daftar hewan
    public void tampilkanDaftarHewan() {
        System.out.println("=== DAFTAR HEWAN DI SIZOPI ===");
        for (Hewan hewan : daftarHewan) {
            System.out.println("- " + hewan.getNama() + " (" + hewan.getJenis() + "): " + hewan.aksiUnik());
        }
    }
    
    // Method untuk menampilkan jadwal makan
    public void tampilkanJadwalMakan() {
        System.out.println("=== JADWAL MAKAN HARI INI ===");
        for (Hewan hewan : daftarHewan) {
            System.out.println(hewan.getNama() + " makan pukul " + hewan.getJadwalMakan());
        }
    }
    
    // Method untuk menampilkan hewan berdasarkan jenis
    public void tampilkanHewanBerdasarkanJenis(String jenis) {
        System.out.println("=== DAFTAR " + jenis.toUpperCase() + " ===");
        for (Hewan hewan : daftarHewan) {
            if (hewan.getJenis().equalsIgnoreCase(jenis)) {
                System.out.println("- " + hewan.getNama());
            }
        }
    }
}

// Main class untuk menjalankan program
public class SIZOPI {
    public static void main(String[] args) {
        // Membuat instance PengelolaZoo
        PengelolaZoo pengelola = new PengelolaZoo();
        
        // Menambahkan hewan-hewan ke zoo
        pengelola.tambahHewan(new Mamalia("Gajah", "08:00", 37.0));
        pengelola.tambahHewan(new Burung("Elang", "10:00", "Tajam"));
        pengelola.tambahHewan(new Reptil("Ular Sanca", "12:00", "Halus"));
        
        // Menampilkan daftar hewan dan aksi unik mereka
        pengelola.tampilkanDaftarHewan();
        System.out.println();
        
        // Menampilkan jadwal makan
        pengelola.tampilkanJadwalMakan();
    }
}
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

// Superclass - Abstract class untuk Pegawai
abstract class Pegawai {
    protected String nama;
    protected String id;
    protected double gajiPokok;
    
    public Pegawai(String nama, String id, double gajiPokok) {
        this.nama = nama;
        this.id = id;
        this.gajiPokok = gajiPokok;
    }
    
    // Getter methods (Encapsulation)
    public String getNama() {
        return nama;
    }
    
    public String getId() {
        return id;
    }
    
    public double getGajiPokok() {
        return gajiPokok;
    }
    
    // Abstract method untuk polymorphism
    public abstract double hitungTotalGaji();
    
    // Method untuk menampilkan info pegawai
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama + " | ID: " + id + " | Gaji Pokok: Rp" + gajiPokok);
        System.out.println("Total Gaji: Rp" + hitungTotalGaji());
    }
}

// Subclass untuk Pegawai Tetap
class PegawaiTetap extends Pegawai {
    private double tunjangan;
    
    public PegawaiTetap(String nama, String id, double gajiPokok, double tunjangan) {
        super(nama, id, gajiPokok);
        this.tunjangan = tunjangan;
    }
    
    @Override
    public double hitungTotalGaji() {
        return gajiPokok + tunjangan;
    }
    
    public double getTunjangan() {
        return tunjangan;
    }
}

// Subclass untuk Pegawai Kontrak
class PegawaiKontrak extends Pegawai {
    private int periodeKontrak; // dalam bulan
    
    public PegawaiKontrak(String nama, String id, double gajiPokok, int periodeKontrak) {
        super(nama, id, gajiPokok);
        this.periodeKontrak = periodeKontrak;
    }
    
    @Override
    public double hitungTotalGaji() {
        return gajiPokok;
    }
    
    public int getPeriodeKontrak() {
        return periodeKontrak;
    }
}

// Subclass untuk Freelancer
class Freelancer extends Pegawai {
    private int jumlahProyek;
    private double honorPerProyek;
    
    public Freelancer(String nama, String id, int jumlahProyek, double honorPerProyek) {
        super(nama, id, 0.0); // Freelancer tidak memiliki gaji pokok
        this.jumlahProyek = jumlahProyek;
        this.honorPerProyek = honorPerProyek;
    }
    
    @Override
    public double hitungTotalGaji() {
        return jumlahProyek * honorPerProyek;
    }
    
    public int getJumlahProyek() {
        return jumlahProyek;
    }
    
    public double getHonorPerProyek() {
        return honorPerProyek;
    }
}

// Class untuk mengelola sistem pegawai
class SistemManajemenPegawai {
    private List<Pegawai> daftarPegawai;
    
    public SistemManajemenPegawai() {
        this.daftarPegawai = new ArrayList<>();
    }
    
    public void tambahPegawai(Pegawai pegawai) {
        daftarPegawai.add(pegawai);
    }
    
    public void tampilkanSemuaPegawai() {
        System.out.println("=== DAFTAR PEGAWAI ===");
        for (Pegawai pegawai : daftarPegawai) {
            pegawai.tampilkanInfo();
        }
    }
    
    public double hitungTotalGajiSemuaPegawai() {
        double total = 0;
        for (Pegawai pegawai : daftarPegawai) {
            total += pegawai.hitungTotalGaji();
        }
        return total;
    }
    
    public void tampilkanTotalGaji() {
        System.out.println("=== TOTAL GAJI SEMUA PEGAWAI ===");
        System.out.println("Rp" + new DecimalFormat("###").format(hitungTotalGajiSemuaPegawai()) + ".0");
    }
}

// Main class untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        // Membuat instance sistem manajemen pegawai
        SistemManajemenPegawai sistem = new SistemManajemenPegawai();
        
        // Menambahkan data pegawai sesuai contoh
        PegawaiTetap budi = new PegawaiTetap("Budi", "PG001", 5000000.0, 2000000.0);
        PegawaiKontrak sari = new PegawaiKontrak("Sari", "PG002", 4000000.0, 6);
        Freelancer riko = new Freelancer("Riko", "PG003", 4, 750000.0);
        
        // Menambahkan pegawai ke sistem
        sistem.tambahPegawai(budi);
        sistem.tambahPegawai(sari);
        sistem.tambahPegawai(riko);
        
        // Menampilkan informasi pegawai dan total gaji
        sistem.tampilkanSemuaPegawai();
        sistem.tampilkanTotalGaji();
    }
}
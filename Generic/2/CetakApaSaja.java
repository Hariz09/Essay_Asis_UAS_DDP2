import java.util.*;

// Interface untuk objek yang dapat dicetak
interface Cetakable {
    String getIsiCetakan();
}

// Main class untuk menjalankan sistem
public class CetakApaSaja {
    public static void main(String[] args) {
        // Membuat printer untuk masing-masing jenis objek
        Printer<DokumenTeks> printerDokumen = new Printer<>();
        Printer<Foto> printerFoto = new Printer<>();
        Printer<LabelProduk> printerLabel = new Printer<>();
        
        // Menambahkan dokumen teks
        printerDokumen.tambahItem(new DokumenTeks("Kontrak Kerja", "Perjanjian ini dibuat pada tanggal..."));
        printerDokumen.tambahItem(new DokumenTeks("Surat Undangan", "Dengan hormat, kami mengundang Anda..."));
        
        // Menambahkan foto
        printerFoto.tambahItem(new Foto("liburan.png", "1080p"));
        printerFoto.tambahItem(new Foto("wisuda.jpg", "4K"));
        
        // Menambahkan label produk
        printerLabel.tambahItem(new LabelProduk("Kopi Arabika", "Rp25000"));
        printerLabel.tambahItem(new LabelProduk("Teh Hijau", "Rp15000"));
        
        // Mencetak semua dokumen
        System.out.println("=== MENCETAK DOKUMEN ===");
        Printer.cetakItem(printerDokumen.getSemuaCetakan());
        
        // Mencetak semua foto
        System.out.println("\n=== MENCETAK FOTO ===");
        Printer.cetakItem(printerFoto.getSemuaCetakan());
        
        // Mencetak semua label produk
        System.out.println("\n=== MENCETAK LABEL PRODUK ===");
        Printer.cetakItem(printerLabel.getSemuaCetakan());
        
        // Demonstrasi fleksibilitas sistem dengan mencetak campuran
        System.out.println("\n=== MENCETAK CAMPURAN (DEMO FLEKSIBILITAS) ===");
        List<Cetakable> campuran = new ArrayList<>();
        campuran.add(new DokumenTeks("Laporan Harian", "Kegiatan hari ini berjalan lancar..."));
        campuran.add(new Foto("meeting.jpg", "720p"));
        campuran.add(new LabelProduk("Gula Pasir", "Rp12000"));
        
        Printer.cetakItem(campuran);
        
        // Menampilkan statistik sistem
        System.out.println("\n=== STATISTIK SISTEM ===");
        System.out.println("Total dokumen dalam antrean: " + printerDokumen.getJumlahItem());
        System.out.println("Total foto dalam antrean: " + printerFoto.getJumlahItem());
        System.out.println("Total label dalam antrean: " + printerLabel.getJumlahItem());
    }
}
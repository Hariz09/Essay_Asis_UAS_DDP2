// Main.java - Kelas untuk menjalankan program
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi sistem SIZOPI
        SistemSIZOPI sizopi = new SistemSIZOPI();
        
        // Membuat data konsumsi sample
        List<DataKonsumsi> dataKonsumsi = Arrays.asList(
            new DataKonsumsi("Daging", 5.0),
            new DataKonsumsi("Buah", 3.5),
            new DataKonsumsi("Sayur", 6.0),
            new DataKonsumsi("Ikan", 4.0),
            new DataKonsumsi("Daging", 9.0),  // Tambahan daging
            new DataKonsumsi("Sayur", 5.0),   // Tambahan sayur
            new DataKonsumsi("Buah", 3.0),    // Tambahan buah
            new DataKonsumsi("Ikan", 4.0)     // Tambahan ikan
        );
        
        // Menambahkan data ke sistem
        sizopi.tambahKonsumsi(dataKonsumsi);
        
        // Menampilkan data konsumsi harian (diurutkan dari terbanyak)
        sizopi.tampilkanDataKonsumsi();
        System.out.println();
        
        // Menggunakan Lambda Expression dengan Functional Interface
        // Filter makanan di atas 10kg
        sizopi.tampilkanHasilFilter("MAKANAN DI ATAS 10 KG", 
            (jenisMakanan, totalKg) -> totalKg > 10.0);
        System.out.println();
        
        // Menampilkan makanan paling sedikit dikonsumsi
        sizopi.tampilkanMakananPalingSedikit();
        System.out.println();
        
        // Contoh penggunaan filter lainnya
        sizopi.tampilkanHasilFilter("MAKANAN BERBASIS PROTEIN", 
            (jenisMakanan, totalKg) -> jenisMakanan.equals("Daging") || jenisMakanan.equals("Ikan"));
        System.out.println();
        
        // Menampilkan statistik tambahan
        System.out.println("=== STATISTIK HARIAN ===");
        System.out.println("Total konsumsi hari ini: " + sizopi.getTotalKonsumsiHarian() + " kg");
        System.out.println("Jenis makanan yang dikonsumsi: " + sizopi.getJenisMakananDikonsumsi().size() + " jenis");
    }
}
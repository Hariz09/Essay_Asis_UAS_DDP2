// Membingung tiba tiba hashmap :v
import java.util.*;

// Functional Interface
@FunctionalInterface
interface FilterKonsumsi {
    boolean filter(String jenisMakanan, double totalKg);
}

// Class untuk menyimpan data konsumsi individual
class DataKonsumsi {
    private String jenisMakanan;
    private double kilogram;
    
    public DataKonsumsi(String jenisMakanan, double kilogram) {
        this.jenisMakanan = jenisMakanan;
        this.kilogram = kilogram;
    }
    
    public String getJenisMakanan() { return jenisMakanan; }
    public double getKilogram() { return kilogram; }
}

// Class utama sistem SIZOPI
public class SistemKonsumsiMakanan {
    private Map<String, Double> konsumsiHarian;
    
    public SistemKonsumsiMakanan() {
        this.konsumsiHarian = new HashMap<>();
    }
    
    // Method untuk menambah data konsumsi
    public void tambahKonsumsi(List<DataKonsumsi> dataList) {
        for (DataKonsumsi data : dataList) {
            konsumsiHarian.merge(data.getJenisMakanan(), data.getKilogram(), Double::sum);
        }
    }
    
    // Method untuk menampilkan data terurut berdasarkan konsumsi terbanyak
    public void tampilkanDataKonsumsi() {
        System.out.println("=== DATA KONSUMSI HARI INI ===");
        konsumsiHarian.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    // Method untuk filter menggunakan Functional Interface
    public void tampilkanDenganFilter(String judul, FilterKonsumsi filter) {
        System.out.println("=== " + judul + " ===");
        konsumsiHarian.entrySet().stream()
            .filter(entry -> filter.filter(entry.getKey(), entry.getValue()))
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    public static void main(String[] args) {
        SistemKonsumsiMakanan sistem = new SistemKonsumsiMakanan();
        
        // Data konsumsi sample
        List<DataKonsumsi> dataHarian = Arrays.asList(
            new DataKonsumsi("Daging", 5.0),
            new DataKonsumsi("Buah", 3.5),
            new DataKonsumsi("Sayur", 7.0),
            new DataKonsumsi("Ikan", 4.0),
            new DataKonsumsi("Daging", 9.0),
            new DataKonsumsi("Buah", 3.0),
            new DataKonsumsi("Sayur", 4.0),
            new DataKonsumsi("Ikan", 4.0)
        );
        
        sistem.tambahKonsumsi(dataHarian);
        
        // Tampilkan semua data
        sistem.tampilkanDataKonsumsi();
        System.out.println();
        
        // Filter makanan di atas 10kg menggunakan Lambda Expression
        sistem.tampilkanDenganFilter("MAKANAN DI ATAS 10 KG", 
            (jenis, kg) -> kg > 10.0);
        System.out.println();
        
        // Tampilkan makanan paling sedikit dikonsumsi
        sistem.tampilkanDenganFilter("MAKANAN PALING SEDIKIT DIKONSUMSI",
            (jenis, kg) -> kg == sistem.konsumsiHarian.values().stream()
                .min(Double::compareTo).orElse(0.0));
    }
}
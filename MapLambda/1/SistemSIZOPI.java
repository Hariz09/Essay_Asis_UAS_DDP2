// SistemSIZOPI.java - Kelas utama sistem menggunakan Stream Collectors
import java.util.*;
import java.util.stream.Collectors;

class SistemSIZOPI {
    private List<DataKonsumsi> dataKonsumsiList;
    
    public SistemSIZOPI() {
        this.dataKonsumsiList = new ArrayList<>();
    }
    
    // Method untuk menambah data konsumsi
    public void tambahKonsumsi(DataKonsumsi data) {
        dataKonsumsiList.add(data);
    }
    
    // Method untuk menambah multiple data konsumsi
    public void tambahKonsumsi(List<DataKonsumsi> dataList) {
        dataKonsumsiList.addAll(dataList);
    }
    
    // Method untuk mendapatkan map konsumsi menggunakan Stream Collector
    public Map<String, Double> getKonsumsiHarian() {
        return dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summingDouble(DataKonsumsi::getJumlahKg)
            ));
    }
    
    // Method untuk menampilkan semua data konsumsi (diurutkan dari terbanyak ke terkecil)
    public void tampilkanDataKonsumsi() {
        System.out.println("=== DATA KONSUMSI HARI INI ===");
        dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summingDouble(DataKonsumsi::getJumlahKg)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    // Method untuk filter menggunakan Functional Interface dan Lambda
    public void tampilkanHasilFilter(String judul, FilterKonsumsi filter) {
        System.out.println("=== " + judul.toUpperCase() + " ===");
        dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summingDouble(DataKonsumsi::getJumlahKg)
            ))
            .entrySet().stream()
            .filter(entry -> filter.test(entry.getKey(), entry.getValue()))
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    // Method untuk mencari makanan dengan konsumsi paling sedikit
    public void tampilkanMakananPalingSedikit() {
        System.out.println("=== MAKANAN PALING SEDIKIT DIKONSUMSI ===");
        dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summingDouble(DataKonsumsi::getJumlahKg)
            ))
            .entrySet().stream()
            .min(Map.Entry.comparingByValue())
            .ifPresent(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    // Method untuk mendapatkan total konsumsi keseluruhan
    public double getTotalKonsumsiHarian() {
        return dataKonsumsiList.stream()
            .collect(Collectors.summingDouble(DataKonsumsi::getJumlahKg));
    }
    
    // Method untuk mendapatkan jenis makanan yang dikonsumsi
    public Set<String> getJenisMakananDikonsumsi() {
        return dataKonsumsiList.stream()
            .map(DataKonsumsi::getJenisMakanan)
            .collect(Collectors.toSet());
    }
    
    // Method untuk mendapatkan rata-rata konsumsi per jenis makanan
    public Map<String, Double> getRataRataKonsumsi() {
        return dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.averagingDouble(DataKonsumsi::getJumlahKg)
            ));
    }
    
    // Method untuk mendapatkan jumlah entry per jenis makanan
    public Map<String, Long> getJumlahEntryPerJenis() {
        return dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.counting()
            ));
    }
    
    // Method untuk mendapatkan statistik lengkap per jenis makanan
    public Map<String, DoubleSummaryStatistics> getStatistikKonsumsi() {
        return dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summarizingDouble(DataKonsumsi::getJumlahKg)
            ));
    }
    
    // Method untuk menampilkan top N makanan yang paling banyak dikonsumsi
    public void tampilkanTopMakanan(int n) {
        System.out.println("=== TOP " + n + " MAKANAN TERBANYAK ===");
        dataKonsumsiList.stream()
            .collect(Collectors.groupingBy(
                DataKonsumsi::getJenisMakanan,
                Collectors.summingDouble(DataKonsumsi::getJumlahKg)
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .limit(n)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue() + " kg"));
    }
    
    // Method untuk menampilkan statistik detail
    public void tampilkanStatistikDetail() {
        System.out.println("=== STATISTIK DETAIL KONSUMSI ===");
        Map<String, DoubleSummaryStatistics> stats = getStatistikKonsumsi();
        
        stats.forEach((jenis, stat) -> {
            System.out.println("\n" + jenis + ":");
            System.out.println("  Total: " + stat.getSum() + " kg");
            System.out.println("  Rata-rata: " + String.format("%.2f", stat.getAverage()) + " kg");
            System.out.println("  Minimum: " + stat.getMin() + " kg");
            System.out.println("  Maximum: " + stat.getMax() + " kg");
            System.out.println("  Jumlah entry: " + stat.getCount());
        });
    }
    
    // Method untuk reset data
    public void resetData() {
        dataKonsumsiList.clear();
    }
    
    // Method untuk mendapatkan semua data konsumsi
    public List<DataKonsumsi> getAllDataKonsumsi() {
        return new ArrayList<>(dataKonsumsiList);
    }
}
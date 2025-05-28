import java.util.*;
import java.util.stream.Collectors;

@FunctionalInterface
interface FilterMinuman {
    boolean test(Minuman m);
}

class Minuman {
    private String nama, jenis;
    private double harga, rating;
    private boolean tersedia;
    
    public Minuman(String nama, String jenis, double harga, double rating, boolean tersedia) {
        this.nama = nama; this.jenis = jenis; this.harga = harga;
        this.rating = rating; this.tersedia = tersedia;
    }
    
    public String getNama() { return nama; }
    public String getJenis() { return jenis; }
    public double getHarga() { return harga; }
    public double getRating() { return rating; }
    public boolean isTersedia() { return tersedia; }
    
    @Override
    public String toString() {
        return String.format("%s (%.1f - Rp%.0f)", nama, rating, harga);
    }
}

public class KylauinRecommender {
    
    public static List<Minuman> cariMinuman(List<Minuman> menu, FilterMinuman filter) {
        return menu.stream().filter(filter::test).collect(Collectors.toList());
    }
    
    public static Optional<Minuman> cariMinumanTerbaik(List<Minuman> menu) {
        return menu.stream()
                   .filter(Minuman::isTersedia)
                   .max(Comparator.comparingDouble(Minuman::getRating));
    }
    
    public static List<String> getNamaMinumanTersedia(List<Minuman> menu, double minRating) {
        return menu.stream()
                   .filter(m -> m.isTersedia() && m.getRating() >= minRating)
                   .map(Minuman::getNama)
                   .collect(Collectors.toList());
    }
    
    public static void main(String[] args) {
        List<Minuman> menu = Arrays.asList(
            new Minuman("Espresso", "kopi", 15000, 4.2, true),
            new Minuman("Americano", "kopi", 18000, 3.8, true),
            new Minuman("Cappuccino", "kopi", 22000, 4.1, false),
            new Minuman("Matcha Latte", "non-kopi", 25000, 4.5, true),
            new Minuman("Caramel Macchiato", "kopi", 28000, 4.9, true),
            new Minuman("Teh Tarik", "non-kopi", 12000, 3.5, true),
            new Minuman("Chocolate Frappé", "non-kopi", 30000, 4.3, false),
            new Minuman("Vanilla Latte", "kopi", 24000, 4.0, true)
        );
        
        System.out.println("=== KYLAUIN COFFEE RECOMMENDER ===\n");
        
        // Minuman kopi rating tinggi (≥ 4.0)
        System.out.println("MINUMAN KOPI RATING TINGGI (>= 4.0):");
        cariMinuman(menu, m -> m.getJenis().equals("kopi") && m.isTersedia() && m.getRating() >= 4.0)
            .forEach(m -> System.out.println("- " + m));
        
        System.out.println("\n=== MINUMAN TERSEDIA DENGAN RATING >= 4.0 ===");
        getNamaMinumanTersedia(menu, 4.0).forEach(System.out::println);
        
        // Minuman terbaik
        cariMinumanTerbaik(menu).ifPresentOrElse(
            terbaik -> System.out.println("\nMinuman terbaik: " + terbaik.getNama() + " (" + terbaik.getRating() + ")"),
            () -> System.out.println("\nTidak ada minuman tersedia.")
        );
        
        System.out.println("\n" + "=".repeat(50));
        
        // Filter demonstrations
        System.out.println("\nMINUMAN EKONOMIS (< Rp 20.000):");
        cariMinuman(menu, m -> m.isTersedia() && m.getHarga() < 20000)
            .forEach(m -> System.out.println("- " + m));
        
        System.out.println("\nMINUMAN NON-KOPI TERSEDIA:");
        cariMinuman(menu, m -> m.isTersedia() && !m.getJenis().equals("kopi"))
            .forEach(m -> System.out.println("- " + m));
        
        // Statistics
        System.out.println("\nSTATISTIK MENU:");
        var tersedia = menu.stream().filter(Minuman::isTersedia);
        
        System.out.println("- Total tersedia: " + tersedia.count());
        System.out.println("- Rata-rata rating: " + String.format("%.1f", 
            menu.stream().filter(Minuman::isTersedia).mapToDouble(Minuman::getRating).average().orElse(0.0)));
        System.out.println("- Harga termurah: Rp " + String.format("%.0f", 
            menu.stream().filter(Minuman::isTersedia).mapToDouble(Minuman::getHarga).min().orElse(0.0)));
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Selamat menikmati minuman Anda di Kylauin!");
    }
}
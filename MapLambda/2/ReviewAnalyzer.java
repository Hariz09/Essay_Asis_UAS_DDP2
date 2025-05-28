import java.util.*;
import java.util.stream.Collectors;

@FunctionalInterface
interface RatingCalculator {
    double hitungRataRata(List<Integer> rating);
}

public class ReviewAnalyzer {
    public static void main(String[] args) {
        // Data rating produk
        Map<String, List<Integer>> ratingProduk = new HashMap<>();
        ratingProduk.put("Sapu Ajaib", Arrays.asList(4, 5, 4, 4, 5));
        ratingProduk.put("Meja Lipat", Arrays.asList(3, 4, 3, 4, 3));
        ratingProduk.put("Headset GamerZ", Arrays.asList(5, 5, 5, 4, 5));

        // Implementasi lambda untuk RatingCalculator
        RatingCalculator rc = list -> list.stream()
                .mapToInt(i -> i)
                .average()
                .orElse(0.0);

        // Tampilkan rekomendasi
        tampilkanRekomendasi(ratingProduk, rc);
    }

    static void tampilkanRekomendasi(Map<String, List<Integer>> data, RatingCalculator rc) {
        System.out.println("=== DAFTAR PRODUK & RATING ===");

        // Tampilkan semua produk dengan rata-rata rating
        data.entrySet().stream()
                .forEach(entry -> {
                    String produk = entry.getKey();
                    List<Integer> ratings = entry.getValue();
                    double rataRata = rc.hitungRataRata(ratings);
                    String status = rataRata >= 4.0 ? " Direkomendasikan" : "";
                    System.out.printf("%s: %.1f%s%n", produk, rataRata, status);
                });

        System.out.println("\n=== PRODUK YANG DIREKOMENDASIKAN ===");

        // Filter, urutkan, dan collect produk yang direkomendasikan
        List<Map.Entry<String, List<Integer>>> produkRekomendasi = data.entrySet().stream()
                .filter(entry -> rc.hitungRataRata(entry.getValue()) >= 4.0)
                .sorted((e1, e2) -> Double.compare(
                        rc.hitungRataRata(e2.getValue()),
                        rc.hitungRataRata(e1.getValue())))
                .collect(Collectors.toList());

        // Tampilkan hasil collect
        produkRekomendasi.stream()
                .forEach(entry -> {
                    String produk = entry.getKey();
                    double rataRata = rc.hitungRataRata(entry.getValue());
                    System.out.printf("%s (%.1f)%n", produk, rataRata);
                });
    }
}
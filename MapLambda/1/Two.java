import java.util.HashMap;
import java.util.Map;

public class Two {
    private static Map<String, Double> hashMap = new HashMap<>();
    public static void main(String[] args) {
        
        // Example usage to avoid "never read" warning
        hashMap.put("Daging", 14.0);
        hashMap.put("Sayur", 11.0);
        hashMap.put("Ikan", 8.0);
        hashMap.put("Buah", 6.5);
        FilterKonsumsi filterLebih10 = (a,b) -> b > 10;
        FilterKonsumsi filterPalingSedikit = (a,b) -> {
            double min = hashMap.values().stream().min(Double::compareTo).orElse(Double.MAX_VALUE);
            return b.equals(min);
        };
        filter(filterLebih10);
        System.out.println();
        filter(filterPalingSedikit);
    }
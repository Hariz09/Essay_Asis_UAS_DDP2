import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReviewAnalyzerTest {
    
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testTampilkanRekomendasi() {
        // Arrange
        Map<String, List<Integer>> data = new HashMap<>();
        data.put("High Rating", Arrays.asList(5, 5, 4, 5));
        data.put("Low Rating", Arrays.asList(2, 3, 2, 3));
        data.put("Medium Rating", Arrays.asList(4, 4, 4, 4));
        
        RatingCalculator rc = list -> list.stream().mapToInt(i -> i).average().orElse(0.0);
        
        // Act
        ReviewAnalyzer.tampilkanRekomendasi(data, rc);
        
        // Assert
        String result = output.toString();
        
        // Check header sections exist
        assertTrue(result.contains("=== DAFTAR PRODUK & RATING ==="));
        assertTrue(result.contains("=== PRODUK YANG DIREKOMENDASIKAN ==="));
        
        // Check all products are listed with ratings
        assertTrue(result.contains("High Rating: 4.8 Direkomendasikan"));
        assertTrue(result.contains("Low Rating: 2.5"));
        assertTrue(result.contains("Medium Rating: 4.0 Direkomendasikan"));
        
        // Check recommended products section (sorted by rating desc)
        assertTrue(result.contains("High Rating (4.8)"));
        assertTrue(result.contains("Medium Rating (4.0)"));
        assertFalse(result.contains("Low Rating (2.5)"));
        
        // Check sorting order (High Rating should appear before Medium Rating)
        int highIndex = result.lastIndexOf("High Rating (4.8)");
        int mediumIndex = result.lastIndexOf("Medium Rating (4.0)");
        assertTrue(highIndex < mediumIndex);
    }
}
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class SistemPengelompokanMahasiswaTest {
    
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    
    // Test untuk class Mahasiswa
    @Test
    void testMahasiswaCreation() {
        Mahasiswa mahasiswa = new Mahasiswa("John");
        assertEquals("John", mahasiswa.getNama());
    }
    
    @Test
    void testMahasiswaTampilkan() {
        Mahasiswa mahasiswa = new Mahasiswa("Alice");
        mahasiswa.tampilkan();
        assertEquals("Mahasiswa: Alice", outputStreamCaptor.toString().trim());
    }
    
    // Test untuk class AsistenPraktikum
    @Test
    void testAsistenPraktikumCreation() {
        AsistenPraktikum asisten = new AsistenPraktikum("Bob");
        assertEquals("Bob", asisten.getNama());
        assertTrue(asisten instanceof Mahasiswa);
    }
    
    @Test
    void testAsistenPraktikumTampilkan() {
        AsistenPraktikum asisten = new AsistenPraktikum("Charlie");
        asisten.tampilkan();
        assertEquals("Asisten Praktikum: Charlie", outputStreamCaptor.toString().trim());
    }
    
    // Test untuk class KetuaOrganisasi
    @Test
    void testKetuaOrganisasiCreation() {
        KetuaOrganisasi ketua = new KetuaOrganisasi("Eva");
        assertEquals("Eva", ketua.getNama());
        assertTrue(ketua instanceof Mahasiswa);
    }
    
    @Test
    void testKetuaOrganisasiTampilkan() {
        KetuaOrganisasi ketua = new KetuaOrganisasi("Frank");
        ketua.tampilkan();
        assertEquals("Ketua Organisasi: Frank", outputStreamCaptor.toString().trim());
    }
    
    // Test untuk generic class Kelompok
    @Test
    void testKelompokEmptyCreation() {
        Kelompok<Mahasiswa> kelompok = new Kelompok<>();
        assertTrue(kelompok.getAnggota().isEmpty());
    }
    
    @Test
    void testKelompokTambahAnggotaSingle() {
        Kelompok<AsistenPraktikum> kelompok = new Kelompok<>();
        AsistenPraktikum asisten = new AsistenPraktikum("Henry");
        
        kelompok.tambahAnggota(asisten);
        
        assertEquals(1, kelompok.getAnggota().size());
        assertEquals("Henry", kelompok.getAnggota().get(0).getNama());
    }
    
    @Test
    void testKelompokTambahAnggotaMultiple() {
        Kelompok<KetuaOrganisasi> kelompok = new Kelompok<>();
        KetuaOrganisasi ketua1 = new KetuaOrganisasi("Ivy");
        KetuaOrganisasi ketua2 = new KetuaOrganisasi("Jack");
        
        kelompok.tambahAnggota(ketua1);
        kelompok.tambahAnggota(ketua2);
        
        assertEquals(2, kelompok.getAnggota().size());
        assertEquals("Ivy", kelompok.getAnggota().get(0).getNama());
        assertEquals("Jack", kelompok.getAnggota().get(1).getNama());
    }
    
    // Test untuk upper bound wildcard (? extends)
    @Test
    void testTampilkanAnggotaAsistenPraktikum() {
        List<AsistenPraktikum> listAsisten = new ArrayList<>();
        listAsisten.add(new AsistenPraktikum("Kate"));
        listAsisten.add(new AsistenPraktikum("Leo"));
        
        SistemPengelompokanMahasiswa.tampilkanAnggota(listAsisten);
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Asisten Praktikum: Kate"));
        assertTrue(output.contains("Asisten Praktikum: Leo"));
    }
    
    @Test
    void testTampilkanAnggotaKetuaOrganisasi() {
        List<KetuaOrganisasi> listKetua = new ArrayList<>();
        listKetua.add(new KetuaOrganisasi("Mia"));
        listKetua.add(new KetuaOrganisasi("Noah"));
        
        SistemPengelompokanMahasiswa.tampilkanAnggota(listKetua);
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Ketua Organisasi: Mia"));
        assertTrue(output.contains("Ketua Organisasi: Noah"));
    }
    
    @Test
    void testTampilkanAnggotaMixedList() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        listMahasiswa.add(new Mahasiswa("Olivia"));
        listMahasiswa.add(new AsistenPraktikum("Paul"));
        listMahasiswa.add(new KetuaOrganisasi("Quinn"));
        
        SistemPengelompokanMahasiswa.tampilkanAnggota(listMahasiswa);
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Mahasiswa: Olivia"));
        assertTrue(output.contains("Asisten Praktikum: Paul"));
        assertTrue(output.contains("Ketua Organisasi: Quinn"));
    }
    
    // Test untuk lower bound wildcard (? super)
    @Test
    void testTambahKeKelompokMahasiswa() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        AsistenPraktikum asisten = new AsistenPraktikum("Rachel");
        
        SistemPengelompokanMahasiswa.tambahKeKelompok(listMahasiswa, asisten);
        
        assertEquals(1, listMahasiswa.size());
        assertEquals("Rachel", listMahasiswa.get(0).getNama());
        assertTrue(listMahasiswa.get(0) instanceof AsistenPraktikum);
    }
    
    @Test
    void testTambahKeKelompokObject() {
        List<Object> listObject = new ArrayList<>();
        AsistenPraktikum asisten = new AsistenPraktikum("Sam");
        
        SistemPengelompokanMahasiswa.tambahKeKelompok(listObject, asisten);
        
        assertEquals(1, listObject.size());
        assertTrue(listObject.get(0) instanceof AsistenPraktikum);
        assertEquals("Sam", ((AsistenPraktikum) listObject.get(0)).getNama());
    }
    
    @Test
    void testTambahKeKelompokNonEmpty() {
        List<Mahasiswa> listMahasiswa = new ArrayList<>();
        listMahasiswa.add(new Mahasiswa("Existing"));
        
        AsistenPraktikum asisten = new AsistenPraktikum("Tina");
        SistemPengelompokanMahasiswa.tambahKeKelompok(listMahasiswa, asisten);
        
        assertEquals(2, listMahasiswa.size());
        assertEquals("Existing", listMahasiswa.get(0).getNama());
        assertEquals("Tina", listMahasiswa.get(1).getNama());
    }
    
    // Test integrasi lengkap
    @Test
    void testCompleteWorkflow() {
        // Setup kelompok asisten
        Kelompok<AsistenPraktikum> kelompokAsisten = new Kelompok<>();
        kelompokAsisten.tambahAnggota(new AsistenPraktikum("Uma"));
        kelompokAsisten.tambahAnggota(new AsistenPraktikum("Victor"));
        
        // Setup kelompok ketua
        Kelompok<KetuaOrganisasi> kelompokKetua = new Kelompok<>();
        kelompokKetua.tambahAnggota(new KetuaOrganisasi("Wendy"));
        kelompokKetua.tambahAnggota(new KetuaOrganisasi("Xavier"));
        
        // Verify sizes
        assertEquals(2, kelompokAsisten.getAnggota().size());
        assertEquals(2, kelompokKetua.getAnggota().size());
        
        // Test wildcard methods
        SistemPengelompokanMahasiswa.tampilkanAnggota(kelompokAsisten.getAnggota());
        SistemPengelompokanMahasiswa.tampilkanAnggota(kelompokKetua.getAnggota());
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Asisten Praktikum: Uma"));
        assertTrue(output.contains("Asisten Praktikum: Victor"));
        assertTrue(output.contains("Ketua Organisasi: Wendy"));
        assertTrue(output.contains("Ketua Organisasi: Xavier"));
    }
    
    @Test
    void testEmptyListBehavior() {
        List<AsistenPraktikum> emptyList = new ArrayList<>();
        
        assertDoesNotThrow(() -> {
            SistemPengelompokanMahasiswa.tampilkanAnggota(emptyList);
        });
        
        // Should produce no output for empty list
        String output = outputStreamCaptor.toString();
        assertTrue(output.isEmpty() || output.trim().isEmpty());
    }
    
    @Test
    void testPolymorphismBehavior() {
        List<Mahasiswa> mixedList = new ArrayList<>();
        mixedList.add(new Mahasiswa("Regular"));
        mixedList.add(new AsistenPraktikum("Assistant"));
        mixedList.add(new KetuaOrganisasi("Leader"));
        
        // Test polymorphic behavior through tampilkan()
        SistemPengelompokanMahasiswa.tampilkanAnggota(mixedList);
        
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Mahasiswa: Regular"));
        assertTrue(output.contains("Asisten Praktikum: Assistant"));
        assertTrue(output.contains("Ketua Organisasi: Leader"));
    }
}
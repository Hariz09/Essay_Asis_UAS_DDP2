// ===== TEMPLATE =====
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Garasi<Mobil> garasiMobil = new Garasi<>();
        Garasi<Motor> garasiMotor = new Garasi<>();
        Garasi<Truk> garasiTruk = new Garasi<>();

        garasiMobil.simpan(new Mobil("Toyota", 4));
        garasiMobil.simpan(new Mobil("Honda", 2));

        garasiMotor.simpan(new Motor("Yamaha", true));
        garasiMotor.simpan(new Motor("Suzuki", false));

        garasiTruk.simpan(new Truk("Hino", 8.0));
        garasiTruk.simpan(new Truk("Mitsubishi", 5.5));

        System.out.println("=== GARASI MOBIL ===");
        cetakSemuaKendaraan(garasiMobil.getSemua());

        System.out.println("\n=== GARASI MOTOR ===");
        cetakSemuaKendaraan(garasiMotor.getSemua());

        System.out.println("\n=== GARASI TRUK ===");
        cetakSemuaKendaraan(garasiTruk.getSemua());

        tambahKendaraanKeList(garasiMobil.getSemua(), new Mobil("Daihatsu", 4));
        System.out.println("\n=== Setelah menambah mobil ke garasi mobil ===");
        cetakSemuaKendaraan(garasiMobil.getSemua());

        List<Motor> daftarMotor = garasiMotor.getSemua();
        // daftar motor baru
        Garasi<Motor> garasiMotorBaru = new Garasi<>();
        garasiMotorBaru.simpan(new Motor("Kawasaki", true));
        garasiMotorBaru.simpan(new Motor("Honda", false));
        salinKendaraan(daftarMotor, garasiMotorBaru.getSemua());
        System.out.println("\n=== Garasi Motor Baru ===");
        cetakSemuaKendaraan(garasiMotorBaru.getSemua());
    }

    public static <T extends Kendaraan> void cetakSemuaKendaraan(List<T> daftar) {
        for (T k : daftar) {
            System.out.println(k.info());
        }
    }

    // Wildcard method untuk menambahkan mobil ke list yang bertipe super Mobil
    public static void tambahKendaraanKeList(List<? super Mobil> daftar, Mobil m) {
        daftar.add(m);
    }

    // Kombinasi wildcard: Salin dari satu list ke list lain
    public static <T> void salinKendaraan(List<? extends T> sumber, List<? super T> target) {
        for (T item : sumber) {
            target.add(item);
        }
    }
}

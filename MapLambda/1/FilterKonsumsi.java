// FilterKonsumsi.java - Functional Interface
@FunctionalInterface
interface FilterKonsumsi {
    boolean test(String jenisMakanan, Double totalKg);
}
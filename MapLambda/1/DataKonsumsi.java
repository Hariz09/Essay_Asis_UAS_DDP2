// DataKonsumsi.java - Model untuk data konsumsi
class DataKonsumsi {
    private String jenisMakanan;
    private double jumlahKg;
    
    public DataKonsumsi(String jenisMakanan, double jumlahKg) {
        this.jenisMakanan = jenisMakanan;
        this.jumlahKg = jumlahKg;
    }
    
    // Getters
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    
    public double getJumlahKg() {
        return jumlahKg;
    }
    
    // Setters
    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }
    
    public void setJumlahKg(double jumlahKg) {
        this.jumlahKg = jumlahKg;
    }
    
    @Override
    public String toString() {
        return "DataKonsumsi{jenisMakanan='" + jenisMakanan + "', jumlahKg=" + jumlahKg + "}";
    }
}
class Truk extends Kendaraan {
    private double kapasitasTon;
    
    public Truk(String merk, double kapasitasTon) {
        super(merk);
        this.kapasitasTon = kapasitasTon;
    }
    
    public double getKapasitasTon() {
        return kapasitasTon;
    }
    
    @Override
    public String info() {
        return "Truk [Merk: " + merk + ", Kapasitas: " + kapasitasTon + " ton]";
    }
}
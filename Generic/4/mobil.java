class Mobil extends Kendaraan {
    private int jumlahPintu;
    
    public Mobil(String merk, int jumlahPintu) {
        super(merk);
        this.jumlahPintu = jumlahPintu;
    }
    
    public int getJumlahPintu() {
        return jumlahPintu;
    }
    
    @Override
    public String info() {
        return "Mobil [Merk: " + merk + ", Jumlah Pintu: " + jumlahPintu + "]";
    }
}
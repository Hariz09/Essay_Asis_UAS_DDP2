abstract class Kendaraan {
    protected String merk;
    
    public Kendaraan(String merk) {
        this.merk = merk;
    }
    
    public String getMerk() {
        return merk;
    }
    
    // Abstract method yang harus diimplementasikan oleh subclass
    public abstract String info();
}
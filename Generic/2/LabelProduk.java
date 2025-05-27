// Class untuk label produk
class LabelProduk implements Cetakable {
    private String namaProduk;
    private String harga;
    
    public LabelProduk(String namaProduk, String harga) {
        this.namaProduk = namaProduk;
        this.harga = harga;
    }
    
    @Override
    public String getIsiCetakan() {
        return "[Produk: " + namaProduk + " | Harga: " + harga + "]";
    }
    
    // Getter methods
    public String getNamaProduk() { return namaProduk; }
    public String getHarga() { return harga; }
}
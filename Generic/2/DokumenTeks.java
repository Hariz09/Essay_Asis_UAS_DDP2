// Class untuk dokumen teks
class DokumenTeks implements Cetakable {
    private String judul;
    private String isi;
    
    public DokumenTeks(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }
    
    @Override
    public String getIsiCetakan() {
        return "[Judul: " + judul + "]\nIsi: " + isi;
    }
    
    // Getter methods
    public String getJudul() { return judul; }
    public String getIsi() { return isi; }
}
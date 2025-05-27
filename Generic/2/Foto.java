// Class untuk foto
class Foto implements Cetakable {
    private String namaFile;
    private String resolusi;
    
    public Foto(String namaFile, String resolusi) {
        this.namaFile = namaFile;
        this.resolusi = resolusi;
    }
    
    @Override
    public String getIsiCetakan() {
        return "[File: " + namaFile + " | Resolusi: " + resolusi + "]";
    }
    
    // Getter methods
    public String getNamaFile() { return namaFile; }
    public String getResolusi() { return resolusi; }
}
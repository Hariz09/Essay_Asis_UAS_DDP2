class Motor extends Kendaraan {
    private boolean includeHelm;
    
    public Motor(String merk, boolean includeHelm) {
        super(merk);
        this.includeHelm = includeHelm;
    }
    
    public boolean isIncludeHelm() {
        return includeHelm;
    }
    
    @Override
    public String info() {
        return "Motor [Merk: " + merk + ", Include Helm: " + (includeHelm ? "Ya" : "Tidak") + "]";
    }
}
package Node;

public class NodePenerima {
    private String namaPenerima;
    private String alamat;
    private String kota;
    public NodePenerima(String namaPenerima, String alamat, String kota){
        this.namaPenerima = namaPenerima;
        this.alamat = alamat;
        this.kota = kota;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getKota() {
        return kota;
    }
}

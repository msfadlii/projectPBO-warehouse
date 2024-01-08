package Node;

public class NodeBarang {
    private String no_resi;
    private String Tanggaltiba;
    private String Penerima;
    private int berat;
    private String namaPetugas;
    private NodeKategori nodeKategori;
    private NodePenerima nodePenerima;
    public NodeBarang(String no_resi, String tanggalTiba, String penerima, String alamat, String kota, int berat, String namaPetugas, String namaKategori) {
        this.no_resi = no_resi;
        this.Tanggaltiba = tanggalTiba;
        this.nodePenerima = new NodePenerima(penerima, alamat, kota);
        //this.Penerima = penerima;
        this.berat = berat;
        this.namaPetugas = namaPetugas;
        this.nodeKategori = new NodeKategori(namaKategori);
    }

    public String getNo_resi() {
        return no_resi;
    }

    public String getPenerima() {
        return Penerima;
    }

    public String getTanggaltiba() {
        return Tanggaltiba;
    }


    public int getBerat() {
        return berat;
    }

    public String getNamaPetugas() {
        return namaPetugas;
    }

    public NodeKategori getKategori() {
        return nodeKategori;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public void setPenerima(String penerima) {
        Penerima = penerima;
    }

    public void setKategori(String kategori) {
        this.nodeKategori = new NodeKategori(kategori);
    }

    public void setNodePenerima(String penerima, String alamat, String kota) {
        this.nodePenerima = new NodePenerima(penerima, alamat, kota);
    }

    public NodePenerima getNodePenerima() {
        return nodePenerima;
    }
}

package Model;

import Model.JSON.MKategoriJSON;
import Model.JSON.ModelJSON;
import Node.NodeBarang;
import Node.NodeCheckoutBarang;
import Node.NodeKategori;
import View.Login;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Node;

import java.util.ArrayList;


public class ModelBarang {
    private ArrayList<NodeBarang> listNodeBarangMasuk;
    private ArrayList<NodeBarang> listNodeBarang;
    private ArrayList<NodeCheckoutBarang> listBarangKeluar;
    ModelJSON modelJSON;
    ModelJSON modelJSONIn;
    ModelJSON modelJSONOut;

    public ModelBarang(){
        listNodeBarang = new ArrayList<>();
        listNodeBarangMasuk = new ArrayList<>();
        listBarangKeluar = new ArrayList<>();
        modelJSONIn = new ModelJSON<>("src/Database/barang_masuk.json");
        listNodeBarangMasuk = modelJSONIn.readFromFile(new TypeToken<ArrayList<NodeBarang>>() {}.getType());
        modelJSONOut = new ModelJSON<>("src/Database/barang_keluar.json");
        listBarangKeluar = modelJSONOut.readFromFile(new TypeToken<ArrayList<NodeBarang>>() {}.getType());
        modelJSON = new ModelJSON<>("src/Database/barang.json");
        listNodeBarang = modelJSON.readFromFile(new TypeToken<ArrayList<NodeBarang>>() {}.getType());
    }

    public void tambahBarang(String no_resi, String tanggalTiba, String penerima, String alamat, String kota, int berat, String namaPetugas, String namaKategori) {
        if (modelJSON.checkFile()){
            if(listNodeBarangMasuk != null){
                listNodeBarangMasuk.add(new NodeBarang(no_resi, tanggalTiba, penerima, alamat, kota, berat, namaPetugas, namaKategori));
                listNodeBarang.add(new NodeBarang(no_resi, tanggalTiba, penerima, alamat, kota, berat, namaPetugas, namaKategori));
                modelJSON.writeToFile(listNodeBarangMasuk);
                modelJSONIn.writeToFile(listNodeBarangMasuk);
            }else{
                listNodeBarangMasuk = new ArrayList<NodeBarang>();
                listNodeBarangMasuk.add(new NodeBarang(no_resi, tanggalTiba, penerima, alamat, kota, berat, namaPetugas, namaKategori));
                listNodeBarang.add(new NodeBarang(no_resi, tanggalTiba, penerima, alamat, kota, berat, namaPetugas, namaKategori));
                modelJSON.writeToFile(listNodeBarangMasuk);
                modelJSONIn.writeToFile(listNodeBarangMasuk);
            }
        } else {
            System.out.println("Database Barang tidak ada !");
        }
    }

    public void updateBarang(NodeBarang barang){
        int index = listNodeBarang.indexOf(barang);
        listNodeBarang.get(index).setBerat(barang.getBerat());
        listNodeBarang.get(index).setNodePenerima(barang.getNodePenerima().getNamaPenerima(), barang.getNodePenerima().getAlamat(), barang.getNodePenerima().getKota());
//        listNodeBarang.get(index).setPenerima(barang.getPenerima()); ;
        listNodeBarang.get(index).setKategori(barang.getKategori().getNama()); ;

        modelJSON.writeToFile(listNodeBarang);
    }

    public void deleteBarang(NodeBarang barang){
        listNodeBarang.remove(barang);
        modelJSON.writeToFile(listNodeBarang);
    }

    public NodeBarang getBarang(String cari){
        NodeBarang dataNodeBarang = null;
        for (NodeBarang nodeBarang : listNodeBarang){
            if(cari.equals(nodeBarang.getNamaPetugas()) || cari.equals(nodeBarang.getKategori().getNama())
                    || cari.equals(nodeBarang.getNo_resi()) || cari.equals(nodeBarang.getNodePenerima().getKota()) || cari.equals(nodeBarang.getTanggaltiba().substring(0, 10))){
                return nodeBarang;
            }
        }
        return dataNodeBarang;
    }
    public ArrayList<NodeBarang> getBarangGudang(String cari){
        ArrayList<NodeBarang> dataNodeBarang = new ArrayList<>();
        for (NodeBarang nodeBarang : listNodeBarang){
            if(cari.equals(nodeBarang.getNodePenerima().getKota())
                    || cari.equals(nodeBarang.getTanggaltiba().substring(0, 10))){
                dataNodeBarang.add(nodeBarang);
            }
        }
        return dataNodeBarang;
    }
//     || cari.equals(nodeBarang.getTanggaltiba().substring(0, 10))
    public ArrayList<NodeBarang> getBarangMasuk(String cari){
        ArrayList<NodeBarang> dataNodeBarang = new ArrayList<>();
        for (NodeBarang nodeBarang : listNodeBarangMasuk){
            if(cari.equals(nodeBarang.getNodePenerima().getKota())
                    || cari.equals(nodeBarang.getTanggaltiba().substring(0, 10))){
                dataNodeBarang.add(nodeBarang);
            }
        }
        return dataNodeBarang;
    }

    public ArrayList<NodeCheckoutBarang> getBarangKeluar(String cari){
        ArrayList<NodeCheckoutBarang> dataNodeCheckoutBarang = new ArrayList<>();
        for (NodeCheckoutBarang nodeBarang : listBarangKeluar){
            if(cari.equals(nodeBarang.getNamaPetugas()) || cari.equals(nodeBarang.getKategori().getNama())
                    || cari.equals(nodeBarang.getNo_resi()) || cari.equals(nodeBarang.getNodePenerima().getKota()) || cari.equals(nodeBarang.getTanggaltiba().substring(0, 10))){
                dataNodeCheckoutBarang.add(nodeBarang);
            }
        }
        return dataNodeCheckoutBarang;
    }


    public ArrayList<NodeBarang> listBarangMasuk(){
        //listNodeBarangMasuk = modelJSON.readFromFile();
        return listNodeBarangMasuk;
    }
    public ArrayList<NodeBarang> listBarang(){
        //listNodeBarang = modelJSON.readFromFile();
        return listNodeBarang;
    }

    public ArrayList<NodeCheckoutBarang> listBarangKeluar(){
        //listBarangKeluar = modelJSON.readFromFile();
        return listBarangKeluar;
    }

    public ArrayList<NodeKategori> getDataKategori(){
        MKategoriJSON mKategoriJSON = new MKategoriJSON();
        ArrayList<NodeKategori> listNodeKategori = mKategoriJSON.readFromFile();

        return listNodeKategori;
    }

    public void checkoutBarang(NodeBarang nodeBarang, String tanggalKeluar){
        if(listBarangKeluar != null){
            listBarangKeluar.add(new NodeCheckoutBarang(nodeBarang.getNo_resi(), nodeBarang.getTanggaltiba(), nodeBarang.getNodePenerima().getNamaPenerima(), nodeBarang.getNodePenerima().getAlamat(), nodeBarang.getNodePenerima().getKota(), nodeBarang.getBerat(), Login.username, nodeBarang.getKategori().getNama(), tanggalKeluar));
        }else{
            listBarangKeluar = new ArrayList<NodeCheckoutBarang>();
            listBarangKeluar.add(new NodeCheckoutBarang(nodeBarang.getNo_resi(), nodeBarang.getTanggaltiba(), nodeBarang.getNodePenerima().getNamaPenerima(), nodeBarang.getNodePenerima().getAlamat(), nodeBarang.getNodePenerima().getKota(), nodeBarang.getBerat(), Login.username, nodeBarang.getKategori().getNama(), tanggalKeluar));
        }
        modelJSONOut.writeToFile(listBarangKeluar);
        deleteBarang(nodeBarang);
    }
}
package Controller;

import Model.ModelBarang;
import Node.NodeBarang;
import Node.NodeCheckoutBarang;
import Node.NodeKategori;
import View.Login;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControllerBarang implements ControllerTemplate{
    ModelBarang modelBarang;

    public ControllerBarang(ModelBarang barang){
        this.modelBarang = barang;
    }
    @Override
    public void insert(String no_resi, String tanggalTiba, String penerima, int berat, String namaPetugas, String namaKategori){
        String no_resiBaru = no_resi;
        while(true){
            NodeBarang cekResi = modelBarang.getBarang(no_resi);
            if(cekResi != null){
                System.out.println("Nomor Resi tersebut sudah ada!");
                System.out.print("Masukkan Nomor Resi : ");
                Scanner input = new Scanner(System.in);
                no_resiBaru = input.nextLine();
                modelBarang.tambahBarang(no_resi, tanggalTiba, penerima, berat, namaPetugas, namaKategori);
                System.out.println("Barang berhasil ditambahkan!");
            }else{
                break;
            }
        }
        modelBarang.tambahBarang(no_resi, tanggalTiba, penerima, berat, namaPetugas, namaKategori);
    }
    @Override
    public void update(String no_resi, String penerima, int berat, String namaKategori){
        NodeBarang barang = modelBarang.getBarang(no_resi);

        if(barang == null){
            System.out.println("Barang tidak ada!");
        }else{
            barang.setBerat(berat);
            barang.setPenerima(penerima);
            barang.setKategori(namaKategori);
            modelBarang.updateBarang(barang);
            System.out.println("Barang berhasil diupdate!");
        }
    }
    @Override
    public void delete(String no_resi){
        NodeBarang barang = modelBarang.getBarang(no_resi);

        if(barang == null){
            System.out.println("Barang tidak ada!");
        }else{
            modelBarang.deleteBarang(barang);
            System.out.println("Barang berhasil dihapus!");
        }
    }


    public ArrayList<NodeBarang> viewBarangMasuk(){
        ArrayList<NodeBarang> barangArrayList = modelBarang.listBarangMasuk();
        if(barangArrayList == null){
            barangArrayList = new ArrayList<>();
        }
        return barangArrayList;
    }
    public ArrayList<NodeBarang> viewBarang(){
        ArrayList<NodeBarang> barangArrayList = modelBarang.listBarang();
        if(barangArrayList == null){
            barangArrayList = new ArrayList<>();
        }
        return barangArrayList;
    }
    public ArrayList<NodeCheckoutBarang> viewBarangKeluar(){
        ArrayList<NodeCheckoutBarang> barangArrayList = modelBarang.listBarangKeluar();
        if(barangArrayList == null){
            barangArrayList = new ArrayList<>();
        }
        return barangArrayList;
    }

    public void listKategori(){
        System.out.println("Kategori : ");
        for (int i = 0; i< modelBarang.getDataKategori().size(); i++){
            System.out.println((i+1)+". "+ modelBarang.getDataKategori().get(i).getNama());
        }
    }

    public String pilihKategori(int pilihan){
        String namaKat="";
        ArrayList<NodeKategori> kategoriAL = modelBarang.getDataKategori();
        for (int i = 0; i < kategoriAL.size(); i++){
            if(pilihan == kategoriAL.get(i).getNomor()){
                namaKat = kategoriAL.get(i).getNama();
                break;
            }
        }
        return namaKat;
    }

    public void checkout(String resi, String tanggal){
        int checkout=0;
        NodeBarang nodeBarang = modelBarang.getBarang(resi);
        if(nodeBarang != null){
            modelBarang.checkoutBarang(nodeBarang, tanggal);
            checkout = 1;
        }
        if(checkout == 1){
            System.out.println("Checkout Barang Berhasil !");
        }else{
            System.out.println("Barang tidak ditemukan!");
        }
    }

    public NodeBarang searchBarang(String cari){
        return modelBarang.getBarang(cari);
    }

    public void buatFileLaporan(String jenis_laporan){
        String path = "";
        if(jenis_laporan.equals("masuk")){
            path = "src/Laporan/fileLaporanMasuk.txt";
        }else if(jenis_laporan.equals("keluar")){
            path = "src/Laporan/fileLaporanKeluar.txt";
        }else{
            path = "src/Laporan/fileLaporan.txt";
        }
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File Laporan berhasil dibuat: " + myObj.getName());
            } else {
                System.out.println("File Laporan sudah ada.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cetakLaporanTxt(path, jenis_laporan);
    }

    public void cetakLaporanTxt(String path, String jenis_laporan){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dow = now.getDayOfWeek();
        //Write File
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write("------------------------------------------------------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t\t\t\tLAPORAN BARANG\n------------------------------------------------------------------------------------------------------------------------------\n");
            writer.write("Tanggal : "+dow+", "+dtf.format(now)+
                    "\nPetugas : "+Login.username +
                    "\n------------------------------------------------------------------------------------------------------------------------------\n");

            if (jenis_laporan.equals("masuk")) {
                writer.write(" \t\t\t\t\t\t\t\t\t\t\tLAPORAN BARANG MASUK"+
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write("\nNOMOR RESI \t\tKATEGORI \t\tBERAT \t\tPENERIMA \t\tTANGGAL \t\t\t\t\tPETUGAS\n");
                if (modelBarang.listBarangMasuk() != null) {
                    for (NodeBarang nodeBarang : modelBarang.listBarangMasuk()){
                        writer.write(
                                nodeBarang.getNo_resi()+" \t\t"+
                                        nodeBarang.getKategori().getNama()+" \t\t\t"+
                                        nodeBarang.getBerat()+" \t\t"+
                                        nodeBarang.getPenerima()+" \t\t\t"+
                                        nodeBarang.getTanggaltiba()+" \t\t"+
                                        nodeBarang.getNamaPetugas()+"\n"
                        );
                    }
                } else {
                    System.out.println("Tidak ada barang!");
                }
            } else if (jenis_laporan.equals("keluar")) {
                writer.write(" \t\t\t\t\t\t\t\t\t\t\tLAPORAN BARANG KELUAR"+
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write("\nNOMOR" +
                        " RESI \t\tKATEGORI \t\tBERAT \t\tPENERIMA \t\tTANGGAL MASUK \t\t\t\t\tPETUGAS \t\t\tTANGGAL KELUAR\n");
                if (modelBarang.listBarangKeluar() != null) {
                    for (NodeCheckoutBarang barang: modelBarang.listBarangKeluar()){
                        writer.write(
                                barang.getNo_resi()+" \t\t"+
                                        barang.getKategori().getNama()+" \t\t\t"+
                                        barang.getBerat()+" \t\t"+
                                        barang.getPenerima()+" \t\t\t"+
                                        barang.getTanggaltiba()+" \t\t\t"+
                                        barang.getNamaPetugas()+" \t\t\t\t"+
                                        barang.getTanggalKeluar()+"\n"
                        );
                    }
                } else {
                    System.out.println("Tidak ada barang!");
                }
            } else {
                writer.write(" \t\t\t\t\t\t\t\t\t\t\tLAPORAN BARANG DI GUDANG"+
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write("\nNOMOR RESI \t\tKATEGORI \t\tBERAT \t\tPENERIMA \t\tTANGGAL \t\t\t\t\tPETUGAS\n");
                if (modelBarang.listBarang() != null) {
                    for (NodeBarang nodeBarang : modelBarang.listBarang()){
                        writer.write(
                                nodeBarang.getNo_resi()+" \t\t"+
                                        nodeBarang.getKategori().getNama()+" \t\t\t"+
                                        nodeBarang.getBerat()+" \t\t"+
                                        nodeBarang.getPenerima()+" \t\t\t"+
                                        nodeBarang.getTanggaltiba()+" \t\t"+
                                        nodeBarang.getNamaPetugas()+"\n"
                        );
                    }
                } else {
                    System.out.println("Tidak ada barang!");
                }
            }
            System.out.println("Berhasil Menulis File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cetakLaporanPdf(String jenis_laporan){
//        String path="src/Laporan/LaporanPDF1.pdf";
//            PdfWriter pdfWriter = new PdfWriter(path);
//            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
//            pdfDocument.setDefaultPageSize(PageSize.A4);
//            Document document = new Document(pdfDocument);
//            float twocol = 285f;
//            float twocol150 = twocol+150f;
//            float twocolumnWidth[] = {twocol150, twocol};
//
//            Table table = new Table(twocolumnWidth);
//            table.addCell(new Cell().add("LAPORAN BARANG"));
//            document.add(table);
//
//            document.close();
//
//            System.out.println("Laporan PDF berhasil dibuat");

    }

}
package Controller.JSON;

import Model.Barang;
import Model.Kategori;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class CBarangJSON {
    static String fname = "src/Database/barang.json";

    public boolean cekFile(){
        boolean cek = false;
        try {
            File file = new File(fname);
            if(file.exists()){
                cek = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return cek;
    }

    public JSONArray convertArrayListtoJSONArray(ArrayList<Barang> listBarang){
        if(listBarang == null){
            return null;
        } else {
            JSONArray arrBarang = new JSONArray();
            for (Barang barang : listBarang) {
                JSONObject objBarang = new JSONObject();
                objBarang.put("no_resi", barang.getNo_resi());
                objBarang.put("tanggal", barang.getTanggaltiba());
                objBarang.put("penerima", barang.getPenerima());
                objBarang.put("berat", barang.getBerat());
                objBarang.put("nama_petugas", barang.getNamaPetugas());
                objBarang.put("kategori", barang.getKategori().getNama());
                arrBarang.add(objBarang);
            }
            return arrBarang;
        }
    }

    public void writeFileJSON(ArrayList<Barang> listBarang){
        JSONArray arrBarang = convertArrayListtoJSONArray(listBarang);
        try {
            FileWriter fileWriter = new FileWriter(fname);
            fileWriter.write(arrBarang.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Barang> convertJSONArraytoArrayList(JSONArray arrBarang){
        if(arrBarang == null){
            return null;
        } else {
            ArrayList<Barang> listBarang = new ArrayList<>();
            for(Object objBarang : arrBarang){
                JSONObject jsonObject = (JSONObject) objBarang;
                String no_resi = jsonObject.get("no_resi").toString();
                String tanggal = jsonObject.get("tanggal").toString();
                String penerima = jsonObject.get("penerima").toString();
                int berat = Integer.parseInt(jsonObject.get("berat").toString());
                String nama_petugas = jsonObject.get("nama_petugas").toString();
                String sKategori = jsonObject.get("kategori").toString();
                Kategori kategori = new Kategori(sKategori);
                listBarang.add(new Barang(no_resi, tanggal, penerima, berat, nama_petugas,kategori));
            }
            return listBarang;
        }
    }

    public ArrayList<Barang> readFromFile(){
        if(cekFile()){
            ArrayList listBarang = null;
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(fname);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                listBarang = convertJSONArraytoArrayList(jsonArray);
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            } catch (IOException e){
                throw new RuntimeException(e);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
            return listBarang;
        }else{
            return null;
        }
    }
}
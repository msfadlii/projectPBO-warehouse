package Controller.JSON;

import Model.Barang;
import Model.CheckoutBarang;
import Model.Kategori;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class CBarangJSON {
    static String fnameIn = "src/Database/barang_masuk.json";
    static String fname = "src/Database/barang.json";
    static String fnameOut = "src/Database/barang_keluar.json";
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
                objBarang.put("kategori", barang.getKategori().getNama());
                objBarang.put("penerima", barang.getPenerima());
                objBarang.put("berat", barang.getBerat());
                objBarang.put("tanggal", barang.getTanggaltiba());
                objBarang.put("petugas_in", barang.getNamaPetugas());
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
                String sKategori = jsonObject.get("kategori").toString();
                String penerima = jsonObject.get("penerima").toString();
                int berat = Integer.parseInt(jsonObject.get("berat").toString());
                String tanggal = jsonObject.get("tanggal").toString();
                String nama_petugas = jsonObject.get("petugas_in").toString();
                listBarang.add(new Barang(no_resi, tanggal, penerima, berat, nama_petugas, sKategori));
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

    /*
                BARANG MASUK
     */

    public boolean cekFileIn(){
        boolean cek = false;
        try {
            File file = new File(fnameIn);
            if(file.exists()){
                cek = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return cek;
    }

    public void writeFJSONIn(ArrayList<Barang> listBarang){
        JSONArray arrBarang = convertArrayListtoJSONArray(listBarang);
        try {
            FileWriter fileWriter = new FileWriter(fnameIn);
            fileWriter.write(arrBarang.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Barang> readFromFileIn(){
        if(cekFile()){
            ArrayList listBarang = null;
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(fnameIn);
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

    /*
                BARANG KELUAR
     */

    public boolean cekFileOut(){
        boolean cek = false;
        try {
            File file = new File(fnameOut);
            if(file.exists()){
                cek = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return cek;
    }

    public JSONArray convertALtoJSONArr(ArrayList<CheckoutBarang> listBarang){
        if(listBarang == null){
            return null;
        } else {
            JSONArray arrBarang = new JSONArray();
            for (CheckoutBarang barang : listBarang) {
                JSONObject objBarang = new JSONObject();
                objBarang.put("no_resi", barang.getNo_resi());
                objBarang.put("kategori", barang.getKategori().getNama());
                objBarang.put("berat", barang.getBerat());
                objBarang.put("penerima", barang.getPenerima());
                objBarang.put("tanggal_masuk", barang.getTanggaltiba());
                objBarang.put("petugas_in", barang.getNamaPetugas());
                objBarang.put("tanggal_keluar", barang.getTanggalKeluar());
                arrBarang.add(objBarang);
            }
            return arrBarang;
        }
    }

    public void writeFJSON(ArrayList<CheckoutBarang> listBarang){
        JSONArray arrBarang = convertALtoJSONArr(listBarang);
        try {
            FileWriter fileWriter = new FileWriter(fnameOut);
            fileWriter.write(arrBarang.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CheckoutBarang> convertJArrtoAL(JSONArray arrBarang){
        if(arrBarang == null){
            return null;
        } else {
            ArrayList<CheckoutBarang> listBarang = new ArrayList<>();
            for(Object objBarang : arrBarang){
                JSONObject jsonObject = (JSONObject) objBarang;
                String no_resi = jsonObject.get("no_resi").toString();
                String sKategori = jsonObject.get("kategori").toString();
                int berat = Integer.parseInt(jsonObject.get("berat").toString());
                String penerima = jsonObject.get("penerima").toString();
                String tanggal = jsonObject.get("tanggal_masuk").toString();
                String nama_petugas = jsonObject.get("petugas_in").toString();
                String tglKeluar = jsonObject.get("tanggal_keluar").toString();
                listBarang.add(new CheckoutBarang(no_resi, tanggal, penerima, berat, nama_petugas, sKategori, tglKeluar));
            }
            return listBarang;
        }
    }

    public ArrayList<CheckoutBarang> readFromFileOut(){
        if(cekFileOut()){
            ArrayList listBarang = null;
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(fnameOut);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                listBarang = convertJArrtoAL(jsonArray);
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
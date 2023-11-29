package Controller.JSON;

import Model.Barang;
import Model.Kategori;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class CKategoriJSON {
    static String fname = "src/Database/kategori.json";

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

    public JSONArray convertArrayListtoJSONArray(ArrayList<Kategori> listKategori){
        if(listKategori == null){
            return null;
        } else {
            JSONArray arrkategori = new JSONArray();
            for (Kategori kategori : listKategori) {
                JSONObject objKategori = new JSONObject();
                objKategori.put("Kategori", kategori.getNama());

            }
            return arrkategori;
        }
    }

    public void writeFileJSON(ArrayList<Kategori> listkategori){
        JSONArray arrKategori = convertArrayListtoJSONArray(listkategori);
        try {
            FileWriter fileWriter = new FileWriter(fname);
            fileWriter.write(arrKategori.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Kategori> convertJSONArraytoArrayList(JSONArray arrKategori){
        if(arrKategori == null){
            return null;
        } else {
            ArrayList<Kategori> listKategori = new ArrayList<>();
            for(Object objkategori : arrKategori){
                JSONObject jsonObject = (JSONObject) objkategori;
                String nama = jsonObject.get("nama").toString();


                listKategori.add(new Kategori(nama));
            }
            return listKategori;
        }
    }

    public ArrayList<Kategori> readFromFile(){
        if(cekFile()){
            ArrayList listKategori = null;
            JSONParser parser = new JSONParser();
            try {
                Reader reader = new FileReader(fname);
                JSONArray jsonArray = (JSONArray) parser.parse(reader);
                listKategori = convertJSONArraytoArrayList(jsonArray);
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            } catch (IOException e){
                throw new RuntimeException(e);
            } catch (ParseException e){
                throw new RuntimeException(e);
            }
            return listKategori;
        }else{
            return null;
        }
    }

}

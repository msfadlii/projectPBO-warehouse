package Model.JSON;

import Node.NodeKategori;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class MKategoriJSON {
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

    public ArrayList<NodeKategori> convertJSONArraytoArrayList(JSONArray arrKategori){
        if(arrKategori == null){
            return null;
        } else {
            ArrayList<NodeKategori> listNodeKategori = new ArrayList<>();
            for(Object objkategori : arrKategori){
                JSONObject jsonObject = (JSONObject) objkategori;
                int nomor = Integer.parseInt(jsonObject.get("nomor").toString());
                String nama = jsonObject.get("nama_kategori").toString();

                listNodeKategori.add(new NodeKategori(nomor, nama));
            }
            return listNodeKategori;
        }
    }

    public ArrayList<NodeKategori> readFromFile(){
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
package Model.JSON;

import com.google.gson.JsonArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class ModelAbstract<T> {
    abstract boolean checkFile();
    abstract void setupFile();
    abstract JsonArray convertListToJsonArray(ArrayList<T> list) ;
    abstract ArrayList<T> convertJsonArrayToList(JsonArray jsonArray, Type type);
    abstract void writeToFile(ArrayList<T> list);
    abstract ArrayList<T> readFromFile(Type type);
}

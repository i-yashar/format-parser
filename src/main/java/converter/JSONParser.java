package converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONParser implements Parser{
    private final GsonBuilder gsonBuilder;
    private final Gson gson;

    public JSONParser(Gson gson) {
        gsonBuilder =
        this.gson = gson;
    }

    @Override
    public String serialize(Object object) {
        return this.gson.toJson(object);
    }

    @Override
    public void serialize(Object object, String fileName) {
        File file = new File(fileName);
        try {
            boolean isCreated = file.createNewFile();
            this.serialize(object, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serialize(Object object, File file) {
        try(FileWriter fw = new FileWriter(file)){
            this.gson.toJson(object, fw);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public <T> T deserialize(String fileName, Class<T> type) {
        return null;
    }

    @Override
    public <T> T deserialize(File file, Class<T> type) {
        return null;
    }
}

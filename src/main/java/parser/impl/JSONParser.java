package parser.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import parser.FormatParser;
import parser.enums.Format;

import java.io.*;

public class JSONParser implements FormatParser {
    private final GsonBuilder gsonBuilder;
    private Gson gson;

    public JSONParser() {
        gsonBuilder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        this.gson = gsonBuilder.create();
    }

    @Override
    public String serialize(Object object) {
        return this.gson.toJson(object);
    }

    @Override
    public void serialize(Object object, String fileName) {

        this.serialize(object, new File(PATH + fileName));
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
        return this.deserialize(new File(PATH + fileName), type);
    }

    @Override
    public <T> T deserialize(File file, Class<T> type) {
        T result = null;

        try(FileReader fileReader = new FileReader(file);) {
            result = this.gson.fromJson(fileReader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void setPrettyPrint() {
        this.gsonBuilder.setPrettyPrinting();
        this.gson = this.gsonBuilder.create();
    }

    @Override
    public void changeFormat(Format format) {

    }
}

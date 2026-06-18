package data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;

public class JsonUtil {

    private static final Gson gson =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    public static <T> void save(String path, T data) {

        try {

            File file = new File(path);

            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(file);

            gson.toJson(data, writer);

            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T load(String path, Class<T> clazz) {

        try {

            File file = new File(path);

            if (!file.exists()) {
                return null;
            }

            FileReader reader = new FileReader(file);

            T data = gson.fromJson(reader, clazz);

            reader.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T load(String path, Type type) {

        try {

            File file = new File(path);

            if (!file.exists()) {
                return null;
            }

            FileReader reader = new FileReader(file);

            T data = gson.fromJson(reader, type);

            reader.close();

            return data;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
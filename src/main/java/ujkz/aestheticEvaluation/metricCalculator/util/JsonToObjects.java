package ujkz.aestheticEvaluation.metricCalculator.util;

import com.google.gson.stream.JsonReader;
import ujkz.aestheticEvaluation.metricCalculator.metrics.Composant;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonToObjects {

    public List<Composant> toComposantList(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        List<Composant> composantList = new ArrayList<>();

        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("compos"))
                readComposant(reader, composantList);
            else
                reader.skipValue();
        }
        reader.endObject();

        return composantList;
    }

    private void readComposant(JsonReader reader, List<Composant> composantList) throws IOException {
        int id = 0;
        Double x = 0.0;
        Double y = 0.0;
        double width = 0.0;
        double height = 0.0;
        String type = "";

        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("height")) {
                    height = reader.nextDouble();
                } else if (name.equals("width")) {
                    width = reader.nextDouble();
                } else if (name.equals("position")) {
                    Double[] pos = setPosition(reader);
                    x = pos[0];
                    y = pos[1];
                } else if (name.equals("class")) {
                    type = reader.nextString();
                } else if (name.equals("id")) {
                    id = reader.nextInt();
                } else {
                    reader.skipValue();
                }
            }
            reader.endObject();
            composantList.add(new Composant(id, x, y, width, height, type));
        }
        reader.endArray();
    }

    private Double[] setPosition(JsonReader reader) throws IOException {
        Double[] pos = new Double[2];

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("column_min")) {
                pos[0] = reader.nextDouble();
            } else if (name.equals("row_min")) {
                pos[1] = reader.nextDouble();
            } else
                reader.skipValue();
        }
        reader.endObject();

        return pos;
    }
}
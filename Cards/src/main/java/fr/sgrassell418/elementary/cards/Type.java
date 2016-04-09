package fr.sgrassell418.elementary.cards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by stephane on 08/04/16.
 */
public class Type {

    private int id;
    private String name;
    private int[] weaknesses;

    @JsonIgnore
    public static List<Type> allTypes = new ArrayList<Type>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(int[] weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String mapToJson() throws JsonProcessingException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(this);
    }

    public static Type jsonToType(String json) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.readValue(json, Type.class);
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weaknesses=" + Arrays.toString(weaknesses) +
                '}';
    }

    public static void initAllTypes(){
        String json = "[\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Fire\",\n" +
                "      \"weaknesses\": [1,2,3]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Water\",\n" +
                "      \"weaknesses\": [2,5,7]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Ice\",\n" +
                "      \"weaknesses\": [0,6]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Earth\",\n" +
                "      \"weaknesses\": [1,4,7]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"Air\",\n" +
                "      \"weaknesses\": [0,6,7]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"Electricity\",\n" +
                "      \"weaknesses\": [3,4]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 6,\n" +
                "      \"name\": \"Metal\",\n" +
                "      \"weaknesses\": [0,1,5]\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 7,\n" +
                "      \"name\": \"Plant\",\n" +
                "      \"weaknesses\": [0,2,6]\n" +
                "    }\n" +
                "  ]";

        try {
            Type.allTypes = new ObjectMapper().readValue(json, new TypeReference<List<Type>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

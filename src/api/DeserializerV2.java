package api;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DeserializerV2 implements JsonDeserializer <DWG>{

    @Override
    public DWG deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        DWG newGraph = new DWG();

        JsonArray nodes = jsonObject.get("Nodes").getAsJsonArray();
        JsonArray edges = jsonObject.get("Edges").getAsJsonArray();

        for (JsonElement set : nodes)
        {
            JsonObject val = set.getAsJsonObject();
            int id = val.get("id").getAsInt();
            String pos = val.get("pos").getAsString();
            NodeData node = new Vertex(id);
            node.setLocation(new Dimensions(pos));
            newGraph.addNode(node);
        }

        for (JsonElement set : edges)
        {
            JsonObject val = set.getAsJsonObject();
            int src =  val.get("src").getAsInt();
            int dest = val.get("dest").getAsInt();
            double w = val.get("w").getAsDouble();
            newGraph.connect(src, dest, w);
        }

        return newGraph;
    }

}
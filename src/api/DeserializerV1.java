package api;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map.Entry;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class DeserializerV1 implements JsonDeserializer <DWG> {
    @Override
    public DWG deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2) throws JsonParseException
    {
        JsonObject jsonObject = json.getAsJsonObject();
        DWG newGraph = new DWG();

        JsonObject nodes = jsonObject.get("Nodes").getAsJsonObject();
        JsonObject edges = jsonObject.get("Edges").getAsJsonObject();

        for (Entry<String, JsonElement> set : nodes.entrySet())
        {
            JsonElement jsonValueElement = set.getValue(); //the value of the hashmap as json element
            int key = jsonValueElement.getAsJsonObject().get("key").getAsInt();
            double weight = jsonValueElement.getAsJsonObject().get("weight").getAsDouble();
            int tag = jsonValueElement.getAsJsonObject().get("tag").getAsInt();
            String info = jsonValueElement.getAsJsonObject().get("info").getAsString();
            Vertex n = new Vertex(key,weight,tag,info);
            newGraph.addNode(n);
        }

        for (Entry<String, JsonElement> set : edges.entrySet())
        {
            JsonElement jsonValueElement = set.getValue(); //the value of the hashmap as json element
            Iterator<Entry<String, JsonElement>> it = jsonValueElement.getAsJsonObject().entrySet().iterator();
            while(it.hasNext()) {
                Entry<String, JsonElement> f = it.next();
                JsonElement jElement = f.getValue();
                int src = jElement.getAsJsonObject().get("src").getAsInt();
                int dest = jElement.getAsJsonObject().get("dest").getAsInt();
                double w = jElement.getAsJsonObject().get("w").getAsDouble();
                newGraph.connect(src, dest, w);
            }
        }

        return newGraph;
    }
}

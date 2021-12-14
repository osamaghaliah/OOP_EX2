import MyGUI.DrawingV2;
import api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static void main(String[] args) throws IOException, ParseException {
        Ex2.runGUI(args[0]);
    }
    public static DirectedWeightedGraph getGraph(String json_file) {
        DWGAlgo dw = new DWGAlgo();
        dw.load(json_file);
        DirectedWeightedGraph ans = dw.getGraph() ;
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGraphAlgo(String json_file) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DWG.class, new DeserializerV2());
        Gson gson =  builder.setPrettyPrinting().create();
        DWG helper = null;
        try {
            helper = gson.fromJson(new FileReader(json_file), DWG.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DirectedWeightedGraphAlgorithms Graph = new DWGAlgo();
        Graph.init(helper);
        return Graph;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGraphAlgo(json_file);
        new DrawingV2(alg);
    }
}
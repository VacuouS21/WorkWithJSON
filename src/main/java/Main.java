import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

public class Main{
    public static void main(String[] args) throws IOException, ParseException {
        URL yahoo = new URL(args[0]);
        getArray(getJsonString(yahoo));
    }

    private static void getArray(String jsonString) throws ParseException {
        Object obj = new JSONParser().parse(jsonString);
        JSONArray jo = (JSONArray) obj;
        int[][] matrix=new int[jo.size()+1][jo.size()+1];
        Iterator points = jo.iterator();
        while (points.hasNext()) {
            JSONObject point = (JSONObject) points.next();
            String idPoint= (String) point.get("id");
            JSONArray myArray=(JSONArray) point.get("reference_id");

            if(myArray==null) {
                continue;
            }

            Iterator references = myArray.iterator();

            int i=Integer.parseInt(idPoint);
            while (references.hasNext()) {
                int j=Integer.parseInt(String.valueOf(references.next()));
                matrix[i][j]=1;
                matrix[j][i]=1;
            }

        }
        for(int i=0;i<matrix.length;i++) {
            for(int j=0;j<matrix.length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println("");
        }


// Достаем массив номеров
// Выводим в цикле данные массива

    }
    private static String getJsonString(URL yahoo) throws IOException {
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String myString = "";
        while ((inputLine = in.readLine()) != null)
            myString+=inputLine;
        in.close();
        return myString;

    }
}
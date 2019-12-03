package com.itron.agula;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MeterModelList {
    private static final Map<String, String[]> models = new HashMap<String, String[]>();

    static{

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\agula\\IdeaProjects\\WaterDBCondirioner\\src\\com\\itron\\agula\\meters_list.csv"), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] modelNameDn = line.split(";",-1);
                models.put(modelNameDn[0].toLowerCase().trim(), modelNameDn);
            }
            reader.close();
//            System.out.println("strings read: " + models.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] checkDiameter(String meterModel){
        String model = meterModel.toLowerCase().trim();
        if(models.get(model) != null) {
            return models.get(model);
        } else return new String[]{"пусто","meter DN",meterModel};
    }
}

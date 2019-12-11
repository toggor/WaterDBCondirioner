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
//            the models entry look like:
//            modelNameDn[0] - model written like in input from customer
//            modelNameDn[1] - correct Dn
//            modelNameDn[2] - correct developer name
                models.put(modelNameDn[0].toLowerCase().trim(), modelNameDn);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[] checkDiameter(String meterModel, String meterDn){
        String oldModel = meterModel.toLowerCase().trim();
        String[] okModel = models.get(oldModel);
        if(okModel != null) {
            if(!okModel[1].isEmpty()){
                return new String[]{okModel[2], okModel[1], "OK"};
            }else {
                return new String[]{okModel[2], meterDn, "CheckMe"};
            }
        }
        else return new String[]{meterModel, meterDn, "CheckMe"};
    }
}

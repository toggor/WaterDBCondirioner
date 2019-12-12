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
//        we get the meterDN and Name to check with list
        String oldModel = meterModel.toLowerCase().trim();
        String[] okModel = models.get(oldModel);
        if(okModel != null) {
            if(!okModel[1].isEmpty()){
//                if model is present in list
                if (!okModel[1].equals(meterDn)){
//                    if ModelDN is not the same as MeterDN provided we mark for check
                    return new String[]{okModel[2], okModel[1], "CheckMeterDN"};
                }
                else{
//                    if model present and DN is the same - we say OK
                    return new String[]{okModel[2], okModel[1], "OK"};
                }
            }else {
//                if model Dn is not present in list we leave initial DN and mark it for check
                return new String[]{okModel[2], meterDn, "CheckListDn"};
            }
        }
//        if model is not present in list we keep initial values and mark for check
        else return new String[]{meterModel, meterDn, "CheckMeterList"};
    }
}

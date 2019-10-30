package com.itron.agula;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    private static String FILE_NAME;
    private static String FILE_NAME_CONDITIONED;
    private static String CITY;

    static{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter file name:");
            FILE_NAME = reader.readLine();
            FILE_NAME_CONDITIONED = FILE_NAME.substring(0,FILE_NAME.lastIndexOf("."))+ "_conditioned" + FILE_NAME.substring(FILE_NAME.lastIndexOf("."),FILE_NAME.length());
            System.out.println("Enter city to strip from address:");
            CITY = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
	// write your code here

        long startTime = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_NAME), StandardCharsets.UTF_8))){
            FileWriter writer = new FileWriter(FILE_NAME_CONDITIONED, false);
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(parseWater(line, CITY));
                writer.write("\n");
            }
            reader.close();
            writer.close();
            long timeSpent = System.currentTimeMillis() - startTime;
            System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
        } catch (IOException e) {
            // log error
            e.printStackTrace();
        }
    }

    private static String parseWater(String waterString, String city){
//      Table structure is fixed so we refer to exact rows
        String result ="";
        String[] parameters = waterString.split(";",-1);
//      Oraganization name - translit as name
        parameters[1] = NameCondition.toCondition(parameters[1]).trim();
//      Address - translit as name
        int cityIndex = parameters[2].toLowerCase().indexOf(city.toLowerCase());
        if(-1 != cityIndex){
            parameters[2] = parameters[2].substring(cityIndex+city.length(), parameters[2].length());
        }
        parameters[2] = NameCondition.toCondition(parameters[2]).trim();
//      Serial number - we translit as SN
        parameters[3] = SerialCondition.toCondition(parameters[3]).trim();
//      and just in case - we translit name
        parameters[3] = NameCondition.toCondition(parameters[3]).trim();
//      District - we translit as name
        parameters[11] = NameCondition.toCondition(parameters[11]).trim();
        result = String.join(";", parameters);
        return result;
    }
}

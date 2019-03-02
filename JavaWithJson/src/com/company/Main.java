package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class Main {

    public static void main(String[] args) throws JSONException, IOException {
        JSONObject person = new JSONObject();
        person.put("firstName", "Sudipta");
        person.put("lastName", "Shahin");

        /** json nested object **/
        JSONObject address = new JSONObject();
        address.put("country", "Bangladesh");
        address.put("city", "Dhaka");

        person.put("address", address);

        /** json array **/
        JSONArray languages = new JSONArray();
        languages.put("English");
        languages.put("Russian");
        languages.put("French");

        person.put("languages", languages);

        System.out.println(person.toString());

        FileWriter fileWriter = new FileWriter("/home/shine/Desktop/jsonTest.json");
        fileWriter.write(person.toString());
        fileWriter.flush();
        System.out.println("File write successfully");

    }
}

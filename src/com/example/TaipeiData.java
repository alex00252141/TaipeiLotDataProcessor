package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TaipeiData {
	private String data = "";
	private final String FILENAME = "TCMSV_alldesc";
	private ArrayList<EachData> allData;
	private Gson gson ;

	public TaipeiData() {
		gson = new GsonBuilder().create();
		
		try {
			FileInputStream fin = new FileInputStream(FILENAME);
			InputStreamReader xover = new InputStreamReader(fin);
			BufferedReader is = new BufferedReader(xover);

			data += is.readLine();// read json

			allData = new ArrayList<EachData>();

			JsonObject jobject = new JsonParser().parse(data).getAsJsonObject();
			jobject = jobject.getAsJsonObject("data");
			JsonArray jarray = jobject.getAsJsonArray("park");

			for (int i = 0; i < jarray.size(); ++i) {
				jobject = jarray.get(i).getAsJsonObject();

				allData.add(gson.fromJson(jobject, EachData.class));

				if (jobject.getAsJsonObject("FareInfo") != null) {
					allData.get(i).setFareInfo(jobject.getAsJsonObject("FareInfo").toString());
				}
			}

		} catch (FileNotFoundException e) {
			System.out.printf("file not find");
		} catch (IOException e) {
			System.out.printf("IO error");
		} catch (JsonSyntaxException ex) {
			// System.out.println(ex.getMessage());
		} catch (Exception e) {
			System.out.printf(e.getMessage());
		}
	}

	public ArrayList<EachData> getAllData() {
		return allData;
	};

	public String toString() {
		return data;
	}
}

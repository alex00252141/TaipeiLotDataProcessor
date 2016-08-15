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
	private String data="";
	private String FILENAME;
	private  ArrayList<EachData> allData;
	
	public TaipeiData() {
		FILENAME = "TCMSV_alldesc.json";
		
		try{
			FileInputStream fin = new FileInputStream(FILENAME);
		    InputStreamReader xover = new InputStreamReader(fin);
		    BufferedReader is = new BufferedReader(xover);
		    
		    data += is.readLine();//read json

		    allData = new ArrayList<EachData>();
			
		    JsonElement jelement = new JsonParser().parse(data);
		    JsonObject  jobject = jelement.getAsJsonObject();
			jobject = jobject.getAsJsonObject("data");
			JsonArray jarray = jobject.getAsJsonArray("park");
		
			for(int i = 0;i<jarray.size();++i){
				jobject = jarray.get(i).getAsJsonObject();
				
				Gson gson = new GsonBuilder().create();
				allData.add(gson.fromJson(jobject, EachData.class));
				/*
				jsonArr.add(
						new EachData(jobject.get("id").toString(), 
								jobject.get("area").toString(), 
								jobject.get("name").toString(), 
								jobject.get("type").toString(), 
								jobject.get("summary").toString(), 
								jobject.get("address").toString(), 
								jobject.get("tw97x").toString(),
								jobject.get("tw97y").toString(), 
								jobject.get("payex").toString(), 
								jobject.get("totalcar").toString(), 
								jobject.get("totalmotor").toString(), 
								jobject.get("servicetime").toString()));
				*/
					if(jobject.getAsJsonObject("FareInfo")!=null){
						//System.out.println(jobject.getAsJsonObject("FareInfo"));
						allData.get(i).setFareInfo(jobject.getAsJsonObject("FareInfo").toString());
						//System.out.println(jsonArr.get(i).getFareInfo());
					}
			}
		    
		    
		}catch(FileNotFoundException e){
			System.out.printf("file not find");
		}catch(IOException e){
			System.out.printf("IO error");
		}catch(JsonSyntaxException ex) {
		    //System.out.println(ex.getMessage());
		}
		catch(Exception e){
			System.out.printf(e.getMessage());
		}
	}
	
	public ArrayList<EachData> getAllData(){
		return allData;
	};
	
	public String toString(){
		return data;
	}
}

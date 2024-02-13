package com.company.repuestos1.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
	private ArrayList<HashMap<String,String>> metadata = new ArrayList<>();

	public ArrayList<HashMap<String, String>> getMetadata() {
		return metadata;
	}

	public void setMetadata(String type, String code, String date) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Type", type);
		map.put("Code", code);
		map.put("Date", date);
		
		metadata.add(map);
	}
}

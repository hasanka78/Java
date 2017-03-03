package com.hasanka.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * Word count demo application.
 * compatible with java 6 upwards
 * @author Hasanka Sapumal
 *
 */
public class WordCountDemo {

	private static HashMap<String, Integer> hmap = new HashMap<String,Integer>();
	private static int count = 1;


	public static void main(String[] args) {

		FileReader fr = null;
		BufferedReader br = null;
		String thisLine = null;
		try {
			fr = new FileReader("E:/file1.txt");
			br = new BufferedReader(fr);
			while ((thisLine = br.readLine())!=null) {
				String [] words  =  thisLine.split(",");
				processWords(words);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		getSortedResult(hmap);
		getSortedResultByValue(hmap);
		



	}

	/**
	 * This method process each line and add words into an hashmap
	 * @param words
	 * @return Void
	 */
	private static void processWords(String[] words) {
		for (String string : words) {
			if(hmap.containsKey(string)){
				hmap.put(string, hmap.get(string)+1);
			}
			else{
				hmap.put(string, count);
			}
		}
	}

	/**
	 * this method sort the result set by key
	 * @param hmap
	 * @return Void
	 */
	private static void getSortedResult(HashMap<String, Integer> hmap) {
		TreeMap<String, Integer> tmap = new TreeMap<>(hmap);
		//tmap.putAll(hmap);
		
		System.out.println("sorted by key");
		for (Map.Entry<String,Integer> entry : tmap.entrySet()) {
			System.out.println(entry.getKey()+ ","+ entry.getValue());
		}
		
		// TreeMap - using String.CASE_INSENSITIVE_ORDER which is a Comparator that orders Strings by compareToIgnoreCase
		//Map<String, Integer> sortedMap = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
		//sortedMap.putAll(unsortedMap);
		

	}

	/**
	 * This method sort on value
	 * @param hmap
	 */
	private static void getSortedResultByValue(HashMap<String, Integer> hmap) {
		
		Set<Entry<String,Integer>> set = hmap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				int result = (o2.getValue()).compareTo(o1.getValue());
				 if (result != 0) {
				      return result;
				    } else {
				    	//if value is eqaul , then sort on key
				      return o1.getKey().compareTo(o2.getKey());
				    } 
				
			}
		});
		
		System.out.println("sorted by value \n "+list);
		
	}



	//to refer - http://www.programcreek.com/2014/03/how-developers-sort-in-java/


}

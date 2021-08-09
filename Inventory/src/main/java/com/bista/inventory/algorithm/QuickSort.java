package com.bista.inventory.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.bista.inventory.model.Item;

public class QuickSort {
	
	public List<Item> sort(List<Item> items)
	{
	    if (items.size() <= 1) 
	        return items; // Already sorted  

	    List<Item> sorted = new ArrayList<Item>();
	    List<Item> lesser = new ArrayList<Item>();
	    List<Item> greater = new ArrayList<Item>();
	    Item pivot = items.get(items.size()-1); // Use last Vehicle as pivot
	    for (int i = 0; i < items.size()-1; i++)
	    {
	        
	        if (items.get(i).getQuantity() < (pivot.getQuantity()))
	            lesser.add(items.get(i));    
	        else
	            greater.add(items.get(i));   
	    }

	    lesser = sort(lesser);
	    greater = sort(greater);

	    lesser.add(pivot);
	    lesser.addAll(greater);
	    sorted = lesser;

	    return sorted;
	}

}

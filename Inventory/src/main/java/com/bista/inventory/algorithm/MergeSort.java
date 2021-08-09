package com.bista.inventory.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.bista.inventory.model.Item;

public class MergeSort {

	// Method to sort the list of items
	public List<Item> sort(List<Item> strList) {
		List<Item> list = new ArrayList<>();
		list = mergeSort(strList);
		
		return list;
	}
	

	/*
	 *  Merge sort works on the principle of divide and conquer.
	 *   Merge sort repeatedly breaks down a list into several 
	 *   sublists until each sublist consist of a single element
	 *   and merging those sublists in a manner that results into
	 *    a sorted list.
	 */
	public List<Item> mergeSort(List<Item> list) {
		List<Item> left = new ArrayList<>();
		List<Item> right = new ArrayList<>();

		int pivot;

		// If there is only element in a list then return the list (no need to sort)
		if(list != null ) {
			if (list.size() == 1) {
				return list;
			} else {
				// Find the pivot point
				pivot = list.size() / 2;
				// copy the left half of the list into the left arraylist
				for (int i = 0; i < pivot; i++) {
					left.add(list.get(i));
				}

				// copy the right half of the list into the right arraylist
				for (int i = pivot; i < list.size(); i++) {
					right.add(list.get(i));
				}

				// Do recursive call to sort the left and right half of the arraylist.
				left = mergeSort(left);
				right = mergeSort(right);

				// Merge the left and right back together
				merge(left, right, list);
			}
		}
		
		return list;
	}


	private void merge(List<Item> left, List<Item> right, List<Item> list) {
		int leftIndex = 0;
		int rightIndex = 0;
		int listIndex = 0;

		/*
		 * As long as left and right ArrayList has element, Keep comparing the element
		 * from left and right and add it to the list in alphabetical order so that
		 * whole list is sorted.
		 */
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if ((left.get(leftIndex).getItemName().toLowerCase().compareTo(right.get(rightIndex).getItemName().toLowerCase())) < 0) {
				list.set(listIndex, left.get(leftIndex));
				leftIndex++;
			} else {
				list.set(listIndex, right.get(rightIndex));
				rightIndex++;
			}

			listIndex++;
		}

		List<Item> rest;
		int restIndex;

		if (leftIndex >= left.size()) {
			// The left ArrayList has been used up
			rest = right;
			restIndex = rightIndex;
		} else {
			// The right ArrayList has been used up
			rest = left;
			restIndex = leftIndex;
		}

		// Copy the rest of whichever ArrayList was not used
		for (int i = restIndex; i < rest.size(); i++) {
			list.set(listIndex, rest.get(i));
			listIndex++;
		}
	}
}

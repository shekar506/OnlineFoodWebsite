package com.OnlineFood.daoimpl;

import java.util.HashMap;
import java.util.Map;

import com.OnlineFood.model.CartItem;

public class Cart 
{

	private Map<Integer,CartItem> items;
	
	public Cart() {
		this.items=new HashMap<>();
	}
	//adding item to cart
	public void addItem(CartItem item) {
		int itemId=item.getItemId();
		if(items.containsKey(itemId)) {
			CartItem existingItem=items.get(itemId);
			existingItem.setQuantity(existingItem.getQuantity()+item.getQuantity());
		}
		else {
			items.put(itemId,item);
		}
	}
	// updating the quantity of item in cart
	public void updateItem(int itemId,int quantity) {
		if(items.containsKey(itemId)) {
			if(quantity<=0) {
				items.remove(itemId);
			}
			else {
				items.get(itemId).setQuantity(quantity);
			}
		}
		
	}
	
	//deleting item from card
	public void deleteItem(int itemId) {
		items.remove(itemId);
	}
	
	public Map<Integer,CartItem> getItems(){
		return items;
	}
	
	public void clearCart() {
		items.clear();
	}	
	
}

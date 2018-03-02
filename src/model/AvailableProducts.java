package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AvailableProducts implements Serializable{
	private List<Product> AvailableProducts;
	private List<DiscountProduct> AvailableDiscounted;
	private RewardProcessor Rewards;
	private Cart savedCart;
	
	public AvailableProducts(){
		AvailableProducts = new ArrayList<Product>();
		AvailableDiscounted = new ArrayList<DiscountProduct>();
		Rewards = new RewardProcessor();
		savedCart = new Cart();
	}
	
	public void setAPL(List<Product> newapl){
		AvailableProducts=newapl;
	}
	
	public void addAP(Product p){
		AvailableProducts.add(p);
	}
	public void addDP(DiscountProduct d){
		AvailableDiscounted.add(d);
	}
	public void addRP(RewardProcessor r){
		Rewards = r;
	}
	
	public void setCart(Cart cart){
		savedCart = cart;
	}

	public RewardProcessor getRP(){
		return Rewards;
	}
	public List<Product> getAP(){
		return AvailableProducts;
	}
	public List<DiscountProduct> getAD(){
		return AvailableDiscounted;
	}
	
	public Cart getCart(){
		return savedCart;
	}
}

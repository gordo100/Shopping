package controller;

import model.*;
import view.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TitledPane;



public class CustomerController {

	//fields to be used throughout the class
	private shoppingRootPane view;
	private LoginPane lp;
	private static MarketPane mp;
	private CartPane cp;
	private Customer cu;
	private Name cname = new Name();
	private static Cart cart = new Cart();
	private Date date = new Date();
	private AvailableProducts ap;
	private RewardProcessor rprocessor;

	public CustomerController(shoppingRootPane view, Customer cu) {
		//initialise model and view fields
		this.cu = cu;
		this.view = view;
		lp = view.getLoginPane();
		mp = view.getMarketPane();
		cp = view.getCartPane();

		grabInfo();

		//attach event handlers to view using private helper method
		this.attachEventHandlers();
	}
	private void attachEventHandlers() {
		lp.addSubmitHandler(new LoginSubmitHandler()); //attaches login button handler
		mp.addToCartHandler(new AddToCartHandler());
		cp.addBuyNowHandler(new SubmitCartHandler());
	}

	private class AddToCartHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent e) {
			boolean cont = true;
			System.out.println(mp.getSelectedItem());
			Product selectedProduct = new Product();
			selectedProduct = mp.getSelectedItem();

			for (Order o : cart){
				if (o.getProduct().getProductCode().equals(selectedProduct.getProductCode())){
					cont = false;
				}
			}

			if (cont){
				Order productOrder = new Order();
				productOrder.setProduct(selectedProduct);
				productOrder.increaseQuantity();


				cart.addOrder(productOrder);
				cart.setCartId("CART1");
				cart.setShopper(cu);
				//cart.setDeliveryDate(deliveryDate);
				cp.updateCartView(cart);
				mp.updateCartView(cart);
				marketPaneCartNo();
				}
			}
	}

	private void grabInfo(){
		try {
	    	  FileInputStream fis = new FileInputStream("src/o.tmp");
	  	      ObjectInputStream ois = new ObjectInputStream(fis);
	  	    try {
				AvailableProducts ap = (AvailableProducts)ois.readObject();
				cart = ap.getCart();
				cp.updateCartView(cart);
				rprocessor = ap.getRP();
				List<Product> apl = ap.getAP();
				List<DiscountProduct> adl = ap.getAD();
				int i;
				for (Product tmpP : apl){
					mp.addProductToPList(tmpP);
				}
				//for (DiscountProduct tmpD : adl){
					//mp.addProductToDList(tmpD);
				//}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private class LoginSubmitHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			cname.setFirstName(lp.getFNameInput());
			cname.setFamilyName(lp.getLNameInput());
			System.out.println(cname.toString());
			cu.setCustomerName(cname);
			String idp1 = cu.getCustomerName().getFamilyName().toUpperCase();
			String idp2 = cu.getCustomerName().getFirstName().substring(0, 1).toUpperCase();
			cu.setCustomerId(idp1+idp2+"0");
			System.out.println(cu.toString());
			mp.changeCustomerIDlbl(cu.getCustomerId());
			view.enableTabs();
			view.changeTab();
			cart.clear();
			}
	}

	private class SubmitCartHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			cart.setDeliveryDate(cp.getDate(date));
			cu.addRewardPoints(rprocessor.rewardPoints(cart));
			System.out.println(cart.toString());

			PrintWriter writer;
			try {
				writer = new PrintWriter("src/receipt.txt", "UTF-8");
				writer.println(cart.toString().replace("], ", "\n").replace("[", "\n"));
				writer.close();
				cart.clear();
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Order Submitted");
				alert.setHeaderText("Thank you for your order");
				alert.setContentText("Your order is on its way!");
				alert.showAndWait();
				System.exit(0);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
		}
	public static void marketPaneCartNo(){
		mp.setCartSizeLbl(cart);
	}
	public void uponClose(){
		AvailableProducts ap = new AvailableProducts();
	try {
    	  FileInputStream fis = new FileInputStream("src/o.tmp");
  	      ObjectInputStream ois = new ObjectInputStream(fis);
  	    try {
  	    	ap = (AvailableProducts)ois.readObject();
  	    	ap.setCart(cart);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
		fis.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
    	FileOutputStream fos = new FileOutputStream("src/o.tmp");
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(ap);
		oos.close();
		fos.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
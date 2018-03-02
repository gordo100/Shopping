package view;

import model.Cart;
import model.DiscountProduct;
import model.Order;
import model.Cart;
import model.Product;
import view.CartItemPane;
import view.CartPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.security.auth.callback.Callback;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.SelectionMode;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MarketPane<plist> extends GridPane{

	Label customerIDlbl = new Label("---------");
	protected ListView plist = new ListView();
	protected ListView dlist = new ListView();
    private Button btnAddCart = new Button("Add to Cart");
    private Label lblInCartNo = new Label("Item's in cart: 0");
	private ArrayList<CartItemPane> cartObjects = new ArrayList<>();
	private Label lblTotalCost = new Label("Total Cost = ");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MarketPane (){

		customerIDlbl.setWrapText(true);
		//create labels
		Label plblTitle = new Label("Products:");
		this.setPadding(40,40);
		this.setAlignment(Pos.CENTER);
		this.add(plblTitle,1,0);
		//Label dlblTitle = new Label("Discounted Products:");
		//this.add(dlblTitle, 1,0);
		this.add(customerIDlbl, 3,3);
		this.add(lblInCartNo,3,0);

		//GridPane ctpane = new GridPane();

		//imggridpane.getChildren().add(new ImageView(image));
		//this.add(imggridpane, 2,2);
		//this.add(crtPane, 1, 4);



		//add to cart button

		this.add(btnAddCart, 0,2);


		//normal list
		plist.setCellFactory(lv -> new ListCell<Product>() {
		    @Override
		    public void updateItem(Product item, boolean empty) {
		        super.updateItem(item, empty);
		        if (empty) {
		            setText(null);
		        } else {
		            String text = item.getDescription()+" \t\t\t "+ item.getUnitPrice() + "p"; // get text from item
		            setText(text);
		        }
		    }

		});
		this.add(plist,1,1);

		//this.setRight(clist);
		//clist.setCellFactory(lv -> new ListCell<CartItemPane>() {
		  //@Override
		  	//	public void updateItem(Product item, boolean empty) {
		      //  super.updateItem(item, empty);
		        //if (empty) {
		        	//setText(null);
		       // } else {
		         // String text = item.getDescription()+" \t\t\t "+ item.getUnitPrice() + "p"; // get text from item
		        // setText(text);
		       // }
		 //   }

//	});
		plist.setOnMouseClicked(new EventHandler(){
			@Override
			public void handle(Event event) {
				dlist.getSelectionModel().select(-1);
				}});


		//discounted list
		//dlist.setCellFactory(lv -> new ListCell<DiscountProduct>() {
		  //  @Override
		    //public void updateItem(DiscountProduct item, boolean empty) {
		      // super.updateItem(item, empty);
		        //if (empty) {
		          //  setText(null);
		      //  } else {
		        //    String text = item.getDescription()+" \t\t "+ item.getUnitPrice()+"p (-"+item.getDiscountRate()*100+"%)" ; // get text from item
		          //  setText(text);
		      //  }
		   // }

	//	});
	}

		private void setPadding(int i, int j) {
		// TODO Auto-generated method stub
		
	}

		//dlist.setOnMouseClicked(new EventHandler(){
			//@Override
			//void handle(Event event) {
				//plist.getSelectionModel().select(-1);
				//}});

	//method to attach the add button handler
	public void addToCartHandler(EventHandler<ActionEvent> handler) {
			btnAddCart.setOnAction(handler);
		}

	public Product getSelectedItem(){
		if (plist.getSelectionModel().getSelectedIndex()>=0){
			return (Product) plist.getSelectionModel().getSelectedItem();
		}
		else if (dlist.getSelectionModel().getSelectedIndex()>=0){
			DiscountProduct dp = (DiscountProduct) dlist.getSelectionModel().getSelectedItem();
			Product returnp = new Product();
			returnp.setDescription(dp.getDescription());
			returnp.setProductCode(dp.getProductCode());
			returnp.setUnitPrice(dp.getUnitPrice());
			return returnp;
		}
		else{
			return null;
		}

	}



	public void updateCartView(Cart cart){
		int i;

		//clear list and old objects
		this.getChildren().remove(this.getChildren().size()-cartObjects.size(),this.getChildren().size());
		cartObjects.clear();

		//populate list and add objects
		for (i=0;i<cart.numberOfOrders();i++){
			cartObjects.add(new CartItemPane(cart.getOrder(i),cart,lblTotalCost));
			}
		}

	public void addProductToPList (Product p){
		plist.getItems().add(p);
	}
//	public void addProductToDList (DiscountProduct d){
//		dlist.getItems().add(d);
//	}

	public void changeCustomerIDlbl(String idhere){
		customerIDlbl.setText("Welcome "+idhere);
	}

	public void setCartSizeLbl (Cart cart){
		lblInCartNo.setText("Items in Cart: "+cart.numberOfOrders());

	}

}

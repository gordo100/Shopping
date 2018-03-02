package view;

import model.Cart;
import model.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class CartPane extends GridPane{
	private ArrayList<CartItemPane> cartObjects = new ArrayList<>();

	private Label lblTotalCost = new Label("Total Cost = ");
	private Button btnBuyNow = new Button("Order Now");
	private DatePicker datePicker = new DatePicker(LocalDate.now().plusDays(3));


	public CartPane (){
		//styling
		this.setPadding(new Insets(80, 80, 80, 80));
		this.setVgap(15);
		this.setHgap(20);
		this.setAlignment(Pos.CENTER);

		//button
		this.add(btnBuyNow,1,4);

		//create labels
		Label lblCartText = new Label("Products:");
		this.add(lblCartText,0,0);
		this.add(lblTotalCost,1,1);

		//date picker
		Label lbldatepicker = new Label("Delivery Date");
		this.add(lbldatepicker, 1, 2);
		this.add(datePicker, 1, 3);

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
		for (i=0;i<cartObjects.size();i++){
			this.add(cartObjects.get(i),0,i+1);
			}
		}
	public void addBuyNowHandler(EventHandler<ActionEvent> handler) {
		btnBuyNow.setOnAction(handler);
	}

	public Date getDate(Date date){
		date.setDay(datePicker.getValue().getDayOfMonth());
		date.setMonth(datePicker.getValue().getMonthValue());
		date.setYear(datePicker.getValue().getYear());

		return date;
	}

}
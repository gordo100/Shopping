package view;


import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import model.Cart;

public class shoppingRootPane extends Accordion{

	private MarketPane mp;
	private CartPane cp;
	private LoginPane lp;
	private TitledPane t0;
	private TitledPane t1;
	private TitledPane t2;



	public shoppingRootPane() {
		mp = new MarketPane();
		cp = new CartPane();
		lp = new LoginPane();


		//create tabs with panes added
		t0 = new TitledPane("Login Pane", lp);
		t1 = new TitledPane("Market Pane", mp);
		t2 = new TitledPane("Cart Pane", cp);


		//add tabs to tab pane
		this.getPanes().setAll(t0,t1,t2);
		this.setExpandedPane(t0);
		setVisible(true);
		t1.setDisable(true);
		t2.setDisable(true);
	}


	public MarketPane getMarketPane() {
		return mp;
	}

	public CartPane getCartPane() {
		return cp;
	}

	public LoginPane getLoginPane() {
		return lp;
	}

	public void enableTabs(){
		t1.setDisable(false);
		t2.setDisable(false);
		t0.setDisable(true);
	}

	public void changeTab() {
		this.setExpandedPane(t1);
	}
}

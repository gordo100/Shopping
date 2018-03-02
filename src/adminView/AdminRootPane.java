package adminView;

import javafx.scene.control.Accordion;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import adminView.*;

public class AdminRootPane extends Accordion{

	private ProductsPane pp;
	private DiscountsPane dp;
	private RewardsPane rp;

	public AdminRootPane() {
		//this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); //don't allow tabs to be closed

		pp = new ProductsPane();
		dp = new DiscountsPane();
		rp = new RewardsPane();

		//create tabs with panes added
		TitledPane t0 = new TitledPane("Add/Remove", pp);
		TitledPane t1 = new TitledPane("Discounts",dp);
		TitledPane t3 = new TitledPane("Rewards Scheme",rp);

		//add tabs to tab pane
		this.getPanes().addAll(t0,t1,t3);

		setVisible(true);
		this.setExpandedPane(t0);

	}

	public ProductsPane getProductsPane() {
		return pp;
	}

	public DiscountsPane getDiscountsPane() {
		return dp;
	}

	public RewardsPane getRewardsPane() {
		return rp;
	}


	public void changeTab(TitledPane index) {
		this.setExpandedPane(index);
	}
}

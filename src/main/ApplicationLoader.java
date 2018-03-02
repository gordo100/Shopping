package main;

import java.io.File;
import java.util.Scanner;

import controller.CustomerController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import view.*;


public class ApplicationLoader extends Application{

	private shoppingRootPane view;
	private CustomerController cc;


	@Override
	public void init() {
		//create model and view and pass their references to the controller
		Customer cu = new Customer();
		view = new shoppingRootPane();
		cc = new CustomerController(view, cu);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setMinWidth(900); //sets min width and height for the stage window
		stage.setMinHeight(800);
		stage.setTitle("Fabulous Shopping Extravaganza!");
		stage.setScene(new Scene(view));
		stage.show();
	}

	public static void main(String[] args) {
		//populate list
		launch(args);
	}

	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    cc.uponClose();
	}
}

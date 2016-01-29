package application.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import application.view.GrainProductsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

public class FoodController implements Initializable {

	public MainApp application;

	public void setApp(MainApp application) {
		this.application = application;
	}

	@FXML
	private void handleOpen()	{
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showOpenDialog(application.getStage());
		
		if(file != null)	{
			application.loadFoodDataFromFile(file);
		}
	}
	
	@FXML
	private void handleSave()	{
		File foodFile = application.getFoodFilePath();
		if(foodFile != null)	{
			application.saveFoodDataToFile(foodFile);
		} else	{
			handleSaveAs();
		}
	}
	
	@FXML
	private void handleSaveAs()	{
		FileChooser fileChooser = new FileChooser();
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
				"XML files (*.xml", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		File file = fileChooser.showOpenDialog(application.getStage());
		
		if(file != null)	{
			if(!file.getPath().endsWith(".xml"))	{
				file = new File(file.getPath() + ".xml");
			}
			application.saveFoodDataToFile(file);
		}
	}
	
	@FXML
	private void handleExit()	{
		System.exit(0);
	}
	
	@FXML
	private void goToGrainProducts() {
		GrainProductsController controller = null;
		try {
			controller = (GrainProductsController) application.replaceSceneContent("view/GrainProducts.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToMilkProducts() {
		MilkProductsController controller = null;
		try {
			controller = (MilkProductsController) application.replaceSceneContent("view/MilkProducts.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToMeatProducts() {
		MeatProductsController controller = null;
		try {
			controller = (MeatProductsController) application.replaceSceneContent("view/MeatProducts.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToVegetablesAndFruits() {
		VegetablesAndFruitsController controller = null;
		try {
			controller = (VegetablesAndFruitsController) application
					.replaceSceneContent("view/VegetablesAndFruits.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToOilProducts() {
		OilProductsController controller = null;
		try {
			controller = (OilProductsController) application.replaceSceneContent("view/OilProducts.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void goToSugarProducts() {
		SugarProductsController controller = null;
		try {
			controller = (SugarProductsController) application.replaceSceneContent("view/SugarProducts.fxml");
			controller.setApp(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

}

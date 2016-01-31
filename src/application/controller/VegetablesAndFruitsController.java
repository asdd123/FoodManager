package application.controller;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;

public class VegetablesAndFruitsController extends CategoriesController {

	private static int category = 6;

	@FXML
	public void handleNewFood() {
		application.goToEditDialogFood(category);
	}

	@FXML
	public void back() {
		application.goToOverview();
	}

	@FXML
	protected void handleDelete() {
		super.handleDelete();
	}

	@FXML
	public void scan() {
		super.scan(new VegetablesAndFruitsController());

	}

	private void showVegetablesAndFruitProductDetails(Food food) {
		super.showProductDetails(food);
	}

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getVegetablesAndFruitProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showVegetablesAndFruitProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showVegetablesAndFruitProductDetails(newValue));

	}

}

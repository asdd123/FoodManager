package application.controller;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;

public class MeatProductsController extends CategoriesController {

	private static int category = 2;

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
		super.scan(new MeatProductsController());

	}

	private void showMeatProductDetails(Food food) {
		super.showProductDetails(food);
	}

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getMeatAndEggsProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showMeatProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showMeatProductDetails(newValue));

	}

}

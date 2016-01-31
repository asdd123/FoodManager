package application.controller;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;

public class OilProductsController extends CategoriesController {

	private static int category = 4;

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
		super.scan(new OilProductsController());

	}

	private void showOilProductDetails(Food food) {
		super.showProductDetails(food);
	}

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getOilProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showOilProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showOilProductDetails(newValue));

	}

}

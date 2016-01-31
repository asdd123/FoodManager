package application.controller;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;

public class SugarProductsController extends CategoriesController {

	private static int category = 5;

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
		super.scan(new SugarProductsController());

	}

	private void showSugarProductDetails(Food food) {
		super.showProductDetails(food);
	}

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getSugarProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showSugarProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showSugarProductDetails(newValue));

	}

}

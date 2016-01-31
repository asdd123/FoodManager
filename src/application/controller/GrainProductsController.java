package application.controller;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;

public class GrainProductsController extends CategoriesController {

	private static int category = 1;

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
		super.scan(new GrainProductsController());
	}

	private void showGrainProductDetails(Food food) {
		super.showProductDetails(food);
	}

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getGrainProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showGrainProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showGrainProductDetails(newValue));
	}

}

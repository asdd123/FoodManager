package application.view;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SugarProductsController extends CategoriesController implements Initializable {

	@FXML
	public void handleNewFood() {
		application.goToEditDialogFood(5);
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

	public int getSelectedCategory() {
		return selectedCategory;
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

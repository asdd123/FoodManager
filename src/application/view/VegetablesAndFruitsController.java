package application.view;

import application.MainApp;

import application.model.Food;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class VegetablesAndFruitsController extends CategoriesController implements Initializable {

	@FXML
	public void handleNewFood() {
		application.goToEditDialogFood(6);
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

	public int getSelectedCategory() {
		return selectedCategory;
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

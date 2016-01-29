package application.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.MainApp;
import application.model.DateUtil;
import application.model.Food;
import application.model.FoodDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public abstract class CategoriesController implements Initializable {
	@FXML
	public TableView<Food> foodTable;
	@FXML
	public TableColumn<Food, String> nameColumn;
	@FXML
	public Label nameLabel;
	@FXML
	public Label producerLabel;
	@FXML
	public Label weightLabel;
	@FXML
	public Label volumeLabel;
	@FXML
	public Label piecesLabel;
	@FXML
	public Label priceLabel;
	@FXML
	public Label purchaseDateLabel;
	@FXML
	public Label todaysDateLabel;
	@FXML
	public Label expirationDateLabel;

	public MainApp application;
	public FoodDAO fooddao;
	public int selectedCategory;

	public abstract void handleNewFood();

	public abstract int getSelectedCategory();

	public abstract void setApp(MainApp application);

	public abstract void back();

	protected void handleDelete() {
		int selectedIndex = foodTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			foodTable.getItems().remove(selectedIndex);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(application.getStage());
			alert.setTitle("Brak wyboru");
			alert.setHeaderText("Nie wybrano produktu");
			alert.setContentText("Proszê wybraæ produkt z tabeli");

			alert.showAndWait();

		}

	}

	public void showProductDetails(Food food) {
		if (food != null) {
			nameLabel.setText(food.getName());
			producerLabel.setText(food.getProducer());
			weightLabel.setText(food.getWeight().toString());
			volumeLabel.setText(food.getVolume().toString());
			piecesLabel.setText(food.getPieces().toString());
			priceLabel.setText(food.getPrice().toString());
			purchaseDateLabel.setText(DateUtil.format(food.getPurchaseDate()));
			todaysDateLabel.setText(DateUtil.format(java.time.LocalDate.now()));
			expirationDateLabel.setText(DateUtil.format(food.getExpirationDate()));
		} else {
			nameLabel.setText("");
			producerLabel.setText("");
			weightLabel.setText("");
			volumeLabel.setText("");
			piecesLabel.setText("");
			priceLabel.setText("");
			purchaseDateLabel.setText("");
			todaysDateLabel.setText("");
			expirationDateLabel.setText("");
		}
	}

	public void scan(Object o) {
		boolean isListEmpty;

		if (o.getClass() == GrainProductsController.class) {
			isListEmpty = application.getFoodDAO().getGrainProductsData().isEmpty();
		} else if (o.getClass() == MeatProductsController.class) {
			isListEmpty = application.getFoodDAO().getMeatAndEggsProductsData().isEmpty();
		} else if (o.getClass() == MilkProductsController.class) {
			isListEmpty = application.getFoodDAO().getMilkProductsData().isEmpty();
		} else if (o.getClass() == OilProductsController.class) {
			isListEmpty = application.getFoodDAO().getOilProductsData().isEmpty();
		} else if (o.getClass() == SugarProductsController.class) {
			isListEmpty = application.getFoodDAO().getSugarProductsData().isEmpty();
		} else if (o.getClass() == VegetablesAndFruitsController.class) {
			isListEmpty = application.getFoodDAO().getVegetablesAndFruitProductsData().isEmpty();
		} else {
			isListEmpty = true;
		}

		String errorMsg = "";
		if (!isListEmpty) {

			for (Food food : application.getFoodDAO().getGrainProductsData()) {
				LocalDate foodDate = food.getExpirationDate();
				LocalDate todaysDate = java.time.LocalDate.now();
				if (foodDate.getYear() < todaysDate.getYear()) {
					errorMsg += food.getName() + "\n";
				} else if (foodDate.getYear() == todaysDate.getYear()
						&& foodDate.getDayOfYear() <= todaysDate.getDayOfYear()) {
					errorMsg += food.getName() + "\n";
				}

			}

			if (errorMsg != null && errorMsg.length() != 0) {
				showExpiredFoodName(errorMsg);
			} else {
				everythingIsOk();
			}

		} else {
			lackOfProducts();
		}

	}

	public void showExpiredFoodName(String errorMsg) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.initOwner(application.getStage());
		alert.setTitle("Przeterminowanie");
		alert.setHeaderText("Produkt jest przeterminowany");
		alert.setContentText("Przeterminowany produkt:\n" + errorMsg);

		alert.showAndWait();
	}

	public void everythingIsOk() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(application.getStage());
		alert.setTitle("Informacja");
		alert.setHeaderText("Wszystkie produkty s¹ w porz¹dku");
		alert.setContentText("Skanowanie zakoñczone - brak przeterminowanych produktów");
		alert.showAndWait();
	}

	public void lackOfProducts() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(application.getStage());
		alert.setTitle("Informacja");
		alert.setHeaderText("Brak produktów");
		alert.setContentText("Skanowanie nie nast¹pi³o z powodu braku produktów");
		alert.showAndWait();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
}

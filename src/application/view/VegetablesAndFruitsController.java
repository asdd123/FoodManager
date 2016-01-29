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

public class VegetablesAndFruitsController implements Initializable {
	@FXML
	private TableView<Food> foodTable;
	@FXML
	private TableColumn<Food, String> nameColumn;
	@FXML
	private Label nameLabel;
	@FXML
	private Label producerLabel;
	@FXML
	private Label weightLabel;
	@FXML
	private Label volumeLabel;
	@FXML
	private Label piecesLabel;
	@FXML
	private Label priceLabel;
	@FXML
	private Label purchaseDateLabel;
	@FXML
	private Label todaysDateLabel;
	@FXML
	private Label expirationDateLabel;

	private MainApp application;
	private FoodDAO fooddao;
	private int selectedCategory;

	@FXML
	private void handleNewFood() {
		application.goToEditDialogFood(6);
	}

	@FXML
	private void handleDelete() {
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

	@FXML
	private void back() {
		application.goToOverview();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	private void showVegetablesAndFruitProductDetails(Food food) {
		if (food != null) {
			nameLabel.setText(food.getName());
			producerLabel.setText(food.getProducer());
			weightLabel.setText(food.getWeight().toString());
			volumeLabel.setText(food.getVolume().toString());
			piecesLabel.setText(String.valueOf(food.getPieces()));
			priceLabel.setText(String.valueOf(food.getPrice()));
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

	public void setApp(MainApp application) {
		fooddao = application.getFoodDAO();
		foodTable.setItems(fooddao.getVegetablesAndFruitProductsData());
		this.application = application;
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());

		showVegetablesAndFruitProductDetails(null);

		foodTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showVegetablesAndFruitProductDetails(newValue));

	}

	@FXML
	private void scan() {
		boolean isListEmpty = application.getFoodDAO().getVegetablesAndFruitProductsData().isEmpty();
		String errorMsg = "";
		if (!isListEmpty) {

			for (Food food : application.getFoodDAO().getVegetablesAndFruitProductsData()) {
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

	public int getSelectedCategory() {
		return selectedCategory;
	}
}

package anon.application.view;

import java.net.URL;
import java.util.ResourceBundle;

import anon.application.MainApp;
import anon.application.model.DateUtil;
import anon.application.model.Food;
import anon.application.model.FoodDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FoodEditDialogController implements Initializable {

	@FXML
	private TextField nameField;
	@FXML
	private TextField producerField;
	@FXML
	private TextField weightField;
	@FXML
	private TextField volumeField;
	@FXML
	private TextField piecesField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField purchaseDateField;
	@FXML
	private TextField expirationDateField;

	private Stage dialogStage;
	private Food food;
	private FoodDAO fooddao;
	private MainApp application;
	private int selectedCategory;

	@FXML
	private void initialize() {
	}

	@FXML
	private void handleOk() {
		food = new Food();
		fooddao = application.getFoodDAO();
		if (isInputValid()) {
			food.setCategory(selectedCategory);
			food.setName(nameField.getText());
			food.setProducer(producerField.getText());
			food.setWeight(Double.parseDouble(weightField.getText()));
			food.setVolume(Double.parseDouble(volumeField.getText()));
			food.setPieces(Double.parseDouble(piecesField.getText()));
			food.setPrice(Double.parseDouble(priceField.getText()));
			food.setPurchaseDate(DateUtil.parse(purchaseDateField.getText()));
			food.setExpirationDate(DateUtil.parse(expirationDateField.getText()));
			if (selectedCategory == 1) {
				fooddao.getGrainProductsData().add(food);
				application.goToOverview();
				;
			} else if (selectedCategory == 2) {
				fooddao.getMeatAndEggsProductsData().add(food);
				application.goToOverview();
			} else if (selectedCategory == 3) {
				fooddao.getMilkProductsData().add(food);
				application.goToOverview();
			} else if (selectedCategory == 4) {
				fooddao.getOilProductsData().add(food);
				application.goToOverview();
			} else if (selectedCategory == 5) {
				fooddao.getSugarProductsData().add(food);
				application.goToOverview();
			} else if (selectedCategory == 6) {
				fooddao.getVegetablesAndFruitProductsData().add(food);
				application.goToOverview();
			}
		} else {

		}

	}

	@FXML
	private void handleCancel() {
		application.goToOverview();
	}

	public void setApp(MainApp application, int selectedCategory) {
		this.application = application;
		this.selectedCategory = selectedCategory;
	}

	private boolean isInputValid() {
		String errormsg = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errormsg += "Brak nazwy\n";
		}

		if (weightField.getText() == null || weightField.getText().length() == 0) {
			weightField.setText("0");
			try {
				Double.parseDouble(weightField.getText());
			} catch (NumberFormatException e) {
				errormsg += "B³¹d w wadze\n";
			}
		}

		if (volumeField.getText() == null || volumeField.getText().length() == 0) {
			volumeField.setText("0");
			try {
				Double.parseDouble(volumeField.getText());
			} catch (NumberFormatException e) {
				errormsg += "B³¹d w objêtoœci\n";
			}
		}

		if (piecesField.getText() == null || piecesField.getText().length() == 0) {
			piecesField.setText("0");
			try {
				Double.parseDouble(piecesField.getText());
			} catch (NumberFormatException e) {
				errormsg += "B³¹d w iloœci sztuk\n";
			}
		}

		if (priceField.getText() == null || priceField.getText().length() == 0) {
			priceField.setText("0");
			try {
				Double.parseDouble(priceField.getText());
			} catch (NumberFormatException e) {
				errormsg += "B³¹d w cenie\n";
			}
		}

		if (expirationDateField.getText() == null || expirationDateField.getText().length() == 0) {
			errormsg += "Proszê podaæ datê wa¿noœci\n";
		} else {
			if (!DateUtil.validDate(expirationDateField.getText())) {
				errormsg += "B³¹d w dacie wa¿noœci. Proszê u¿yæ formatu dd.mm.yyyy.\n";
			}
		}

		if (purchaseDateField.getText() == null || purchaseDateField.getText().length() == 0) {
			errormsg += "Proszê podaæ datê zakupu";
		} else {
			if (!DateUtil.validDate(purchaseDateField.getText())) {
				errormsg += "B³¹d w dacie zakupu. Proszê u¿yæ formatu dd.mm.yyyy.\n";
			}
		}

		if (errormsg.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("B³¹d w polach");
			alert.setHeaderText("Proszê poprawiæ b³êdne pola");
			alert.setContentText(errormsg);
			alert.showAndWait();
			return false;
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}

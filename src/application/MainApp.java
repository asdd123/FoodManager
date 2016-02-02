package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import application.controller.FoodController;
import application.model.Food;
import application.model.FoodDAO;
import application.model.FoodListWrapper;
import application.controller.FoodEditDialogController;

public class MainApp extends Application {

	private Stage primaryStage;
	private MainApp application;
	private FoodDAO fooddao;
	private Scene scene;
	
	public MainApp() {
	
	}

	@Override
	public void init()	{
		fooddao = new FoodDAO();
	}

	@Override
	public void start(Stage primaryStage) {
		application = new MainApp();
		this.primaryStage = primaryStage;
		primaryStage.setTitle("FoodApp");
		goToOverview();
		primaryStage.show();
	}

	public void goToOverview() {
		FoodController controller = null;
		try {
			controller = (FoodController) replaceSceneContent("view/FoodOverview.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.setApp(this);
	}

	public void goToEditDialogFood(int selectedCategory)	{
		FoodEditDialogController controller = null;
		try {
			controller = (FoodEditDialogController) replaceSceneContent("view/FoodEditDialog.fxml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		controller.setApp(this, selectedCategory);
	}
	
	public Initializable replaceSceneContent(String fxml) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		InputStream in = MainApp.class.getResourceAsStream(fxml);
		loader.setBuilderFactory(new JavaFXBuilderFactory());
		loader.setLocation(MainApp.class.getResource(fxml));
		AnchorPane page = null;
		try {
			page = (AnchorPane) loader.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		
		scene = new Scene(page);
		
		String css;
		
		if(fxml.equals("view/FoodOverview.fxml"))	{
			css = this.getClass().getResource("view/FoodOverview.css").toExternalForm();
			scene.getStylesheets().add(css);
		} else if(fxml.equals("view/FoodEditDialog.fxml"))	{
			css = this.getClass().getResource("view/FoodEdit.css").toExternalForm();
			scene.getStylesheets().add(css);
		} else	{
			css = this.getClass().getResource("view/Categories.css").toExternalForm();
			scene.getStylesheets().add(css);
		}
		
		primaryStage.setScene(scene);
		return (Initializable) loader.getController();
	}

	public File getFoodFilePath()	{
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if(filePath != null)	{
			return new File(filePath);
		} else {
			return null;
		}
	}
	
	public void setFoodFilePath(File file)	{
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null)	{
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("FoodApp - " + file.getName());
		} else	{
			prefs.remove("filePath");
			primaryStage.setTitle("FoodApp");
		}
	}
	
	public void loadFoodDataFromFile(File file)	{
		try	{
			fooddao = getFoodDAO();
			JAXBContext context = JAXBContext.newInstance(FoodListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			FoodListWrapper wrapper = (FoodListWrapper) um.unmarshal(file);
			
			fooddao.getGrainProductsData().clear();
			fooddao.getMeatAndEggsProductsData().clear();
			fooddao.getMilkProductsData().clear();
			fooddao.getOilProductsData().clear();
			fooddao.getSugarProductsData().clear();
			fooddao.getVegetablesAndFruitProductsData().clear();
			List<Food> food = wrapper.getFood();
			for(Food foodProduct : food)	{
				int category = foodProduct.getCategory();
				if(category == 1)	{
					fooddao.getGrainProductsData().add(foodProduct);
				} else if(category == 2)	{
					fooddao.getMeatAndEggsProductsData().add(foodProduct);
				} else if(category == 3)	{
					fooddao.getMilkProductsData().add(foodProduct);
				} else if(category == 4)	{
					fooddao.getOilProductsData().add(foodProduct);
				} else if(category == 5)	{
					fooddao.getSugarProductsData().add(foodProduct);
				} else if(category == 6)	{
					fooddao.getVegetablesAndFruitProductsData().add(foodProduct);
				}
			}
			setFoodFilePath(file);
		} catch (Exception e)	{
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("B³¹d");
			alert.setHeaderText("Nie mo¿na za³adowaæ danych");
			alert.setContentText("Nie mo¿na za³adowaæ danych z pliku:\n" + file.getPath());
			alert.showAndWait();
		}
	}
	
	public void saveFoodDataToFile(File file)	{
		try	{
			fooddao = getFoodDAO();
			JAXBContext context = JAXBContext.newInstance(FoodListWrapper.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			FoodListWrapper wrapper = new FoodListWrapper();
			
			if(!fooddao.getGrainProductsData().isEmpty()){
				wrapper.setFood(fooddao.getGrainProductsData());
			}
			if(!fooddao.getMeatAndEggsProductsData().isEmpty()){
				wrapper.setFood(fooddao.getMeatAndEggsProductsData());
			}
			if(!fooddao.getMilkProductsData().isEmpty()){
				wrapper.setFood(fooddao.getMilkProductsData());
			}
			if(!fooddao.getOilProductsData().isEmpty()){
				wrapper.setFood(fooddao.getOilProductsData());
			}
			if(!fooddao.getSugarProductsData().isEmpty()){
				wrapper.setFood(fooddao.getSugarProductsData());
			}
			if(!fooddao.getVegetablesAndFruitProductsData().isEmpty()){
				wrapper.setFood(fooddao.getVegetablesAndFruitProductsData());
			}
		
			m.marshal(wrapper, file);
			
			setFoodFilePath(file);
		} catch(Exception e)	{
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("B³¹d");
			alert.setHeaderText("Nie mo¿na zapisaæ danych");
			alert.setContentText("Nie mo¿na zapisaæ danych do pliku:\n" + file.getPath());
			alert.showAndWait();
		}
	}
	
	public FoodDAO getFoodDAO()	{
		return fooddao;
	}
	
	
	public MainApp getApplication() {
		return application;
	}

	public Stage getStage() {
		return primaryStage;
	}
		
	
	public static void main(String[] args) {
		launch(args);
	}
}

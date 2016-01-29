package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FoodDAO {
	private ObservableList<Food> grainProductsData = FXCollections.observableArrayList();
	private ObservableList<Food> milkProductsData = FXCollections.observableArrayList();
	private ObservableList<Food> meatAndEggsProductsData = FXCollections.observableArrayList();
	private ObservableList<Food> vegetablesAndFruitProductsData = FXCollections.observableArrayList();
	private ObservableList<Food> oilProductsData = FXCollections.observableArrayList();
	private ObservableList<Food> sugarProductsData = FXCollections.observableArrayList();
	
	public FoodDAO()	{
		
	}
	
	public ObservableList<Food> getGrainProductsData()	{
		return grainProductsData;
	}
	
	public ObservableList<Food> getMilkProductsData()	{
		return milkProductsData;
	}
	
	public ObservableList<Food> getMeatAndEggsProductsData()	{
		return meatAndEggsProductsData;
	}
	
	public ObservableList<Food> getVegetablesAndFruitProductsData()	{
		return vegetablesAndFruitProductsData;
	}
	
	public ObservableList<Food> getOilProductsData()	{
		return oilProductsData;
	}
	
	public ObservableList<Food> getSugarProductsData()	{
		return sugarProductsData;
	}
	
	
}

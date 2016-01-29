package application.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "food")
public class FoodListWrapper {

	private List<Food> food = new ArrayList<>();
	
	@XmlElement(name = "foods")
	public List<Food> getFood()	{
		return food;
	}
	
	public void setFood(List<Food> foodToSave)	{
		food.addAll(foodToSave);
	}
}

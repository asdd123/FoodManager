package application.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Food {
	private final IntegerProperty category;
	private final StringProperty name;
	private final StringProperty producer;
	private final DoubleProperty weight;
	private final DoubleProperty pieces;
	private final DoubleProperty volume;
	private final DoubleProperty price;
	private final ObjectProperty<LocalDate> purchaseDate;
	private final ObjectProperty<LocalDate>	expirationDate;
	
	public Food()	{
		this(null);
	}
	
	public Food(String name)	{
		this.name = new SimpleStringProperty(name);
		this.producer = new SimpleStringProperty("producer");
		this.weight = new SimpleDoubleProperty(0);
		this.pieces = new SimpleDoubleProperty(0);
		this.volume = new SimpleDoubleProperty(0);
		this.price = new SimpleDoubleProperty(0);
		this.purchaseDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 1, 1));
		this.expirationDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 1, 1));
		this.category = new SimpleIntegerProperty(0);
	}
	
	public void setCategory(Integer category)	{
		this.category.set(category);
	}
	
	public Integer getCategory() {
		return category.get();
	}

	public IntegerProperty getCategoryProperty()	{
		return category;
	}
	
	public void setName(String name)	{
		this.name.set(name);
	}
	
	public String getName()	{
		return name.get();
	}

	public StringProperty getNameProperty()	{
		return name;
	}

	public void setProducer(String producer)	{
		this.producer.set(producer);
	}
	
	public String getProducer()	{
		return producer.get();
	}
	
	public StringProperty getProducerProperty()	{
		return producer;
	}
	
	public void setWeight(Double weight)	{
		this.weight.set(weight);
	}
	
	public Double getWeight()	{
		return weight.get();
	}
	
	public DoubleProperty getWeightProperty()	{
		return weight;
	}
	
	public void setPieces(Double pieces)	{
		this.pieces.set(pieces);
	}
	
	public Double getPieces()	{
		return pieces.get();
	}
	
	public DoubleProperty getPiecesProperty()	{
		return pieces;
	}
	
	public void setVolume(Double volume)	{
		this.volume.set(volume);
	}
	
	public Double getVolume()	{
		return volume.get();
	}
	
	public DoubleProperty getVolumeProperty()	{
		return volume;
	}
	
	public void setPrice(Double price)	{
		this.price.set(price);
	}
	
	public Double getPrice()	{
		return price.get();
	}
	
	public DoubleProperty getPriceProperty()	{
		return price;
	}
	
	public void setPurchaseDate(LocalDate purchaseDate)	{
		this.purchaseDate.set(purchaseDate);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getPurchaseDate()	{
		return purchaseDate.get();
	}
	
	public ObjectProperty<LocalDate> getPurchaseDateProperty()	{
		return purchaseDate;
	}
	
	public void setExpirationDate(LocalDate expirationDate)	{
		this.expirationDate.set(expirationDate);
	}
	
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getExpirationDate()	{
		return expirationDate.get();
	}
	
	public ObjectProperty<LocalDate> getExpirationDateProperty()	{
		return expirationDate;
	}
	
}



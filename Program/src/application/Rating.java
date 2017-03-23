package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rating {
	private final StringProperty number;
	private final StringProperty name;
	private final StringProperty rating;
	private final StringProperty votes;

	public Rating(String number, String name, String rating, String votes) {
		this.number = new SimpleStringProperty(number);
		this.name = new SimpleStringProperty(name);
		this.rating = new SimpleStringProperty(rating);
		this.votes = new SimpleStringProperty(votes);
	}
	
	public String getNumber() {
		return number.get();
	}

	public void setNumber(String number) {
		this.number.set(number);
	}
	
	public StringProperty numberProperty() {
		return number;
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}
	
	public String getRating() {
		return rating.get();
	}

	public void setRating(String rating) {
		this.rating.set(rating);
	}
	
	public StringProperty ratingProperty() {
		return rating;
	}

	public String getVotes() {
		return votes.get();
	}

	public void setVotes(String votes) {
		this.votes.set(votes);
	}
	
	public StringProperty votesProperty() {
		return votes;
	}
}

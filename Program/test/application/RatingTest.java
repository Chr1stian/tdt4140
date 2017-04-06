package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest {
	String testRatingNumber = "5";
	String testRatingName = "testRatingName";
	String testRatingRating = "4";
	String testRatingVotes = "5";
	

	@Test
	public void testRatingNumber() {
		Rating test = new Rating(testRatingNumber, testRatingName, testRatingRating, testRatingVotes);
		test.setNumber(testRatingNumber);
		String ratingNumber = test.getNumber();
		assertEquals(testRatingNumber, test.numberProperty().getValue());
		assertEquals(testRatingNumber, ratingNumber);
	}
	@Test
	public void testRatingName() {
		Rating test = new Rating(testRatingNumber, testRatingName, testRatingRating, testRatingVotes);
		test.setName(testRatingName);
		String ratingName = test.getName();
		assertEquals(testRatingName, test.nameProperty().getValue());
		assertEquals(testRatingName, ratingName);
	}
	@Test
	public void testRatingRating() {
		Rating test = new Rating(testRatingNumber, testRatingRating, testRatingRating, testRatingVotes);
		test.setRating(testRatingRating);
		String ratingRating = test.getRating();
		assertEquals(testRatingRating, test.ratingProperty().getValue());
		assertEquals(testRatingRating, ratingRating);
	}
	@Test
	public void testRatingVotes() {
		Rating test = new Rating(testRatingNumber, testRatingVotes, testRatingRating, testRatingVotes);
		test.setVotes(testRatingVotes);
		String ratingVotes = test.getVotes();
		assertEquals(testRatingVotes, test.votesProperty().getValue());
		assertEquals(testRatingVotes, ratingVotes);
	}
	

}

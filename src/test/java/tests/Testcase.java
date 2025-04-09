package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import utils.Log;

@Listeners(TestListener.class)
public class Testcase extends Basetest {

	@Test(dataProvider = "testdata", dataProviderClass = ExcelReading.class, groups = { "smoke", "regression" })
	public void testFlightBooking(String departureCity, String destinationCity) {
		try {
			Assert.assertEquals(homepage.getPageTitle(), "Welcome to the Simple Travel Agency!");
			homepage.clickdestinationofweek();
			homepage.navigateback();
			homepage.selectDepartureCity(departureCity);
			homepage.selectDestinationCity(destinationCity);
			homepage.searchFlights();

			flightBookingPage.lowestPriceFinder();
			flightBookingPage.chooseflightbtnclick();

			purchasePage.verifytotalcost();
			purchasePage.purchacebtnclick();

			String ID = confirmationPage.getconfirmationId();
			Log.info("Booking Confirmation ID: " + ID);
		} catch (Exception e) {
			e.printStackTrace();
			Log.info("Error: " + e.getMessage());
		}

	}

}
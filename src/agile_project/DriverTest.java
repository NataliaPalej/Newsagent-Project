package agile_project;

public class DriverTest {
	private boolean isValidAreaCode(int areaCode) {
        // Assuming each driver is assigned an area code from 1 to 12
        return areaCode >= 1 && areaCode <= 12;
    }
}

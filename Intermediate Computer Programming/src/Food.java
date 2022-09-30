import java.util.Arrays;

/**
 * @author JOHN  TERENCE MANFUL
 */


public class Food {

    private String foodName;
    private String chefName;
    private double calories;
    private String countryName;
    private String dietaryRequirements;
    private  double price;


    // constructor build and initialize object of this class


    public Food(String foodName, String chefName, double calories, String countryName, String dietaryRequirements,
                double price) {
        this.foodName = foodName;
        this.chefName = chefName;
        this.calories = calories;
        this.countryName = countryName;
        this.dietaryRequirements = dietaryRequirements;
        this.price = price;
    }

    public String getFoodName() {
        return this.foodName;
    }

    public String getChefName() {
        return this.chefName;
    }

    public double getCalories() {
        return this.calories;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public void setPrice(double price) {
        try {
            if (price <= 0)
                throw new InvalidPriceException();
            this.price = price;

        }
        catch (InvalidPriceException ipe){
            System.out.println(ipe.getMessage());
        }
    }

    public static void main(String[] args) {
        Food Food = new Food();

    }
}

public class InvalidPriceException extends Exception{
    public InvalidPriceException() {}
    public InvalidPriceException(String message){
        super(message);
    }

}
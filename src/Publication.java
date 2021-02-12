import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Publication {
    Scanner in = new Scanner(System.in);
    private int publication_id;
    private String publication_name;
    private double cost;
    private String frequency;
    private int stockLevel;
    private ResultSet rs = null;

    public Publication(int publication_id, String name, double cost, String frequency, int stockLevel)
    {
        this.publication_id = publication_id;
        this.publication_name = name;
        this.cost = cost;
        this.frequency = frequency;
        this.stockLevel = stockLevel;
    }

    public Publication()
    {

    }

    // Getters and setters
    public int getpublication_id()
    {
        return publication_id;
    }

    public void setpublication_id(int id)
    {
        this.publication_id = id;
    }

    public String getpublication_name()
    {
        return publication_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getStockLevel() {
        return stockLevel;
    }


    public void setStockLevel(int stockLevel) {
        this.stockLevel = stockLevel;
    }

    public void setpublication_name(String publication_name) {
        this.publication_name = publication_name;
    }



// *****************************************************************************************
// Method that asks the user to input yes or no. returns a true or false statement based on that
// *****************************************************************************************
    public boolean askUserYesOrNo(String question){
        String answer;
        boolean inputValid = false;
        boolean confirm = false;

        while (!inputValid)
        {
            System.out.println(question);
            if (in.hasNextLine())
            {
                // if customer reply is "yes" or "no", save it and exit the loo0
                in.nextLine();
                answer = in.nextLine();
                if (answer.equals("yes") || answer.equals("Yes")) {
                    inputValid = true;
                    confirm = true;

                }
                else if(answer.equals("no") || answer.equals("No")) {
                    inputValid = true;
                    confirm = false;
                }
                else {
                    System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
                }
            }
            else
            {
                //clear the input buffer and start again
                in.nextLine();
                System.out.println("You entered an invalid answer, please use \"yes\" or \"no\"...");
            }
        }
        return confirm;
    }

    // *****************************************************************************************
// Validates that the string entered only consists of numbers.
// *****************************************************************************************
    public boolean validatePublicationId(String publicationId) {

        for (int i = 0; i < publicationId.length() + 1; i++) {

            if (publicationId.charAt(i) >= '0'
                    && publicationId.charAt(i) <= '9') {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }



    // *****************************************************************************************
// Validates that the string entered only consists of letters and spaces.
// *****************************************************************************************
    public boolean validatePublicationName(String newPublication_Name) {
        if (newPublication_Name.matches("[a-zA-Z\\s\'\"]+"))
        {

            return true;
        } else
            System.out.println("You have entered an invalid character(s). Please try again only using valid characters");

        return false;

    }

    // *****************************************************************************************
// Validates that the string entered only consists of the words "Daily" or "Weekly"
// *****************************************************************************************
    public boolean validatePublicationFrequency(String newPublication_Frequency) {
        if (newPublication_Frequency.matches("[a-zA-Z]+"))
        {
            if(newPublication_Frequency.matches("Daily") || newPublication_Frequency.matches("Weekly"))
            {
                return true;
            }
            else if(newPublication_Frequency.matches("daily") || newPublication_Frequency.matches("weekly"))

                System.out.println("Publication NOT updated. Please use capitilization, thank you :)");
            return false;
        }
        else
            System.out.println("Please only enter the words, 'Daily' or 'Weekly'");
        return false;

    }

    public boolean validateANumber(String publicationCost)
    {

        for (int i = 0; i < publicationCost.length() + 1; i++) {

            if (publicationCost.charAt(i) >= '0'
                    && publicationCost.charAt(i) <= '9') {
                return true;
            } else {
                System.out.println("A character you entered is not a valid number, please try again using only valid numbers.");
                return false;
            }
        }
        System.out.println("A character you entered is not a valid number, please try again using only valid numbers.");
        return false;

    }




    @Override
    public String toString() {
        return "Publication{" +
                "publication_id=" + publication_id +
                ", publication_name='" + publication_name + '\'' +
                ", cost=" + cost +
                ", frequency='" + frequency + '\'' +
                ", stockLevel=" + stockLevel +
                '}';
    }
}

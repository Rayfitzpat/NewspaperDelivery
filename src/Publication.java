import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Publication {

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

//
//    public ArrayList<Publication> readAllPublications(Statement stmt) {
//        //1: Query the database for all students
//        //2: Display the result set in an appropriate manner
//        String str = "Select * from publication";
//
//        ArrayList listOfPublications = new ArrayList<Publication>();
//
//        try {
//            rs = stmt.executeQuery(str);
//            while (rs.next()) {
//                int spublication_id = rs.getInt("publication_id");
//                String publication_name = rs.getString("publication_name");
//                String publication_frequency = rs.getString("publication_frequency");
//                float publication_cost = rs.getFloat("publication_cost");
//                int stockLevel = rs.getInt("publication_stock_level");
//
//                listOfPublications.add(new Publication(spublication_id, publication_name, publication_cost, publication_frequency,  stockLevel));
//            }
//
//        } catch (SQLException sqle) {
//            System.out.println("Error: failed to read all students.");
//            System.out.println(sqle.getMessage());
//            System.out.println(str);
//        }
//
//        return listOfPublications;
//    }
//
//    public void displayAllPublications (ArrayList<Publication> publications)
//    {
//        System.out.printf("\n%-12s %-30s %-20s %-10s %-10s\n", "Col1", "Col2", "Col3", "Col4", "Col5");
//        for (int i = 0; i < publications.size(); i++)
//        {
//            System.out.printf("%-12d %-30s %-20f %-10d %-10s\n", publications.get(i).getpublication_id(), publications.get(i).getpublication_name(), publications.get(i).getCost(), publications.get(i).getStockLevel(), publications.get(i).getFrequency());
//        }
//    }
//
//    public void createPublication(Publication publication)
//    {
//
//    }
//
//    public void updatePublication(Publication publication)
//    {
//
//    }
//
//    public void deletePublication(int publication_id)
//    {
//
//    }
//

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

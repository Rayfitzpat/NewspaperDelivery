import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Publication {

    private int publicationId;
    private String publicationName;
    private double cost;
    private String frequency;
    private int stockLevel;
    private ResultSet rs = null;

    public Publication(int publicationId, String name, double cost, String frequency, int stockLevel)
    {
        this.publicationId = publicationId;
        this.publicationName = name;
        this.cost = cost;
        this.frequency = frequency;
        this.stockLevel = stockLevel;
    }

    public Publication()
    {

    }

    // Getters and setters
    public int getPublicationId()
    {
        return publicationId;
    }

    public void setPublicationId(int id)
    {
        this.publicationId = id;
    }

    public String getPublicationName()
    {
        return publicationName;
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

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
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
//                int spublicationId = rs.getInt("publicationId");
//                String publicationName = rs.getString("publicationName");
//                String publicationFrequency = rs.getString("publicationFrequency");
//                float publicationCost = rs.getFloat("publicationCost");
//                int stockLevel = rs.getInt("publicationStockLevel");
//
//                listOfPublications.add(new Publication(spublicationId, publicationName, publicationCost, publicationFrequency,  stockLevel));
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
//            System.out.printf("%-12d %-30s %-20f %-10d %-10s\n", publications.get(i).getPublicationId(), publications.get(i).getPublicationName(), publications.get(i).getCost(), publications.get(i).getStockLevel(), publications.get(i).getFrequency());
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
//    public void deletePublication(int publicationId)
//    {
//
//    }
//

    @Override
    public String toString() {
        return "Publication{" +
                "publicationId=" + publicationId +
                ", publicationName='" + publicationName + '\'' +
                ", cost=" + cost +
                ", frequency='" + frequency + '\'' +
                ", stockLevel=" + stockLevel +
                '}';
    }
}

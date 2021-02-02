public class CustomerMain {
    public static void main(String[] args) {
        Customer customer = new Customer();



        try {
            customer.validateHoliday("2021-29", "2021-10-9");
            System.out.println("valid");
        }
        catch (CustomerExceptionHandler e)
        {
            System.out.println("invalid");
        }


    }
}

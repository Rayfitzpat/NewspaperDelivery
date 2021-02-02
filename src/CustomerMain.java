public class CustomerMain {
    public static void main(String[] args) {
        Customer customer = new Customer();

        try {
            customer.validatePhoneNumber("g");
            System.out.println("match");
        }
        catch (CustomerExceptionHandler e)
        {
            System.out.println("now match");
        }

    }
}

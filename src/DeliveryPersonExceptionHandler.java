public class DeliveryPersonExceptionHandler extends Throwable {

        private String message;

        public DeliveryPersonExceptionHandler(String errMessage){
            message = errMessage;
        }

        public String getMessage() {
            return message;
        }
    }


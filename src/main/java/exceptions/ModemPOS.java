package exceptions;

import java.io.IOException;
import java.net.Socket;

class ModemConnectException extends Exception { }
class ModemLibrary {
    public static void dialModem(int phoneNum) throws ModemConnectException {

    }
}

class RetryException extends Exception {
    public RetryException(String message, Throwable cause) {
        super(message, cause);
    }
}
public class ModemPOS {
    private static boolean USE_DIALUP = false;

    // HOW YOU FAIL IS AS MUCH PART OF YOUR PUBLIC API AS EVERYTHING ELSE PUBLIC
    // "Modem" is an impelementation detail!!!! It should NOT be exposed
    public static void sellStuff(int value, int ccNum) throws RetryException /*ModemConnectException, IOException */{
        try {
            if (USE_DIALUP) {
                ModemLibrary.dialModem(1234);
            } else {
                Socket s = new Socket("127.0.0.1", 8000);
            }
        } catch (ModemConnectException | IOException e) {
            throw new RetryException("Environment failure...", e);
        }
        // request payment
        // hand over goods

//        assume retries
    }

    public static void beAShop() {
        try {
            sellStuff(2000, 1234);
        } catch (RetryException e) {
//        } catch (ModemConnectException | IOException mce) {
            // ask for help
        }
    }
}

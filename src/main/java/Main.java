
import Controller.PowerBallController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;


/**
 *
 */
public class Main {
    public static void main(String[] args) {
        ConnectionSingleton.getConnection();
        PowerBallController controller = new PowerBallController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }


}

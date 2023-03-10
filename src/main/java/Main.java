import Controller;//add the correct path here
import Controller.PowerBallController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;
import java.sql.Connection;
import java.sql.PreparedStatement;
//please import the controller class here



/**
 *
 */
public class Main {
    public static void main(String[] args) {
        PowerBallController controller = new PowerBallController();
        Javalin app = controller.startAPI();
        app.start(8080);
    }


}

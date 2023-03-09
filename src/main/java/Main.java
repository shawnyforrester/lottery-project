import Controller;//add the correct path here
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
        // controller = new SocialMediaController(); add the correct class here
        Javalin app = controller.startAPI();
        app.start(8080);
    }


}

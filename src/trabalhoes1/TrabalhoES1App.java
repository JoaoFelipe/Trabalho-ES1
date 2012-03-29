/*
 * TrabalhoES1App.java
 */

package trabalhoes1;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class TrabalhoES1App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
//        show(new TrabalhoES1View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of TrabalhoES1App
     */
    public static TrabalhoES1App getApplication() {
        return Application.getInstance(TrabalhoES1App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        
//        frame.show();
        launch(TrabalhoES1App.class, args);
    }
}

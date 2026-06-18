import data.AppData;
import ui.WelcomeScreen;

public class MainProgram {
    public static void main(String[] args) {
        AppData.loadAll();
        new WelcomeScreen();
    }
}
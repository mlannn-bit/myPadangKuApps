import data.AppData;
import ui.WelcomeScreen;

public class Main {
    public static void main(String[] args) {
        AppData.loadAll();
        new WelcomeScreen();
    }
}
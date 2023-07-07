import com.iLibrary.views.UILauncher;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            UILauncher launcher = new UILauncher();
            launcher.setVisible(true);
        });
    }
}

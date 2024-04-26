import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PFrame extends JFrame {

    public PPanel panel;

    public PFrame() {

        setTitle("Páv App ©Eduard Štich 2024");
        try
        {
            BufferedImage image = ImageIO.read(Main.class.getResource("icon.png"));

            setIconImage(image);
        } catch (Exception e) {
            System.out.println(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        panel = new PPanel();
        add(panel);

        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);
    }
}

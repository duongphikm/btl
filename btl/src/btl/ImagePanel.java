package btl;
import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
	public static final long serialVersionUID = 1L;
	
	private Image img;

	public ImagePanel() {

		Dimension size = new Dimension(400, 400);

		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		setLayout(null);

		setBackground(new Color(0, 0, 255));
	}

	public void setImage(Image image) {
		img = image;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, null);
		}
	}
}

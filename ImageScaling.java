package ui.bag;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageScaling {

	public static Image zoom(String srcPath, double d) {

		BufferedImage src;
		Image image = null;
		try {
			src = ImageIO.read(new File(srcPath));
			int width = src.getWidth(); // Դͼ��
			int height = src.getHeight(); // Դͼ��

			// ��ȡһ��������ԭ��scale��ͼ��ʵ��
			image = src.getScaledInstance((int) (width * d),
					(int) (height * d), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
}

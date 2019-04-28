import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.Serializable;

public class BufferedImageLoader implements Serializable {

    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException{
        image = ImageIO.read(new File(path));
        return image;
    }


}

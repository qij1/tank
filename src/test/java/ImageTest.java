import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

public class ImageTest {

    @Test
    public void test() {

        try {
            BufferedImage image = ImageIO.read(new File("C:\\Users\\12415\\Desktop\\MCA\\设计模式\\马士兵老师_坦克大战_java基础(一期)\\tank\\src\\images\\bulletD.gif"));
            assertNotNull(image);
            BufferedImage image1 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
            assertNotNull(image1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

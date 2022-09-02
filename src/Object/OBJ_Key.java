package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject {
    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResource("/ObjectImages/key.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


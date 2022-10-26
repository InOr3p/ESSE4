package mat.unical.it.progettouid2022.model;

import javafx.scene.image.Image;

import java.io.File;

public class ImmagineCorrente {
    private Image img;
    private File file;

    private static ImmagineCorrente instance = new ImmagineCorrente();
    private ImmagineCorrente() {}

    public static ImmagineCorrente getInstance() {
        return instance;
    }

    public Image getImg() {
        return img;
    }

    public File getFile() {
        return file;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setFile(File file) {
        this.file = file;
    }
}

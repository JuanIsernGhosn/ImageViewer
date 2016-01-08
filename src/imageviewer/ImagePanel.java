package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Graphics;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ImageDisplay{
    
    private Image image;

    public ImagePanel(Image image){
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g){
        g.drawImage((java.awt.Image)image.bitmap(), 0, 0, this);
    }    

    @Override
    public Image image(){
        return image;
    }

    @Override
    public void show(Image image){
        this.image = image;
        this.repaint();
    }
    
}

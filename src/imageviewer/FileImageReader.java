package imageviewer;

import imageviewer.model.Image;
import imageviewer.view.ImageReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FileImageReader implements ImageReader {
    private final File[] files;
    private static final String[] ImageExtensions = {".jpg",".png",".algo"}; 
    
    public FileImageReader(String path){
        this(new File(path));
    }
    
    public FileImageReader(File folder){
        this.files = folder.listFiles(withImageExtension());
    }
    
    @Override
    public Image read(){
        return ImageAt(0);
    }
    
    private Image ImageAt(final int index){
        return new Image() {
            @Override
            public Object bitmap(){
                try {
                    return ImageIO.read(files[index]);
                } catch (IOException ex) {
                    return null;
                }
            }

            @Override
            public Image prev() {
                return ImageAt(index > 0 ? index - 1 : files.length - 1);
            }

            @Override
            public Image next(){
              return ImageAt(index < files.length - 1 ?  index +1 : 0);
            }
        };
    }
   
    private FilenameFilter withImageExtension() {
        return new FilenameFilter() {
        @Override    
        public boolean accept(File dir, String name){
            for (String extensions : ImageExtensions){
                if(name.endsWith(extensions)) return true;
            }
            return false;
        }};
    }
}

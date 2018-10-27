package assets;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.EnumMap;

public class ImageLoader {
	//a class to locate the bgm assets
	private static EnumMap<ImageName,String> paths;
	
	static {
		paths = new EnumMap<ImageName,String>(ImageName.class); 
		
		paths.put(ImageName.BOX_V1,"Img/box1.1.png");
		paths.put(ImageName.BOX_V2,"Img/box1.2.png");
		paths.put(ImageName.BOX_V3,"Img/box1.3.png");
		paths.put(ImageName.BOX_V4,"Img/box1.4.png");
	}
	
	public static Image loadImage(Toolkit tk,ImageName name) {
		try {
			Image image = tk.getImage(paths.get(name));
			return image;
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.err.println(name.toString()+" is not refering a valid asset!");
		}catch(SecurityException e) {
			e.printStackTrace();
			System.err.println(name.toString()+" cannot be loaded!");
		}
		System.exit(-1);
		return null;//just to please the IDE
	}
}
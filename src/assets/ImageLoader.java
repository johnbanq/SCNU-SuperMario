package assets;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.EnumMap;

public class ImageLoader {
	//a class to locate the bgm assets
	private static EnumMap<ImageName,String[]> paths;
	static {
		paths = new EnumMap<ImageName,String[]>(ImageName.class); 
		
		paths.put(ImageName.BOX,new String[]{
				"Img/box1.png",
				"Img/box2.png",
				"Img/box3.png",
				"Img/box4.png"
				}
		);
		
		paths.put(ImageName.BRICK,new String[]{
				"Img/brick1.png",
				"Img/brick2.png"
				}
		);
		
		paths.put(ImageName.FLOWER,new String[]{
				"Img/flower1.1.png",
				"Img/flower1.2.png",
				"Img/flower1.3.png",
				"Img/flower1.4.png",
				"Img/flower1.5.png",
				"Img/flower1.6.png",
				}
		);

		paths.put(ImageName.FUNGUS,new String[]{
				"Img/fungus1.png",
				"Img/fungus2.png",
				"Img/fungus3.png"
				}
		);
		
		paths.put(ImageName.CHARACTER_PHOTO,new String[]{
				"Img/ùŒ◊”.png"
				}
		);
		paths.put(ImageName.ICON,new String[]{
				"Img/icon.png"
				}
		);
		
	}
	
	public static Image[] loadImage(ImageName name) {
		return loadImage(Toolkit.getDefaultToolkit(),name);
	}
	
	public static Image[] loadImage(Toolkit tk,ImageName name) {
		try {
			String[] img_names = paths.get(name);
			Image[] imgs = new Image[img_names.length];
			for(int i=0;i<img_names.length;i++){
				imgs[i] = tk.getImage(img_names[i]);
			}
			return imgs;
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
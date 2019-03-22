package br.furb.furbot.suporte;

import java.net.URL;
import javax.swing.ImageIcon;







public class LoadImage
{
  private static LoadImage loadImage;
  
  private LoadImage() {}
  
  public static LoadImage getInstance()
  {
    if (loadImage == null)
      loadImage = new LoadImage();
    return loadImage;
  }
  
  public ImageIcon getIcon(String iconName)
  {
    ClassLoader classLoader = getClass().getClassLoader();
    ImageIcon icon = null;
    URL res = classLoader.getResource(iconName);
    if (res != null) {
      icon = new ImageIcon(res);
    } else
      icon = new ImageIcon(iconName);
    return icon;
  }
}

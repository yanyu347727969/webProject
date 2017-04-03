package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtil {
	/**
     * 压缩图片方法一(高质量)
     * @param oldFile 将要压缩的图片
     * @param width 压缩宽
     * @param height 压缩高
     * @param smallIcon 压缩图片后,添加的扩展名(在图片后缀名前添加)
     * @param quality 压缩质量 范围：<i>0.0-1.0</i> 高质量:<i>0.75</i> 中等质量:<i>0.5</i> 低质量:<i>0.25</i>
     * @param percentage 是否等比压缩 若true宽高比率将将自动调整
     * @param savedFile 压缩后的文件
     */
	public static File compressImage(File file, int width, int height,
            float quality, boolean percentage,File savedFile) {
        try {
            //File file = new File(oldFile);
        	String path = savedFile.getAbsolutePath();
        	File dir = new File(path.substring(0, path.lastIndexOf("\\")));
        	if(!dir.exists()){//必须先创建文件夹
        		dir.mkdir();
        	}
            // 验证文件是否存在
            if(!file.exists())
                throw new FileNotFoundException("找不到原图片!");
             
            // 获取图片信息
            BufferedImage image = ImageIO.read(file);
            int orginalWidth = image.getWidth();
            int orginalHeight = image.getHeight();
             
            // 验证压缩图片信息
            if (width <= 0 || height <= 0 || !Pattern.matches("^[1-9]\\d*$", String.valueOf(width))
                    || !Pattern.matches("^[1-9]\\d*$", String.valueOf(height)))
                throw new Exception("图片压缩后的高宽有误!");
             
            // 等比压缩
            if (percentage) {
                double rate1 = ((double) orginalWidth) / (double) width + 0.1;
                double rate2 = ((double) orginalHeight) / (double) height + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                width = (int) (((double) orginalWidth) / rate);
                height = (int) (((double) orginalHeight) / rate);
            }
             
            // 创建原图像的缩放版本
            Image image2 = image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
 
            // 创建数据缓冲区图像
            BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
            // 创建一个Graphics2D
            Graphics2D g2 = bufImage.createGraphics();
 
            // 重绘图像
            g2.drawImage(image2, 0, 0, width, height, null);
            g2.dispose();
             
            // 过滤像素矩阵
            float[] kernelData = { 
                    -0.125f, -0.125f, -0.125f, 
                    -0.125f, 2, -0.125f, -0.125f, 
                    -0.125f, -0.125f };
            Kernel kernel = new Kernel(3, 3, kernelData);
             
            // 按核数学源图像边缘的像素复制为目标中相应的像素输出像素
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
 
            // 转换像素
            bufImage = cOp.filter(bufImage, null);
 
            FileOutputStream out = new FileOutputStream(savedFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage);
 
            // 设置压缩质量
            param.setQuality(quality, true);
            encoder.encode(bufImage, param);
 
            out.close();
            //System.out.println(newImage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("压缩失败!" + e.getMessage());
        }
		return savedFile;
    }
	
	/**
	 * 
	 * @param originalFile原始的文件
	 * @param resizedFile
	 * @param newWidth
	 * @param quality
	 * @throws IOException
	 */
	public static void resize(File originalFile, File resizedFile,  
            int newWidth, int newHeight, float quality) throws IOException {  
  
		String path = resizedFile.getAbsolutePath();
    	File dir = new File(path.substring(0, path.lastIndexOf("\\")));
    	if(!dir.exists()){//必须先创建文件夹
    		dir.mkdir();
    	}
        if (quality > 1) {  
            throw new IllegalArgumentException(  
                    "Quality has to be between 0 and 1");  
        }  
  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        /*int iWidth = i.getWidth(null);  //这块代码是按比例压缩
        int iHeight = i.getHeight(null);  
  
        if (iWidth > iHeight) {  
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)  
                    / iWidth, Image.SCALE_SMOOTH);  
        } else {  
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                    newWidth, Image.SCALE_SMOOTH);  
        } */ 
        resizedImage = i.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //直接设置图片压缩的宽和高
  
        // This code ensures that all the pixels in the image are loaded.  
        Image temp = new ImageIcon(resizedImage).getImage();  
  
        // Create the buffered image.  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);  
  
        // Copy image to buffered image.  
        Graphics g = bufferedImage.createGraphics();  
  
        // Clear background and paint the image.  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
  
        // Soften.  
        float softenFactor = 0.05f;  
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);  
        bufferedImage = cOp.filter(bufferedImage, null);  
  
        // Write the jpeg to a file.  
        FileOutputStream out = new FileOutputStream(resizedFile);  
  
        // Encodes image as a JPEG data stream  
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
  
        JPEGEncodeParam param = encoder  
                .getDefaultJPEGEncodeParam(bufferedImage);  
  
        param.setQuality(quality, true);  
  
        encoder.setJPEGEncodeParam(param);  
        encoder.encode(bufferedImage);  
    } // Example usage 

}

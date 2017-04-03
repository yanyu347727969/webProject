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
     * ѹ��ͼƬ����һ(������)
     * @param oldFile ��Ҫѹ����ͼƬ
     * @param width ѹ����
     * @param height ѹ����
     * @param smallIcon ѹ��ͼƬ��,��ӵ���չ��(��ͼƬ��׺��ǰ���)
     * @param quality ѹ������ ��Χ��<i>0.0-1.0</i> ������:<i>0.75</i> �е�����:<i>0.5</i> ������:<i>0.25</i>
     * @param percentage �Ƿ�ȱ�ѹ�� ��true��߱��ʽ����Զ�����
     * @param savedFile ѹ������ļ�
     */
	public static File compressImage(File file, int width, int height,
            float quality, boolean percentage,File savedFile) {
        try {
            //File file = new File(oldFile);
        	String path = savedFile.getAbsolutePath();
        	File dir = new File(path.substring(0, path.lastIndexOf("\\")));
        	if(!dir.exists()){//�����ȴ����ļ���
        		dir.mkdir();
        	}
            // ��֤�ļ��Ƿ����
            if(!file.exists())
                throw new FileNotFoundException("�Ҳ���ԭͼƬ!");
             
            // ��ȡͼƬ��Ϣ
            BufferedImage image = ImageIO.read(file);
            int orginalWidth = image.getWidth();
            int orginalHeight = image.getHeight();
             
            // ��֤ѹ��ͼƬ��Ϣ
            if (width <= 0 || height <= 0 || !Pattern.matches("^[1-9]\\d*$", String.valueOf(width))
                    || !Pattern.matches("^[1-9]\\d*$", String.valueOf(height)))
                throw new Exception("ͼƬѹ����ĸ߿�����!");
             
            // �ȱ�ѹ��
            if (percentage) {
                double rate1 = ((double) orginalWidth) / (double) width + 0.1;
                double rate2 = ((double) orginalHeight) / (double) height + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                width = (int) (((double) orginalWidth) / rate);
                height = (int) (((double) orginalHeight) / rate);
            }
             
            // ����ԭͼ������Ű汾
            Image image2 = image.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
 
            // �������ݻ�����ͼ��
            BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
 
            // ����һ��Graphics2D
            Graphics2D g2 = bufImage.createGraphics();
 
            // �ػ�ͼ��
            g2.drawImage(image2, 0, 0, width, height, null);
            g2.dispose();
             
            // �������ؾ���
            float[] kernelData = { 
                    -0.125f, -0.125f, -0.125f, 
                    -0.125f, 2, -0.125f, -0.125f, 
                    -0.125f, -0.125f };
            Kernel kernel = new Kernel(3, 3, kernelData);
             
            // ������ѧԴͼ���Ե�����ظ���ΪĿ������Ӧ�������������
            ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
 
            // ת������
            bufImage = cOp.filter(bufImage, null);
 
            FileOutputStream out = new FileOutputStream(savedFile);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufImage);
 
            // ����ѹ������
            param.setQuality(quality, true);
            encoder.encode(bufImage, param);
 
            out.close();
            //System.out.println(newImage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ѹ��ʧ��!" + e.getMessage());
        }
		return savedFile;
    }
	
	/**
	 * 
	 * @param originalFileԭʼ���ļ�
	 * @param resizedFile
	 * @param newWidth
	 * @param quality
	 * @throws IOException
	 */
	public static void resize(File originalFile, File resizedFile,  
            int newWidth, int newHeight, float quality) throws IOException {  
  
		String path = resizedFile.getAbsolutePath();
    	File dir = new File(path.substring(0, path.lastIndexOf("\\")));
    	if(!dir.exists()){//�����ȴ����ļ���
    		dir.mkdir();
    	}
        if (quality > 1) {  
            throw new IllegalArgumentException(  
                    "Quality has to be between 0 and 1");  
        }  
  
        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        /*int iWidth = i.getWidth(null);  //�������ǰ�����ѹ��
        int iHeight = i.getHeight(null);  
  
        if (iWidth > iHeight) {  
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)  
                    / iWidth, Image.SCALE_SMOOTH);  
        } else {  
            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,  
                    newWidth, Image.SCALE_SMOOTH);  
        } */ 
        resizedImage = i.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH); //ֱ������ͼƬѹ���Ŀ�͸�
  
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

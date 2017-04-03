package test;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

import org.junit.Test;

import sun.awt.image.ToolkitImage;
import util.ImageUtil;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class TestImageZoom {
	/**
     * ѹ��ͼƬ����һ(������)
     * @param oldFile ��Ҫѹ����ͼƬ
     * @param width ѹ����
     * @param height ѹ����
     * @param smallIcon ѹ��ͼƬ��,��ӵ���չ��(��ͼƬ��׺��ǰ���)
     * @param quality ѹ������ ��Χ��<i>0.0-1.0</i> ������:<i>0.75</i> �е�����:<i>0.5</i> ������:<i>0.25</i>
     * @param percentage �Ƿ�ȱ�ѹ�� ��true��߱��ʽ����Զ�����
     */
	
	@Test
	public void test() throws Exception{
		BufferedImage image = ImageIO.read(new FileInputStream("C:/Users/Administrator/Desktop/th.jpg"));
		byte[] compressImage = cropAndCompressImage(0,0,image.getWidth(),image.getHeight(),image,image.getWidth()/2,image.getHeight()/2);
		byte2image(compressImage,"C:/Users/Administrator/Desktop/hh.jpg");
	}
	public void byte2image(byte[] data,String path){
	    if(data.length<3||path.equals("")) return;
	    try{
	    FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
	    imageOutput.write(data, 0, data.length);
	    imageOutput.close();
	    System.out.println("Make Picture success,Please find image in " + path);
	    } catch(Exception ex) {
	      System.out.println("Exception: " + ex);
	      ex.printStackTrace();
	    }
	  }
	public byte[] cropAndCompressImage(int x, int y, int w, int h,
			BufferedImage image, int targetW, int targetH) throws Exception {
		if (w == -1 && h == -1 && image != null) {
			// use full image
			w = image.getWidth();
			h = image.getHeight();
		}
		if (x < 0 || y < 0 || w <= 0 || h <= 0 || image == null) {
			// error
		}
		w = Math.min(x + w, image.getWidth()) - x;
		h = Math.min(y + h, image.getHeight()) - y;
		try {
			BufferedImage cropedImage = image.getSubimage(x, y, w, h);

			BufferedImage compressedImage = null;
            if (image.isAlphaPremultiplied()) {
                compressedImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TRANSLUCENT);
            } else {
                compressedImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
            }
			Graphics2D graph = compressedImage.createGraphics();
			graph.drawImage(cropedImage.getScaledInstance(targetW,targetH,BufferedImage.SCALE_SMOOTH), 0, 0,Color.white,null);
			graph.dispose();
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ImageIO.write(compressedImage, "jpg", bytes);

			return bytes.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static File compressImage(File file, int width, int height, String smallIcon,
            float quality, boolean percentage,File savedFile) {
        try {
            //File file = new File(oldFile);
             
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
             
            // ѹ������ļ���
            //String filePrex = oldFile.substring(0, oldFile.lastIndexOf('.'));
            //String newImage = filePrex + smallIcon + oldFile.substring(filePrex.length());
 
            // ѹ���ļ����λ��
            //File savedFile = new File("newImage");
 
            // ����һ���µ��ļ�
            //savedFile.createNewFile();
 
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

}

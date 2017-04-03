package test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import util.ImageUtil;

public class TestImageResize {
	
	@Test
	public void test(){
		File originalFile = new File("C:/Users/Administrator/Desktop/th.jpg");
		File resizedFile = new File("C:/Users/Administrator/Desktop/ppp.jpg");
		try {
			ImageUtil.resize(originalFile, resizedFile, 350, 350, 1f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

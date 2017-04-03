package test;

public class Test {
	private static final String PATH = "G:/apache-tomcat-6.0.36/webapps/ROOT/onload/";
	@org.junit.Test
	public void test(){
		System.out.println(System.getProperty("user.dir"));
		String[] strings = System.getProperty("user.dir").split("\\\\");
		for (String string : strings) {
			System.out.println(string);
		}
	//	String path = strings[0]+"/"+strings[1]+PATH;
		//System.out.println(path);
	}

}

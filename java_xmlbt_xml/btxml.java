package java_xmlbt_xml;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class btxml {
	
	    public static void main(String[] args) {
	    	System.out.println("0000");
	    	generateDirectoryTreeXML();
		}
	    public static void generateDirectoryTreeXML() {
	        // Đoạn mã tạo cây thư mục XML
	        // Đoạn mã nhập đường dẫn thư mục từ người dùng
	    	Scanner sc = new Scanner(System.in);
	        String directoryPath = sc.nextLine();
	        
	        // Tạo đối tượng File từ đường dẫn thư mục
	        File directory = new File(directoryPath);
	        
	        // Kiểm tra xem đường dẫn có tồn tại không
	        if (!directory.exists()) {
	            System.out.println("Đường dẫn thư mục không tồn tại.");
	            return;
	        }
	        
	        // Kiểm tra xem đối tượng có phải là thư mục không
	        if (!directory.isDirectory()) {
	            System.out.println("Đường dẫn không phải là một thư mục.");
	            return;
	        }
	        
	        // Liệt kê cây thư mục dưới dạng XML
	        String xml = generateXML(directory);
	        
	        // In ra XML
	        System.out.println(xml);
	        
	        // Lưu XML vào tệp
	        saveXMLToFile(xml, "directory_tree.xml");
	    }
	    
	    
	    
	    private static String generateXML(File directory) {
	        StringBuilder xmlBuilder = new StringBuilder();
	        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	        xmlBuilder.append("<directory_tree>\n");
	        generateXMLForDirectory(directory, xmlBuilder, 1);
	        xmlBuilder.append("</directory_tree>");
	        return xmlBuilder.toString();
	    }
	    
	    private static void generateXMLForDirectory(File directory, StringBuilder xmlBuilder, int depth) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                // Bắt đầu một thẻ mới cho mỗi thư mục hoặc tệp tin
	                xmlBuilder.append(getIndent(depth));
	                xmlBuilder.append("<");
	                xmlBuilder.append(file.isDirectory() ? "directory" : "file");
	                xmlBuilder.append(" name=\"" + file.getName() + "\">");
	                
	                if (file.isDirectory()) {
	                    // Nếu là thư mục, tiếp tục đệ quy
	                    generateXMLForDirectory(file, xmlBuilder, depth + 1);
	                }
	                
	                // Đóng thẻ
	                xmlBuilder.append("</");
	                xmlBuilder.append(file.isDirectory() ? "directory" : "file");
	                xmlBuilder.append(">\n");
	            }
	        }
	    }
	    
	    private static String getIndent(int depth) {
	        StringBuilder indentBuilder = new StringBuilder();
	        for (int i = 0; i < depth; i++) {
	            indentBuilder.append("  "); // Sử dụng khoảng trắng làm khoảng cách
	        }
	        return indentBuilder.toString();
	    }
	    
	    private static void saveXMLToFile(String xml, String filename) {
	        try (FileWriter writer = new FileWriter(filename)) {
	            writer.write(xml);
	            System.out.println("Lưu cây thư mục thành công vào " + filename);
	        } catch (IOException e) {
	            System.out.println("Đã xảy ra lỗi khi lưu tệp.");
	            e.printStackTrace();
	        }
	    }
	}





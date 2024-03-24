package java_xmlbt_xml;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class bt2xml {



	    public static void main(String[] args) {
	        try {
	            // Tạo một đối tượng DocumentBuilderFactory
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	            // Tạo một tài liệu mới
	            Document doc = docBuilder.newDocument();

	            // Tạo nút gốc <students>
	            Element rootElement = doc.createElement("students");
	            doc.appendChild(rootElement);

	            // Tạo các nút sinh viên và thêm chúng vào nút gốc
	            Element student1 = createStudent(doc, "John", 20, 3.5);
	            rootElement.appendChild(student1);

	            Element student2 = createStudent(doc, "Alice", 21, 3.8);
	            rootElement.appendChild(student2);

	            // Ghi nội dung vào tệp XML
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
	            Transformer transformer = transformerFactory.newTransformer();
	            DOMSource source = new DOMSource(doc);
	            StreamResult result = new StreamResult(new FileOutputStream(new File("students.xml")));
	            transformer.transform(source, result);

	            System.out.println("Tạo tệp XML thành công.");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Phương thức tạo nút sinh viên
	    private static Element createStudent(Document doc, String name, int age, double gpa) {
	        // Tạo nút sinh viên
	        Element student = doc.createElement("student");

	        // Tạo nút con tên và thiết lập giá trị của nó
	        Element nameElement = doc.createElement("name");
	        nameElement.appendChild(doc.createTextNode(name));
	        student.appendChild(nameElement);

	        // Tạo nút con tuổi và thiết lập giá trị của nó
	        Element ageElement = doc.createElement("age");
	        ageElement.appendChild(doc.createTextNode(String.valueOf(age)));
	        student.appendChild(ageElement);

	        // Tạo nút con GPA và thiết lập giá trị của nó
	        Element gpaElement = doc.createElement("gpa");
	        gpaElement.appendChild(doc.createTextNode(String.valueOf(gpa)));
	        student.appendChild(gpaElement);

	        return student;
	    }
	}



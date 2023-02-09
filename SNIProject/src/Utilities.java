import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static void  readFromExcel() {

		try {
			// create an object of FileInputStream class to read excel file
			FileInputStream fis = new FileInputStream("C:\\Eclipse\\SNIProject\\SNI-DATA.xlsx");
			// creating workbook instance that refers to .xls file
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// creating a Sheet object
			XSSFSheet sheet = workbook.getSheet("LogIn");
			// get all rows in the sheet
			Iterator<Row> rows = sheet.rowIterator();
			XSSFRow row;
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				XSSFCell cell = row.getCell(0);
				System.out.println("The cell is " + cell);
					String email = row.getCell(0).getStringCellValue();
					String pwd = row.getCell(1).getStringCellValue();
					System.out.println("The email is is " + email);
					System.out.println("The pwd is is " + pwd);
			}
		} catch (Exception e) {
		}

	}
	
	public List readLogInData() {
		List<String> loginData = new ArrayList<String>();
		
		return loginData;
	}
}

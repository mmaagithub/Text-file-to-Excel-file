package changeExeToExcel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Test1 {

	public static void change(File file,String newPath) throws IOException{
		HSSFWorkbook wb = new HSSFWorkbook();// 建立新HSSFWorkbook对象
		HSSFSheet sheet = wb.createSheet("new sheet");// 建立新的sheet对象
		// Create a row and put some cells in it. Rows are 0 based.
		HSSFRow row = sheet.createRow((short) 0);// 建立新行
		HSSFCell cell = row.createCell((short) 0);
		FileReader fin = new FileReader(file);
		FileOutputStream fos =new FileOutputStream(newPath);
		int i, rownum = 0, charnum = 0;
		String str="";
		try {
			while ((i = fin.read()) != -1) {
				str=str+String.valueOf((char)i);
				if (str.endsWith(",")||str.endsWith("，")) {  
					charnum++;
					cell = row.createCell((short)charnum );
					str="";
				} else if (str.endsWith("\n")) { 
					rownum++;
					row = sheet.createRow((short) rownum);// 建立新行
					charnum=0;
					cell = row.createCell((short)charnum );
					str="";
				}
				cell.setCellValue(str);
			}
			wb.write(fos);
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			fos.close();
			fin.close();
		}
	}
}

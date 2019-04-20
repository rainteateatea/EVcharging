import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;



import jxl.Sheet;
import jxl.Workbook;


public class Readinput2 {
	
	public static ArrayList<Input2> readexcel2(File file) {
		ArrayList<Input2> input2list = new ArrayList<Input2>();
		try {
			
			InputStream isInputStream = new FileInputStream(file.getAbsolutePath());
			
			Workbook wb = Workbook.getWorkbook(isInputStream);
			
			Sheet sheet = wb.getSheet(1);
		//	ArrayList<Things> thingsList = new ArrayList<Things>();
		//	ArrayList<Input2> input2list = new ArrayList<Input2>();
			String col1 ="";
			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					if (j==0) {
						col1 = sheet.getCell(j, i).getContents();
					}
					if (j==1) {
						String temp = sheet.getCell(j, i).getContents();
						int col2 = Integer.parseInt(temp);
						Input2 input2 = new Input2(col1, col2);
						input2list.add(input2);
					}
		
					
				}
			}
			for (int i = 0; i < input2list.size(); i++) {
				for (int j = 1; j < input2list.size()-i; j++) {
					if (input2list.get(j-1).getType()<input2list.get(j).getType()) {
						Input2 I = input2list.get(j-1);
						input2list.set(j-1, input2list.get(j));
						input2list.set(j, I);
						
					}
				}
				
			}
			for (int i = 0; i < input2list.size(); i++) {
				System.out.println(input2list.get(i).getCpid()+"   "+input2list.get(i).getType());
			}
		//	return input2list;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return input2list;
		
	}

}

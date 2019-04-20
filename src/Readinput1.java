import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class Readinput1 {
	public static ArrayList<Input1> readexcel1(File file) {
//		public static ArrayList<Input2> readexcel2(File file)
	//ArrayList<Input2> input2list = new ArrayList<Input2>();
		ArrayList<Input1> input1list = new ArrayList<Input1>();
		try {
			InputStream isInputStream = new FileInputStream(file.getAbsolutePath());
			
			Workbook wb = Workbook.getWorkbook(isInputStream);
			
			Sheet sheet = wb.getSheet(0);
			
			String customerid="";
			int pstart=0;
			int pfinish=0;
			int mile=0;
			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < sheet.getColumns(); j++) {
					if (j==0) {
						customerid = sheet.getCell(j,i).getContents();

					}
					else if (j==1) {
						String hour = sheet.getCell(j,i).getContents();
						String[] split = hour.split(":");
						pstart = Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
					}
					else if (j==2) {
						String hour = sheet.getCell(j,i).getContents();
						String[] split = hour.split(":");
						pfinish = Integer.parseInt(split[0])*60+Integer.parseInt(split[1]);
					}
					else if (j==3) {
						mile = Integer.parseInt(sheet.getCell(j, i).getContents());
					}
					else{
						String EVtype = sheet.getCell(j, i).getContents();
						Input1 input1 = new Input1(customerid, pstart, pfinish, mile, EVtype);
						input1list.add(input1);
					} 
					
				}
			}
			
			for(int i=0;i<input1list.size();i++){
				System.out.println(input1list.get(i).getCid()+"  "+input1list.get(i).getPst()+
						" "+input1list.get(i).getPft()+"  "+
						input1list.get(i).getMile()+"  "+input1list.get(i).getCartype());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (BiffException e) {
			e.printStackTrace();
			// TODO: handle exception
		}catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return input1list;
		
	}


}

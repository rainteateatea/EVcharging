
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;











import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;


import jxl.Workbook;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class Writefile {
	public static void writefile(ArrayList<Car> cars, ArrayList<Input2> input2) throws ParseException {
		
		//create Gantt chart
		IntervalCategoryDataset dataset = createSampleDataset(cars,input2);
		JFreeChart chart = ChartFactory.createGanttChart("EV Charging reservation system", "charging point id",
				"charging vehicle", dataset,false,false,false);
		
		CategoryPlot plot = chart.getCategoryPlot();
		
		chart.getTitle().setFont(new Font("新宋体",Font.BOLD,20));
		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLabelFont(new Font("新宋体",Font.BOLD,14));
		domainAxis.setTickLabelFont(new Font("新宋体",Font.BOLD,12));
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("新宋体",Font.BOLD,16));
		DateAxis da = (DateAxis)plot.getRangeAxis(0);
		chart.getCategoryPlot().setRenderer(new MyRenderer(dataset));
		da.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
		
		FileOutputStream fop = null;
		try {
			System.out.println("danny>> begin");
			fop = new FileOutputStream("E:gantt.jpg");
			ChartUtilities.writeChartAsJPEG(fop, chart, 800, 600,null);
			System.out.println("danny>> end");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			try {
				fop.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		
	
		
		try {
			//write excel
			WritableWorkbook book = Workbook.createWorkbook(new File("output.xls"));
			WritableSheet sheet = book.createSheet("output", 0);
			Label label1 = new jxl.write.Label(0, 0, "Customer Id");
			sheet.addCell(label1);
			jxl.write.Label label2 = new jxl.write.Label(1, 0, "EV TYPE");
			sheet.addCell(label2);
			jxl.write.Label label3 = new jxl.write.Label(2, 0, "Start Charging time");
			sheet.addCell(label3);
			jxl.write.Label label4 = new jxl.write.Label(3, 0, "Charging end time");
			sheet.addCell(label4);
			jxl.write.Label label5 = new jxl.write.Label(4, 0, "charging duration");
			sheet.addCell(label5);
			jxl.write.Label label6 = new jxl.write.Label(5, 0, "charging point");
			sheet.addCell(label6);
			
			
		//	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			DateFormat df = new DateFormat("HH:mm");
		
			int i=0;//为了数组 0-n
			int j=1;//为了excel 表格 1 raw -n
			int realstarttime=0;
			int realfinishtime=0;
			for(Car car: cars){
				//int j=0;
				int m=0;// each loop for one charing point
			//	System.out.println("第"+i+"个charging point can charge：");
				for(Things t : car.getThingsList()){
					//customer id string
					Label Customerid = new Label(0,j,t.getName());
					sheet.addCell(Customerid);
					//EV type string
					Label EVtype = new Label(1, j, t.getEVtype());
					sheet.addCell(EVtype);
					
					//start time and finish time
					if (m==0) {
						//start time
						realstarttime = t.getStartTime();
						String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
						Date starttimeDate = sdf.parse(st);
						
						DateTime dstartTime = new DateTime(2,j,starttimeDate,new WritableCellFormat(df));
						sheet.addCell(dstartTime);
						//finish time
						realfinishtime = t.getStartTime()+t.getCost();
						String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
						Date finishtimeDate = sdf.parse(ft);
						
						DateTime dfinishtime = new DateTime(3,j,finishtimeDate,new WritableCellFormat(df));
						sheet.addCell(dfinishtime);
						
					}
					else {
						//m!=0
						//two types schedule
						if (realfinishtime<=t.getStartTime()) {
							//start time
							realstarttime = t.getStartTime();
							String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
							Date starttimeDate = sdf.parse(st);
							
							DateTime dstartTime = new DateTime(2,j,starttimeDate,new WritableCellFormat(df));
							sheet.addCell(dstartTime);
							//finish time
							realfinishtime = realstarttime+t.getCost();
							String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
							Date finishtimeDate = sdf.parse(ft);
							
							DateTime dfinishtime = new DateTime(3,j,finishtimeDate,new WritableCellFormat(df));
							sheet.addCell(dfinishtime);
						}
						else {
							//start time
							realstarttime = realfinishtime;
							String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
							Date starttimeDate = sdf.parse(st);
							
							DateTime dstartTime = new DateTime(2,j,starttimeDate,new WritableCellFormat(df));
							sheet.addCell(dstartTime);
							//finish time
							realfinishtime = realstarttime+t.getCost();
							String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
							Date finishtimeDate = sdf.parse(ft);
							
							DateTime dfinishtime = new DateTime(3,j,finishtimeDate,new WritableCellFormat(df));
							sheet.addCell(dfinishtime);
						}
						
						
					}
					
					
					//charging duration cost int
					Number cost = new Number(4, j, t.getCost());
					sheet.addCell(cost);
					
					//charging point id string
					Label cp = new Label(5, j, input2.get(i).getCpid());
					sheet.addCell(cp);
					
					
				
					j++;
					m++;
				}
			//	System.out.println(j+"辆车");
			//	System.out.println("\n");
			i++;
			}
			
		//	System.out.println(input2.get(0).getType());
			
			
			
			book.write();
			book.close();
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
	}
	private static IntervalCategoryDataset createSampleDataset(ArrayList<Car> cars, ArrayList<Input2> input2) throws ParseException {
		
		final TaskSeries s1 = new TaskSeries("SCHEDULE");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		int i=0;//为了数组 0-n
	//	int j=1;//为了excel 表格 1 raw -n
		int realstarttime=0;
		int realfinishtime=0;
		
		for (Car car : cars) {
			int m=0;// each loop for one charing point
			//final Task t3 = new Task("任务2", date(5, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001));
			Date startrange = sdf.parse("00:00");
			Date finishrange = sdf.parse("24:59");
			final Task task = new Task(input2.get(i).getCpid(), startrange, finishrange);
		//	final Task charingpoint = new 
			for (Things t : car.getThingsList()) {
				if (m==0) {
					
					//start time
					realstarttime = t.getStartTime();
					String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
					Date starttimeDate = sdf.parse(st);
					
					//finish time
					realfinishtime = t.getStartTime()+t.getCost();
					String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
					Date finishtimeDate = sdf.parse(ft);
					
					//final Task st31 = new Task("需求1", date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
			        //st31.setPercentComplete(0.50);
			        //t3.addSubtask(st31);
					final Task subtask = new Task(t.getName(), starttimeDate, finishtimeDate);
				//	subtask.setPercentComplete(1.00);
					task.addSubtask(subtask);
					
				}
				
				else {
					//m!=0
					//two types schedule
					if (realfinishtime<=t.getStartTime()) {
						//start time
						realstarttime = t.getStartTime();
						String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
						Date starttimeDate = sdf.parse(st);
						
						//finish time
						realfinishtime = realstarttime+t.getCost();
						String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
						Date finishtimeDate = sdf.parse(ft);
						
						final Task subtask = new Task(t.getName(), starttimeDate, finishtimeDate);
					//	subtask.setPercentComplete(1.00);
						task.addSubtask(subtask);
					}
					else {
						//start time
						realstarttime = realfinishtime;
						String st = Integer.toString(realstarttime/60)+":"+Integer.toString(realstarttime%60);
						Date starttimeDate = sdf.parse(st);
						
						//finish time
						realfinishtime = realstarttime+t.getCost();
						String ft = Integer.toString(realfinishtime/60)+":"+Integer.toString(realfinishtime%60);
						Date finishtimeDate = sdf.parse(ft);
						
						final Task subtask = new Task(t.getName(), starttimeDate, finishtimeDate);
					//	subtask.setPercentComplete(1.00);
						task.addSubtask(subtask);
					}
					
				}
				
			//	j++;
				m++;
			}
			//s1.add(t3);
			s1.add(task);
			i++;
		}
		 final TaskSeriesCollection collection = new TaskSeriesCollection();
	        collection.add(s1);
	        return collection;
		
		//return null;
	}
	private static class MyRenderer extends GanttRenderer {
		 private static final int PASS = 2; // assumes two passes
		    private final List<Color> clut = new ArrayList<Color>();
		    private final IntervalCategoryDataset model;
		    private int row;
		    private int col;
		    private int index;

		    public MyRenderer(IntervalCategoryDataset model) {
		        this.model = model;
		    }

		    @Override
		    public Paint getItemPaint(int row, int col) {
		        if (clut.isEmpty() || this.row != row || this.col != col) {
		            initClut(row, col);
		            this.row = row;
		            this.col = col;
		            index = 0;
		        }
		        int clutIndex = index++/PASS;
		        //System.out.println(clut.get(clutIndex));
		        return clut.get(clutIndex);
		    
		    }
		    private void initClut(int row, int col) {
		        clut.clear();
		        Color c = (Color) super.getItemPaint(row, col);
		        float[] a = new float[3];
		        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), a);
		        TaskSeriesCollection series = (TaskSeriesCollection) model;
		        TaskSeries series2 =  (TaskSeries)series.getRowKeys().get(row);
		        List<Task> tasks = series2.getTasks();// unchecked
		        int taskCount = tasks.get(col).getSubtaskCount();
		        taskCount = Math.max(1, taskCount);
		        for (int i = 0; i < taskCount; i++) {
		            clut.add(Color.getHSBColor(a[0], a[1] / i, a[2]));
		        }
		    }
		}
		
	

}

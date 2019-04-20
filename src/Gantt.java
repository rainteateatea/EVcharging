import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class Gantt{
	
	/**
	 * 
	 */
//	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
	//	GanttRenderer renderer = new GanttRenderer();
		IntervalCategoryDataset dataset = createSampleDataset();
    	
		JFreeChart chart = ChartFactory.createGanttChart("TITILE", "detial", "����", dataset,true,true,false);
		
		
		
		
	//	renderer.setSeriesItemLabelPaint(0, Color.cyan);
		chart.getTitle().setFont(new Font("������",Font.BOLD,20));
		CategoryAxis domainAxis = chart.getCategoryPlot().getDomainAxis();
		domainAxis.setLabelFont(new Font("������",Font.BOLD,14));
		domainAxis.setTickLabelFont(new Font("������",Font.BOLD,12));
		ValueAxis rangeAxis = chart.getCategoryPlot().getRangeAxis();
		rangeAxis.setLabelFont(new Font("������",Font.BOLD,16));
		DateAxis da = (DateAxis)chart.getCategoryPlot().getRangeAxis(0);
		chart.getCategoryPlot().setRenderer(new MyRenderer(dataset));
		
		
		
//		final Color subtask1cColor = Color.blue;

		
		
		
		da.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
		
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
	}
	
	private static Date date(final int day, final int month, final int year) {
		final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        
        final Date result = calendar.getTime();
        return result;
	}
	private static IntervalCategoryDataset createSampleDataset() {
        final TaskSeries s1 = new TaskSeries("SCHEDULE");
       
      
        final Task t1 = new Task("����1", date(1, Calendar.APRIL, 2001), date(5, Calendar.APRIL, 2001));
     //   t1.setPercentComplete(0.8);
        s1.add(t1);
        // ����һ�����񲢲�������������
        
        final Task t3 = new Task("����2", date(5, Calendar.APRIL, 2001), date(5, Calendar.MAY, 2001));
     
        for (int i = 0; i <2; i++) {
			if (i==0) {
				final Task st31 = new Task("����1", date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
		   //     st31.setPercentComplete(0.50);
		        t3.addSubtask(st31);
			}
			else if (i==1) {
				final Task st31 =new Task("����2", date(10, Calendar.APRIL, 2001), date(25, Calendar.APRIL, 2001));
		 //       st31.setPercentComplete(1.0);
				
		        t3.addSubtask(st31);
			}
		}
    
        s1.add(t3);
        
        for (int i = 0; i < 2; i++) {
			if (i==0) {
				 final Task t5 = new Task( "����3", date(2, Calendar.JUNE, 2001), date(5, Calendar.JUNE, 2001));
			        s1.add(t5);
			}
			else if (i==1) {
				final Task t5 = new Task("����4", date(3, Calendar.MARCH, 2001), date(31, Calendar.JULY, 2001));
		  //      t5.setPercentComplete(0.60);
		        
		        s1.add(t5);
			}
		}

        
        final Task t8 = new Task("�������", date(10, Calendar.AUGUST, 2001), date(10, Calendar.AUGUST, 2001));
     
        s1.add(t8);
   
          
        
        final Task t9 = new Task("��������", date(12, Calendar.AUGUST, 2001), date(12, Calendar.SEPTEMBER, 2001));
   
        s1.add(t9);
        
        final Task t10 = new Task("�������", date(13, Calendar.SEPTEMBER, 2001), date(31, Calendar.OCTOBER, 2001));
     
        s1.add(t10);
        
        final Task t12 = new Task("ȫ������", date(28, Calendar.NOVEMBER, 2001), date(30, Calendar.NOVEMBER, 2001));
    
        s1.add(t12);
        final TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(s1);
 
        return collection;
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

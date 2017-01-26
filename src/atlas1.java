import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


class atlas{
	  public  void run() {
		// TODO Auto-generated method stub
			float resizeImg=0.125f;
		    //int num=256;
		    //double h1,h2;
		    int [] k= new int[31];
		    Mat image0 = Imgcodecs.imread(getClass().getResource("/eS001.0-31_Frame1.jpg").getPath());
		    Mat yuvimg0 = new Mat(image0.height(),image0.width(), CvType.CV_8U);
		    Mat smallPic0 = new Mat(image0.height(),image0.width(), CvType.CV_8U);
		    
		    Size dsize0=image0.size();
		    dsize0.height=dsize0.height*resizeImg;
		    dsize0.width=dsize0.width*resizeImg;
		    Imgproc.resize(image0, smallPic0, dsize0);
		    Imgproc.cvtColor(smallPic0, yuvimg0, Imgproc.COLOR_RGB2YUV);
		    List<Mat> Yimg0 = new ArrayList<Mat>(3);
		    Core.split(yuvimg0, Yimg0);

		    Mat channel0 = Yimg0.get(0);
		    Mat imgAvg = new Mat(channel0.height(),channel0.width(), CvType.CV_8U);
		    Mat imgSD2 = new Mat(channel0.height(),channel0.width(), CvType.CV_8U);
		    double[] data=new double[31];
		    double[] a=new double[31];
		    
		    int X=channel0.width();
			  int Y=channel0.height();
			  
		    
			  for(int i=0;i<Y;i++){
				  for(int j=0;j<X;j++){
					  data[0]=0;
					  data[1]=0;
		    for (int l=0;l<k.length;l++){
		    	//Pic1
			    Mat image1 = Imgcodecs.imread(getClass().getResource("/eS001.0-31_Frame"+(l+1)+".jpg").getPath());
			    Mat yuvimg1 = new Mat(image1.height(),image1.width(), CvType.CV_8U);
			    Mat smallPic1 = new Mat(image1.height(),image1.width(), CvType.CV_8U);
			    
			    Size dsize1=image1.size();
			    dsize1.height=dsize1.height*resizeImg;
			    dsize1.width=dsize1.width*resizeImg;
			    Imgproc.resize(image1, smallPic1, dsize1);
			    Imgproc.cvtColor(smallPic1, yuvimg1, Imgproc.COLOR_RGB2YUV);
			    List<Mat> Yimg1 = new ArrayList<Mat>(3);
			    Core.split(yuvimg1, Yimg1);

			    Mat channel1 = Yimg1.get(0);
			    //h1=entropy(channel1,num);
			    //System.out.println("h1="+h1);
			   
						  a[0]=channel1.get(i,j)[0];
						  data[0]+=a[0]/31.0f;
						  //imgAvg.put(i, j, data);
					      
					      data[1] += (a[0]-data[0])*(a[0]-data[1]);
					        
				  //Statistics  avg1=new Statistics(data);
				  //double avg2=avg1.getMean();
				  //double SD2=avg1.getVariance();
						  //if(data[0]>0.0f)
						  //System.out.println("x,y= "+j+","+i+" color="+data[0]);
						  
					
					  }
		    					  imgAvg.put(i, j, data[0]);//avg
								  imgSD2.put(i, j, data[1]/31.0f); //variant
				  }
		    }
		    
		    // Save img
	    String filename0 = "imgAvg1.png";
	    Imgcodecs.imwrite(filename0, imgAvg);
	    String filename1 = "imgSD21.png";
	    Imgcodecs.imwrite(filename1, imgSD2);
		   
		}
}


public class atlas1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				System.out.println("Hello, OpenCV");
			    // Load the native library.
			    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			    new atlas().run();
	}
}


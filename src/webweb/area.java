package webweb;

public class area {
    double length,width,area;
    public area(){
    	length=0;
    	width=0;
    	area=0;
    }
    public void setL(double x){
    	length=x;
    }
    public double getL(){
    	return length;
    }
    public void setW(double x){
    	width=x;
    }
    public double getW(){
    	return width;
    }
    public double getArea(){
    	return length*width;
    }
}

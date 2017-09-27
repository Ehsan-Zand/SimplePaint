package draw;

import java.awt.Color;

public class SelectionHandler {

	public static Color color(Colors col){
		Color color = null;
		
		switch (col) {
		case مشکی:
			color=Color.black;
			break;
		case قرمز:
			color=Color.red;
			break;
		case آبی:
			color=Color.blue;
			break;
		case سبز:
			color=Color.green;
			break;
		default:
			break;
		}
		return color;
	}

//	public static Object shapeType(ShapeType type){
//		Object shape=null;
//		
//		switch (type) {
//		case خط:
//			shape=new Line();
//			break;
//		case دایره:
//			shape=new Circle();
//			break;
//		case مستطیل:
//			shape=new Rectang();
//			break;
//		default:
//			break;
//		}
//		return shape;
//	}
	
	public static Shape shapeType(ShapeType type,int x1,int y1, int x2, int y2, Color col,Boolean fill){
		Shape shape=null;
		
		switch (type) {
		case خط:
			shape=new Line(x1,y1,x2,y2,col);
			break;
		case دایره:
			shape=new Circle(x1,y1,x2,y2,col,fill);
			break;
		case مستطیل:
			shape=new Rectang(x1,y1,x2,y2,col,fill);
			break;
		default:
			break;
		}
		return shape;
	}

}

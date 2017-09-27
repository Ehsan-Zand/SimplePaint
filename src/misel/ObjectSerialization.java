package misel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSerialization implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static byte[] objectToBinary(Object object){
		byte[] otb = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			oos.flush();
			oos.close();
			baos.close();
			otb = baos.toByteArray();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return otb;
	}
	
	public static Object binaryToObject(byte[] bto){
		Object object=null;
		if (bto!=null){
		ByteArrayInputStream bais = new ByteArrayInputStream(bto);
		try {
			ObjectInputStream ois = new ObjectInputStream(bais);
			object=ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return object;
		
	}
}

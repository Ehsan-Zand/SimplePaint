package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ManageDB {

	private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private PreparedStatement ps=null;
    
	public void closeConnections() {
        try {
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            
        }

    }
	
	public void run() {
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            
        } catch (Exception e) {
            
        }
    }
	
	public boolean getUser(String user, String pass) throws SQLException {
        String SQL = "SELECT * FROM login WHERE user = '" + user + "' and pass = '" + pass + "'";
        rs = stmt.executeQuery(SQL);

        rs.last();
        int count = rs.getRow();
        rs.first();
        rs.absolute(0);

        if (count == 0)
        	return false;
        else
        	return true;

    }
	
	
	public ArrayList<byte[]> getByteShapeArraylists (String user){
		String SQL = "SELECT * FROM login WHERE user = '" + user + "'";
		ArrayList<byte[]> gbsal = new ArrayList<>();
        try {
			rs = stmt.executeQuery(SQL);
			if (rs.next()){
				gbsal.add(rs.getBytes("shapeList"));
				gbsal.add(rs.getBytes("clearedShapeList"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gbsal;
		
	}
	
	public void saveShapeArraylists (String user , byte[] shapeList, byte[] clearedShapeList){
		String SQL;

        SQL = "UPDATE login SET shapeList = ? , clearedShapeList = ? WHERE user = ?";
        try {
        	ps = conn.prepareStatement(SQL);
        	ps.setObject(1, shapeList);
        	ps.setObject(2,clearedShapeList);
        	ps.setString(3, user);
        	ps.executeUpdate();
        	ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

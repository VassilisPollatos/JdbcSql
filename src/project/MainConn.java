package project;



public class MainConn {

	public static void main(String args[]) {
		String url = "jdbc:mysql://localhost:3306/stuwo";
		Database db = new Database();
		db.connect(url, "root", "6230Sql");
		db.Menu();
		
		
		

	}

}

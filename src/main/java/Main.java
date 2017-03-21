/**
 * Created by Dima on 20.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Heel");
        DBcon dbcon=new DBcon();
        dbcon.setDBCon();
        dbcon.readTableInfo();
        dbcon.deleteRowByID();
        dbcon.readTableInfo();
        dbcon.addRowToTable();
        dbcon.readTableInfo();
        dbcon.closeConnection();
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Created by Dima on 20.03.2017.
 */
public class DBcon {
    //DataBase Credential
    static final String Url = "jdbc:mysql://localhost/testsh";
    static final String User = "root";
    static final String Pass = "12345";

    Connection con = null;
    Statement stmt = null;
    ResultSet res = null;

    public void setDBCon() {

        try {
            //Load Driver
            Class.forName("com.mysql.jdbc.Driver");
            //Open Connection
            System.out.println("Connecting to DB...");
            con = DriverManager.getConnection(Url, User, Pass);
            System.out.println("Created statement");
            stmt=con.createStatement();
            System.out.println("Connection established ... ! ");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class drivera not foud");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't create connections to DB!!!!");
        }
    }


    public void readTableInfo() {

        String sqlstm;

        sqlstm = "SELECT ID, UserName, Information FROM  TestTable";
        System.out.println("------------------- Read info from Table --------------");
        try {
            res = stmt.executeQuery(sqlstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        parseResultSetData(res);
        System.out.println("==================End read data==========================");
    }

    public void parseResultSetData(ResultSet res) {
        try {
            System.out.println("Start reading from DB");
            while (res.next()) {

                int id = res.getInt("ID");
                String username = res.getString("UserName");
                String information = res.getString("Information");
             //Display info
                System.out.println();
                System.out.println("ID=" + id + " Name= " + username + "Info = " + information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRowByID() {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int id= 0;
        try {
            System.out.println("Please enter row for delete");
            id = br.read();
            System.out.println("You enter id= "+id);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant read info from keyboard");
        }
        String sql = "DELETE FROM testtable where ID= " + id+"";
        try {
            boolean res = stmt.execute(sql);
            System.out.println("Row successfully deleted!");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Can't delete entered row by id =+ + ");
        }
    }

    public void addRowToTable(){
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String sql="";
        System.out.println("Let add info to table please enter ID, userName, Information");
        try {
        System.out.println("Enter ID ");
        int ID=br.read();
        System.out.println("Enter userName");
        String userName=br.readLine();
        System.out.println("Enter information");
        String Information=br.readLine();

        sql="INSERT INTO testtable VALUES("+ID+","+userName+","+Information+")";

            boolean res=stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Info added to table");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Info added to table");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            res.close();
            stmt.close();
            con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cannot close connections");
        }
    }

}

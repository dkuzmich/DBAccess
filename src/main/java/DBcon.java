import java.sql.*;

/**
 * Created by Dima on 20.03.2017.
 */
public class DBcon {
    //DataBase Credential
    static final String Url="jdbc:mysql://localhost/testsh";
    static final String User = "root";
    static final String Pass = "12345";

    Connection con=null;
    Statement stmt=null;
    ResultSet res=null;

    public void setDBCon() {

        try {
            //Load Driver
            Class.forName("com.mysql.jdbc.Driver");

            //Open Connection
            System.out.println("Connecting to DB...");
            con = DriverManager.getConnection(Url, User, Pass);
            System.out.println("Connection established ... ! ");


        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Class drivera not foud");
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't create connections to DB!!!!");
        }
    }


    public void getTableData(){
        System.out.println("Create statement.... ");
        try {
            stmt=con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't create statement");
        }
        String sqlstm;

        sqlstm="SELECT ID, UserName, Information FROM  TestTable";
        try {
            res= stmt.executeQuery(sqlstm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getInfoFromTable(res);
    }

    public void getInfoFromTable(ResultSet res){
        try {
            System.out.println("Start reading from DB");
            while (res.next()){


                int id=res.getInt("ID");
                System.out.println("1. Read ID = "+id);

                String username=res.getString("UserName");
                System.out.println("2. Read userName = "+username);

                String information =res.getString("Information");
                System.out.println("3. Read Information = "+information);

                //Display info
                System.out.println();
                System.out.println("ID="+id+" Name= "+username+"Info = " + information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void setConClosed(){
        try {
            con.close();
            System.out.println("Connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("cannot close connections");
        }
    }

}

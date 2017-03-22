import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

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
            stmt = con.createStatement();
            System.out.println("Connection established ... ! ");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Class drivera not foud");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't create connections to DB!!!!");
        }
    }

    public void myCreateDatabase(){
        setDBCon();
        String sql="Create database test3";

        try {
            int er=stmt.executeUpdate(sql);
            System.out.println("Database test2 created ");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void myDropDatabase(){
        setDBCon();
        String SQL="Drop database test3";
        try {
            int rs=stmt.executeUpdate(SQL);
            System.out.println("Table droped");
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
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
                System.out.println("ID=" + id + " Name= " + username + " Info = " + information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRowByID() {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = 0;
        Boolean bool = false;
        System.out.println("Please enter row for delete");
        //id = br.read();
        id = sc.nextInt();
        System.out.println("You enter id= " + id);

        String sqlTestID = "Select * from testtable where id=" + id + "";
        try {
            ResultSet res = stmt.executeQuery(sqlTestID);
            if (res.next()) {
                int idc=res.getInt("ID");
                String userName=res.getString("UserName");
                String Information=res.getString("Information");

                System.out.println("Found string for delete by ID= "+idc+" userName= "+userName+ "Information= "+Information);
       }


            String sql = "DELETE FROM testtable where ID= " + id + "";

            stmt.executeUpdate(sql);
            System.out.println("!!!!!!! Row is deleted!!!!!!!!!!!");
            readTableInfo();

        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Can't delete entered row by id =+ + ");
        }
    }




    public void addRowToTable() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);



        String sql = "";
        System.out.println("Let add info to table please enter ID, userName, Information");
        try {
           System.out.println("Enter ID ");
            //int ID=br.read();
            int ID = sc.nextInt();

            System.out.println("Enter userName");
            //String userName=br.readLine();
            String UserName =((String) sc.next().toString()).trim();
            System.out.println("Enter information");
            //String Information=br.readLine();
            String Information =(String) sc.next().toString();

            System.out.println("You ented ID- "+ID+" userName = "+UserName+" Information = "+Information);
            sql = "INSERT INTO testtable VALUES("+ID+", '"+UserName+"' , '"+Information+"')";

           // sql = "INSERT INTO testtable VALUES(2,kuzmich,test)";
            PreparedStatement prst=con.prepareStatement(sql);
            int r = prst.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println(" =======!!!!!!!Info cannot be added to table!!!!!");
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

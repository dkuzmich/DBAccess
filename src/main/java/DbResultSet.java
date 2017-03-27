

import java.sql.*;

/**
 * Created by dkuzmich on 3/23/2017.
 */
public class DbResultSet {
    static final String Url = "jdbc:mysql://localhost/testsh";
    static final String User = "root";
    static final String Pass = "12345";
    Connection con=null;
    Statement stm=null;
    PreparedStatement pstm=null;

    public void setDBResSet(){
        try {
            // 1. Register Driver
            Class.forName("com.mysql.jdbc.Driver");

            //2. Open connection
            System.out.println("Connecting to DB...");
            con= DriverManager.getConnection(Url,User,Pass);

            //3. Execute query to create statment
            System.out.println("Create statement");



            stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

            String sql;
            sql="SELECT ID, UserName, Information FROM testtable";


            //test Prepared stm
            pstm=con.prepareStatement(sql);

            ResultSet res=pstm.executeQuery();
            System.out.println("!!! Print info from prepared statement!!!");
            while (res.next()){
                int ID=res.getInt("ID");
                String UserName=res.getString("UserName");
                String Information=res.getString("Information");
                System.out.println("ID= "+ID+" UserName= "+UserName+" Information "+Information);
            }
            System.out.println("!!! END info from prepared statement!!!");


            ResultSet resultSet=stm.executeQuery(sql);

            //Move cursor to last row
            System.out.println("Moving cursor to last");
            resultSet.last();

            //Extract data from result set
            printTable(resultSet);

            //Move cursor to first row
            resultSet.first();
            printTable(resultSet);

            //Move Cursor to next row
            System.out.println("Displaying record all other records...");
            while (resultSet.next()){
                int ID=resultSet.getInt("ID");
            String UserName=resultSet.getString("UserName");
            String Information=resultSet.getString("Information");
            System.out.println("ID= "+ID+" UserName= "+UserName+"Information"+Information);
        }

            //Clean up environment
            System.out.println("Close all conection");
            resultSet.close();
            stm.close();
            con.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printTable(ResultSet rs){
        System.out.println("============Read data from table==========");
        try {
            int ID=rs.getInt("ID");
            String UserName=rs.getString("UserName");
            String Information=rs.getString("Information");
            System.out.println("ID= "+ID+" UserName= "+UserName+"Information"+Information);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("============End data from table==========");
    }

}



import java.sql.*;

/**
 * Created by dkuzmich on 3/27/2017.
 */
public class DbCommitRollBack {
    static final String Url = "jdbc:mysql://localhost/testsh";
    static final String User = "root";
    static final String Pass = "12345";
    Connection con = null;
    Statement stm = null;


    public void setDBResSet() {
        try {
            // 1. Register Driver
            Class.forName("com.mysql.jdbc.Driver");

            //2. Open connection
            System.out.println("Connecting to DB...");
            con = DriverManager.getConnection(Url, User, Pass);

            //3. Execute query to create statment
            System.out.println("Create statement");
            //set autocomint false
            con.setAutoCommit(false);
            //STEP 5: Execute a query to create statment with
            // required arguments for RS example.
            System.out.println("Creating statement...");
            stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

            //STEP 6: INSERT a row into Employees table
            System.out.println("Insert into DB....");

            String sql= "INSERT INTO testtable VALUES (01,'sita','yera')";

            stm.executeUpdate(sql);

            //STEP 7: INSERT one more row into Employees table
            sql="INSERT INTO testtable VALUES (02, 'atis','test')";
            stm.executeUpdate(sql);

            //STEP 8: Commit data here
            System.out.println("Commiting data here....");
            con.commit();

            //STEP 9: Now list all the available records.

            sql="SELECT id, UserName,Information FROM testtable";
            ResultSet res=stm.executeQuery(sql);
            System.out.println("List result set ............");

            printTable(res);

            //STEP 10: Clean-up environment

            res.close();
            stm.close();
            con.close();



        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }

    }
    public void printTable(ResultSet rs){
        System.out.println("============Read data from table==========");

        try {
            rs.beforeFirst();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String UserName = rs.getString("UserName");
                String Information = rs.getString("Information");
                System.out.println("ID= " + ID + " UserName= " + UserName + "Information" + Information);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("============End data from table==========");
    }
}

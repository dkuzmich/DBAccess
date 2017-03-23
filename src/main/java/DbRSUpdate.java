import java.sql.*;
import java.util.Random;

import static java.lang.Math.random;


/**
 * Created by dkuzmich on 3/23/2017.
 */
public class DbRSUpdate {
    static final String Url = "jdbc:mysql://localhost/testsh";
    static final String User = "root";
    static final String Pass = "12345";
    Connection con=null;

    public void setDbRsUpd(){
        System.out.println("Connecting to DB .... .");
        try {
            con=DriverManager.getConnection(Url,User,Pass);

            System.out.println("Creating statement....");
            Statement stmnt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //5. Execute query
            String sql="SELECT ID, UserName, Information FROM testtable";
            ResultSet resultSet=stmnt.executeQuery(sql);

            System.out.println("List result set for reference....");
            printTable(resultSet);

            //Loop throught result set and add 5 to ID
            //Move to beforeFirst for correct while loop
            resultSet.beforeFirst();

            while(resultSet.next()){
                int newID=resultSet.getInt("ID")+13;
                resultSet.updateInt("ID",newID);
                resultSet.updateRow();
            }
            System.out.println("After updating ID ");
            resultSet.beforeFirst();
            printTable(resultSet);

            // Insert a record into the table.
            //Move to insert row and add column data with updateXXX()

            System.out.println("Inserting a new record...");
            resultSet.moveToInsertRow();
            resultSet.updateInt("ID", 7891);
            resultSet.updateString("UserName","Dima777");
            resultSet.updateString("Information", "Test info");
            //Commit changes
            resultSet.insertRow();
            System.out.println("New row inserted");
            //resultSet.beforeFirst();
            printTable(resultSet);

            // Delete second record from the table.
            // Set position to second record first
            resultSet.absolute(2);
            System.out.println("List of record before delete");

            int ID = resultSet.getInt("ID");
            String UserName = resultSet.getString("UserName");
            String Information = resultSet.getString("Information");
            System.out.println("Row before deleteID= " + ID + " UserName= " + UserName + "Information" + Information);

            resultSet.deleteRow();
            System.out.println("Row deleted!");
            printTable(resultSet);
            resultSet.close();
            stmnt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
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

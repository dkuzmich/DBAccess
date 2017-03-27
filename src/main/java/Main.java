/**
 * Created by Dima on 20.03.2017.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Heel");
      /*DbResultSet dbres=new DbResultSet();
      dbres.setDBResSet();
      DbRSUpdate dbupd=new DbRSUpdate();
      dbupd.setDbRsUpd();*/

      DbCommitRollBack dbcomrol=new DbCommitRollBack();
      dbcomrol.setDBResSet();
    }
}

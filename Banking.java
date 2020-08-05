import java.sql.*;

import java.util.Scanner;

public class BankingTest

{

      public static void main (String args []) throws Exception

       {

             

             Scanner s = new Scanner (System.in);

             System.out.println ("Enter the Source account number :" );

             int saccno = s.nextInt ();

             System.out.println ("Enter the Destination account number :");  

             int daccno = s.nextInt ();

            System.out.println ("Enter the amount to transfer :");

            int amnt = s.nextInt ();

            Class.forName ("oracle.jdbc.driver.OracleDriver :");

            Connection cn = DriverManager.getConnection

            ("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

            Statement st = cn.createStatement ();

            cn.setAutoCommit (false);

            ResultSet rs = st.executeQuery ("select avail_balance from account_balance where account_number="+saccno);

            rs.next ();

            int abal = rs.getInt (1);

            //System.out.print (abal);

            if (abal>amnt)

            {

                 int up = st.executeUpdate ("update account_balance set avail_balance =avail_balance-"+amnt+ "where account_number="+saccno);

                 int up1 = st.executeUpdate ("update account_balance set avail_balance = avail_balance-"+amnt+ "where account_number="+daccno);

                 //System.out.print (up+" "+up1);

                 if (up==1 && up1==1)

                 {

                      cn.commit ();

                      System.out.println ("*******: "+amnt+" balance is successfully Transferred:*******");

                 }

                 else

                  {

                      cn.rollback ();

                      System.out.println ("rollback");

                 }

            }

            else

              {

                    System.out.println ("You does not have sufficient balance !!! please deposit in your account.");

              }

      }

}

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Student stud = new Student();
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "");
            if (con != null) {
                System.out.println("Connected");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int ch = 0, op = 0;
        do {
            Statement stmt;
            switch (op) {
                case 1:
                    do {
                        stud.setRollno(sc.nextInt());
                        stud.setS_name(sc.next());
                        stud.setS_email(sc.next());
                        System.out.println(stud.getRollno());
                        System.out.println(stud.getS_name());
                        System.out.println(stud.getS_email());
                        stmt = con.createStatement();
                        stmt.executeUpdate("insert into stud3 values('" + stud.getRollno() + "','" + stud.getS_name() + "','" + stud.getS_email() + "')");
                        System.out.println("Data inserted successfully......");
                        System.out.println("Do you want to continue?");
                        ch = sc.nextInt();
                    } while (ch == 1);
                    break;

                case 2:
                    try {
                        stmt = con.createStatement();
                        stmt.executeQuery("select * from stud3");
                        ResultSet rs = (ResultSet) stmt.getResultSet();
                        while (rs.next()) {
                            System.out.print(rs.getString("rollno"));
                            System.out.print(rs.getString("name"));
                            System.out.print(rs.getString("email"));
                            System.out.println("\n");
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("update stud3 set name='Gauravi' where rollno=3");
                        System.out.println("Your record updated successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;


                case 4:
                    try{
                        stmt = con.createStatement();
                        stmt.executeUpdate("delete from stud3 where rollno=4");
                        System.out.println("Your record deleted successfully.....");

                    }
                    catch (Exception e)
                    {
                        System.out.println(e);
                    }
                    break;

            }
                System.out.println("Enter Your choice: 1:Insert  2:Display 3:Update 4:Delete");
                op = sc.nextInt();
        }while (op != 0) ;
    }
}
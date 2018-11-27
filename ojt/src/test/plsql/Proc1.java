package test.plsql;
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

import www.utility.LogWriter;
 
public class Proc1 {
 
    public Proc1(){ }
    
    public static void main(String[] args) {
        String driver="oracle.jdbc.driver.OracleDriver";
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        String id = "soldesk";
        String password = "1234";
        
        Connection con = null;
        
        // PL/SQL 처리
        //--------------------------------
        CallableStatement cstmt = null;
        //--------------------------------
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, id, password);
 
            // call 명령은 프로시저를 호출합니다.
            cstmt = con.prepareCall("{call proc1(?,?)}");
            
            // 프로시저에 넘겨줄 인수 지정
            cstmt.setInt(1, 10);
            cstmt.setFloat(2, 1.5f);
            cstmt.executeUpdate();
            
            System.out.print("로직 처리 완료");
            LogWriter.logWrite("test", "info", "Log4jTest.oracleConnect()", id + " 계정 데이터베이스 접속 성공", null);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{ cstmt.close(); }catch(Exception e){}
            try{ con.close(); }catch(Exception e){}    
        }
        System.out.println("정상적으로 실행되었습니다.");
    }
}
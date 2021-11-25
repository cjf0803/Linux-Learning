import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import com.google.gson.*;
public class AppJson extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://123.60.105.251/Linux_final";
    static final String USER = "root";
    static final String PASS = "13326@Chen";
    static Connection conn = null;

   public void init() {
      try {
         Class.forName(JDBC_DRIVER);
         conn = DriverManager.getConnection(DB_URL, USER, PASS);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
          response.setContentType("text/html");
          PrintWriter out = response.getWriter();
	  Gson gson = new Gson();
          Student stu = getStudent();
	  String json = gson.toJson(stu);
          out.println(json);
          out.flush();
          out.close();
    }
    
    public Student getStudent() {
        Student stu = new Student();
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "SELECT * FROM t_student WHERE id=4";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stu.setid(rs.getInt("id"));
                stu.setname(rs.getString("name"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    return stu;
   
    }

}
class Student {

    public String name;
    public int id;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public Student() {
		
	}

}

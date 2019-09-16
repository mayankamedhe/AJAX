

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
/**
 * Servlet implementation class AutoCompleteUser
 */
@WebServlet("/AutoCompleteUser")
public class AutoCompleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
//    private static final String languageString = "Person1,Person2,p2";
//    private static final String[] languageArray = languageString.split(",");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoCompleteUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String val = request.getParameter("other_id");
	    System.out.println("in autoother_id=" + val);
//		List<String> languages = findLanguages(languagePrefix);
//		response.setContentType("application/json");
//		PrintWriter out = response.getWriter();
//		out.print(languages);
//	}
//		private List<String> findLanguages(String languagePrefix)
//		{
//			languagePrefix = languagePrefix.toUpperCase();
//			List<String> languages = new ArrayList<String>();
//			for(String language: languageArray)
//			{
//				if(language.toUpperCase().startsWith(languagePrefix))
//				{
//					languages.add(language);
//				}
//			}
//			return(languages);
//		}

		PrintWriter out = response.getWriter(); 
		HttpSession session = request.getSession();
//UNCOMMENT THIS!!!!
//		if(session.getAttribute("id") == null) { //not logged in
//			response.sendRedirect("LoginServlet");
//		}
//		String val = "99";
		String query = "select uid || ' ' || name || ' ' || phone  as label, uid as value from "
				+ "users where name like ? or uid like ? or phone "
						+ "like ? ";
		
		//String query = "insert into conversations values (?, ?)";
		String json = DbHelper.executeQueryJson(query, 
				new DbHelper.ParamType[] {DbHelper.ParamType.STRING,  DbHelper.ParamType.STRING, DbHelper.ParamType.STRING},
				new String[] {"%"+val+"%","%"+val+"%","%"+val+"%"});
		
		//out.println("success");
		response.getWriter().print(json);			
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

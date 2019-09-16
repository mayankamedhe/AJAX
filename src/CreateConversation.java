
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class CreateConversation
 */
@WebServlet("/CreateConversation")
public class CreateConversation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateConversation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null) { //not logged in
			response.sendRedirect("LoginServlet");
		}
		
		String id = (String) session.getAttribute("id");
		String other_id = (String) request.getParameter("other_id");
		PrintWriter out = response.getWriter(); 
		
		System.out.println("id="+id + " other_id="+ other_id);
		//ensure order
		String uid1, uid2;
		if(id.compareTo(other_id) < 0) {
			uid1 = id;
			uid2 = other_id;
		}
		else {
			uid1 = other_id;
			uid2 = id;
		}
		
		String query = "insert into conversations values (?, ?)";
		String json = DbHelper.executeUpdateJson(query, 
				new DbHelper.ParamType[] {DbHelper.ParamType.STRING,  DbHelper.ParamType.STRING},
				new String[] {uid1, uid2});
		
		//response.getWriter().print(json);
		
		String f = "false";
		
		if(json.indexOf(f) != -1)
		{
			//out.println("failure");
			 JOptionPane.showMessageDialog(null, "Failure!");
			 response.sendRedirect("Home");
		}
		else
		{
			//out.println("success");
			 JOptionPane.showMessageDialog(null, "Success!");
			 response.sendRedirect("Home");
		}
//		String html = "xhttp = new XMLHttpRequest();\r\n" + 
//				"\r\n" + 
//				"  xhttp.onreadystatechange = function() {\r\n" + 
//				"  console.log(this.responseText);\r\n" + 
//				"  if (this.readyState == 4 && this.status == 200) {\r\n" +  
//				"        var myObj = JSON.parse(this.responseText).data;" +
//				"str = \"\" " +
//				"for(iter in myObj) { \r\n" +
//				"        str = myObj[iter].status;} " +
//				"document.getElementById(\"txtHint\").innerHTML = str;" +
//				"}};";
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}

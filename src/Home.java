
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String html = "<html><head><title>Sample Ajax Page</title>" + 
				"    <script src=\"jquery-3.3.1.js\"> </script>" + 
				"    <script src=\"jquery.dataTables.min.js\"></script>" + 
				"    <script src=\"jquery-ui.min.js\"></script>" + 

				"    <link rel=\"stylesheet\" href=\"jquery-ui.css\" />" + 
				"    <link rel=\"stylesheet\" href=\"jquery.dataTables.min.css\"/>" + 
				
				"	 <script src=\"whatasap_home.js\"></script>" +
				
				"</head>" + 
				"<body>" + 
				"<button onclick=\"ho()\"> Home </button> <br><br>" +
		       
				"			    		Search:<input type=\"text\" name=\"userid\" id=\"sear\" /><br/><br/> \n" + 
				"			    		<button type=\"submit\" onclick=\"Search_fun()\">Submit</button> \n" + 
		
				"    <div id=\"content\">" +
		        
		        "    <table id=\"myyTable\" class=\"display\">" + 
				"        <thead>" + 
				"        <tr> <th>uid</th> <th>last_timestamp</th> <th>num_msgs</th> </tr>" + 
				"        </thead>" + 
				"    </table>" +
		        
		        "	 </div> <br><br>" + 
				
				"<a href=\"#\" onclick=\"Form_create()\" >Create Conversation</a>\n" +  
				"<div id=\"form\"> " +
				"</div> <br><br>" +
				"</body>" + 
				"</html>";
		response.setContentType("text/html");
		response.getWriter().print(html);
//		HttpSession session = request.getSession();
//		session.setAttribute("", userid);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

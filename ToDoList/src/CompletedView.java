

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tlist;
import customTools.DBUtil;

/**
 * Servlet implementation class CompletedView
 */
@WebServlet("/CompletedView")
public class CompletedView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletedView() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true); 
		String username = (String) session.getAttribute("username");
		System.out.println("the session userid is "+username);
		String message = "";	
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try
		{
		Tlist tdlist = new Tlist();
		String q="select p from Tlist p where p.status = 'Completed' and p.lUsername='"+username+"'";
		TypedQuery<Tlist>aq =em.createQuery(q,Tlist.class);
		//System.out.println(""+aq);
		List<Tlist> list=aq.getResultList();
		System.out.println("query reult:"+aq.getResultList());
		message= "";
		for(Tlist temp:list)
		{	
			
			message+=" <a href=\"DetailsServlet?itemid="+temp.getLid()+"\" class=\"list-group-item\">"+temp.getDescription()+"</a>";   
			
		}
		
		/*message1+="<div class=\"container\">"+
				  "<form role=\"form\" action =  \"Add.jsp\"  method =\"post\" >"+
				  "<button type=\"submit\" class=\"btn btn-default\">Add Task</button>"+
				  "</form></div>";
		request.setAttribute("button",message1);*/
		
		
		}
		catch(Exception e)
		{
			System.out.println(""+e.toString());
			System.out.println(""+e.getCause());
			System.out.println(""+e.getStackTrace());
		}
		
		request.setAttribute("products", message);
		getServletContext().getRequestDispatcher("/list.jsp").forward(request, response);
			
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


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

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/DetailsServlet")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	/* System.out.println("in details swerv get");*/
    	EntityManager em = DBUtil.getEmFactory().createEntityManager();
    	String pic = "";
			try
		
		{
			Integer item_id = Integer.parseInt(request.getParameter("itemid"));
			HttpSession session = request.getSession();
			session.setAttribute("itemID",item_id);
	
		String message = "";
		String message1 = "";
		String q="select p from Tlist p where p.lid="+item_id;
		//System.out.println("query "+q);
		TypedQuery<Tlist>aq =em.createQuery(q,Tlist.class);
		List<Tlist> list=aq.getResultList();
	
		for(Tlist temp:list)
		{	
			//pic = "<div class=\"container\"><center><img src='"+temp.getPicture()+"' class=\"img-rounded\" alt=\"Cinque Terre\"width=\"250\" height=\"240\"></center></div>";
			message+="<h1>ToDo: "+temp.getDescription()+"</h1><h2>Due Date: "+temp.getDuedate()+"</h2><h2>Status: "+temp.getStatus()+"<pre><pre> Date Completed: "+temp.getCompleted()+"</h2>";
			/*	+"<a class=\"btn btn-primary\" href=\"AddServlet?itemid="+item_id+"\"> Add to Cart </a>";*/
			/*message1+="<div class=\"container\">  <form class=\"form-inline\" role=\"form\" action =  \"AddServlet\"  method =\"post\"> <div class=\"form-group\">  <label for=\"\">Quantity:</label>"+
			   "<input type=\"hidden\" name=\"action\" value=\""+temp.getLid()+"\"/>";*/
			
			/*if(request.getSession().getAttribute("username")==null)
			{
				//System.out.println("$");
				message1+=" </form>	</div>";
			}
			else
			{
				
				System.out.println("$**********$");
				request.getSession().setAttribute("productID", temp.getId());
				System.out.println(temp.getId());
				message1+="  <input type=\"text\" class=\"form-control\" id=\"quantity\" name = \"quantity\">  </div>"+
			   " <button type=\"submit\" class=\"btn btn-default\">Add to Cart</button>  </form>	</div>";
			
			}
			*/
		}
		message1+="<div class=\"container\">"+
				  "<form role=\"form\" action =  \"Edit.jsp\"  method =\"post\" >"+
				  "<button type=\"submit\" class=\"btn btn-default\">Edit</button>"+
				  "</form></div>";
		
		System.out.println(message1);
		request.setAttribute("edit", message1);
		request.setAttribute("item", message);

	getServletContext().getRequestDispatcher("/details.jsp").forward(request, response);

		
		}
		catch(Exception e)
		{
			System.out.println(""+e.getStackTrace());

		}
		
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = (String) request.getSession().getAttribute("username");
		String description = request.getParameter("task");
		String ddate = request.getParameter("ddate");
		//int item_id = Integer.parseInt(request.getParameter("action"));
		
		//System.out.println(item_id);
		if(request.getSession().getAttribute("tdlist")==null)
		{
			List<Tlist> tdlist = null;
			
		}
	
		
		List<Tlist> tdlist = (List<Tlist>) request.getSession().getAttribute("tdlist");
		
		//int quantity = Integer.parseInt(request.getParameter("quantity"));
		//String q2 = "select p from Tlist p where p.id = " +item_id;
		
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		//TypedQuery<Tlist> bq2 = em.createQuery(q2, Tlist.class);
		//List<Tlist> list2 = bq2.getResultList();
		EntityTransaction trans = em.getTransaction();
		model.Tlist add = new model.Tlist();
		trans.begin();
		try {
			
			String post = request.getParameter("post");
			add.setLUsername(username);
			add.setDescription(description);
			add.setDuedate(ddate);
			add.setStatus("incomplete");
			add.setCompleted("N/A");
			em.persist(add);
			trans.commit();
			tdlist.add(add);
			
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			//trans.rollback();
		} 
		//System.out.println("this is my array "+list2.get(0).getPname());
		//System.out.println("this is my array "+list2.size());
		
		
		
		request.getSession().setAttribute("tdlist", tdlist);
		
		getServletContext().getRequestDispatcher("/list.jsp").forward(request,  response);
	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

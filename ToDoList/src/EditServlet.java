

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

import org.apache.catalina.Session;

import model.*;
import customTools.DBUtil;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username = (String) request.getSession().getAttribute("username");
		String description = request.getParameter("task");
		String ddate = request.getParameter("ddate");
		String cdate = request.getParameter("cdate");
		Integer itemID = (Integer) request.getSession().getAttribute("itemID");
		//int id = Integer.parseInt(itemID);
		//int item_id = Integer.parseInt(request.getParameter("action"));
		
		//System.out.println(item_id);
	
		
		List<Tlist> tdlist = (List<Tlist>) request.getSession().getAttribute("tdlist");
		
		//int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String q2 = "select p from Tlist p where p.lid = " +itemID;
		TypedQuery<Tlist> bq2 = em.createQuery(q2, Tlist.class);
		List<Tlist> list2 = bq2.getResultList();
		EntityTransaction trans = em.getTransaction();
		model.Tlist add = new model.Tlist();
		trans.begin();
		for (Tlist temp : list2)
		{
		try {

			temp.setDescription(description);
			temp.setDuedate(ddate);
			System.out.println(cdate);
			if (cdate!= null || cdate != "")
			{
				temp.setCompleted(cdate);
				temp.setStatus("Completed");
			}
			if(cdate == null ||  cdate == "")
			{
				//temp.setCompleted("N/A");
				temp.setStatus("Incomplete");
			}
			em.merge(temp);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			em.close();
		}
		}
			//trans.rollback();
		session.setAttribute("itemID", null);
		getServletContext().getRequestDispatcher("/list.jsp").forward(request,  response);
		} 
		//System.out.println("this is my array "+list2.get(0).getPname());
		//System.out.println("this is my array "+list2.size());
	
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

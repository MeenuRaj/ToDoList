

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("in get");
		String username = request.getParameter("username");
		String password = request.getParameter("pwd");
		Double credit = 0.0;
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		model.Tuser user = new model.Tuser();
		String message = "";
		HttpSession session = request.getSession();

		
//			acc.setUsername(password);
			session.setAttribute("username", username);
			String q="select g from Tuser g where g.username ='"+username+"'";
			System.out.println(q);
			TypedQuery<Tuser>aq =em.createQuery(q,Tuser.class);
			System.out.println(aq);
			List<Tuser> list1=aq.getResultList();
			if (list1 == null || list1.isEmpty())
			{
				message = "Incorrect username or password";
				response.setContentType("text/html");
				request.setAttribute("message", message);
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			}
			else	
				{
				String q2 = "select c from Tlist c where c.lUsername ='" +username+"'";
				TypedQuery<Tlist> bq2 = em.createQuery(q2, Tlist.class);
				List<Tlist> list = bq2.getResultList();
				session.setAttribute("tdlist", list);
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				}
			
		
			}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

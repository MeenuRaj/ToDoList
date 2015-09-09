

import java.io.IOException;
import java.math.BigDecimal;
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
 * Servlet implementation class logout
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true); 
		String uid = (String) session.getAttribute("username");
		String message = "";
		
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		//**********/
		List<Tlist> cart = (List<Tlist>) session.getAttribute("shopping_cart");
		message = "<thead><tr><th>You're Cart</th></tr></thead>";
		
		try {
			trans.begin();
		for(CartObj loop: cart)
		{
			model.Cart newcart = new model.Cart();
		
				newcart.setCUname(uid);
				System.out.println(uid);
				newcart.setProduct(loop.getName());
				System.out.println(loop.getName());
				newcart.setPrice(BigDecimal.valueOf(loop.getPrice()));
				System.out.println(BigDecimal.valueOf(loop.getPrice()));
				newcart.setQuantity(BigDecimal.valueOf(loop.getQuantity()));
				newcart.setBought("no");
				System.out.println(BigDecimal.valueOf(loop.getQuantity()));
				em.persist(newcart);

		}
			trans.commit();
			} catch (Exception e) {
				System.out.println("ERROR:" + e);
				e.printStackTrace();
				
			} 
		}
		session.setAttribute("username", null);
		session.setAttribute("shopping_cart", null);
		/*for (CartObj list1 : cart)
		{
			System.out.println(list1.getName());
			System.out.println(list1.getPrice());
			System.out.println(list1.getQuantity());
		}*/
		
		
		
	
	getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

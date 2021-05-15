package ec.edu.ups.controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class CrearUsuarioController
 */
@WebServlet("/CrearUsuarioController")
public class CrearUsuarioController extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CrearUsuarioController() {
        super();
        usuarioDAO = DAOFactory.getFactory().getUsuario();
		usuario = new Usuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = null;
		try {
			System.out.println("Nombre: "+request.getParameter("nombre"));
			usuario.setCedula(String.valueOf(request.getParameter("cedula")));
			usuario.setNombre(String.valueOf(request.getParameter("nombre")));
			usuario.setApellido(String.valueOf(request.getParameter("apellido")));
			usuario.setCorreo(String.valueOf(request.getParameter("email")));
			usuario.setContrasena(String.valueOf(request.getParameter("pass")));
			usuario.setTelefonos(null);
			usuarioDAO.create(usuario);	
			
			url = "/index.html";
		} catch (Exception e) {
			url = "/JSPs/error.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}

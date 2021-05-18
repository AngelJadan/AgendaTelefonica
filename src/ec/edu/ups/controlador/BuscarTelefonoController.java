package ec.edu.ups.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class BuscarTelefono
 */
@WebServlet("/BuscarTelefonoController")
public class BuscarTelefonoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TelefonoDAO telefonoDAO;
	private Telefono telefono;
	private UsuarioDAO usuarioDAO;
	private Usuario usuario;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarTelefonoController() {
        super();
        telefonoDAO = DAOFactory.getFactory().getTelefono();
        telefono = new Telefono();
        
        usuarioDAO = DAOFactory.getFactory().getUsuario();
        usuario = new Usuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Busqueda telefono");
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String url = null;
		System.out.println("Codigo: "+codigo);
		
		try {
			telefono = telefonoDAO.read(codigo);
			System.out.println("Telefono "+telefono.toString());
			request.setAttribute("telefono", telefono);
			url = "/JSPs/editarTelefono.jsp";
		} catch (Exception e) {
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Metodo para actualziar el telefono
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url =null;
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String numero = String.valueOf(request.getParameter("numero"));
		String tipo = String.valueOf(request.getParameter("tipo"));
		String operadora = String.valueOf(request.getParameter("operadora"));
		String cedula = String.valueOf(request.getParameter("cedula"));
		
		try {
			telefono =  new Telefono(codigo, numero, tipo, operadora, null);
			System.out.println("Nuevo telefono "+telefono.toString());
			telefonoDAO.update(telefono);
			

			String sms= "<html><head><title>Actualizado</title></head><body>";
			PrintWriter out = response.getWriter();
			out.println(sms);
			out.println("<a href='/AgendaTelefonica/JSPs/inicio_usuario.jsp'>Volver</a> <br>");
			out.println("<p>Los datos ha sido actualizados</p>");
		} catch (Exception e) {
			System.out.println("Error al actualizar en controller. "+e.getMessage());
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}

package ec.edu.ups.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = null;
		try {
			System.out.println("Nombre: " + request.getParameter("nombre"));
			usuario.setCedula(String.valueOf(request.getParameter("cedula")));
			usuario.setNombre(String.valueOf(request.getParameter("nombre")));
			usuario.setApellido(String.valueOf(request.getParameter("apellido")));
			usuario.setCorreo(String.valueOf(request.getParameter("email")));
			usuario.setContrasena(String.valueOf(request.getParameter("pass")));
			usuario.setTelefonos(null);
			String sms = "<html><head><title>Usuario registrado</title></head><body><a href='./HTMLs/register.html'>Volver</a><br>";
			if (usuario.getCedula().length() == 10 && valida(usuario.getCedula()) == true) {
				if (usuario.getNombre().length() > 0) {
					if (usuario.getApellido().length() > 0) {
						if (usuario.getCorreo().length() > 0 && valEmail(usuario.getCorreo())==true) {
							if (usuario.getContrasena().length() > 0) {
								try {
									Usuario utemp = new Usuario();
									utemp = usuarioDAO.read(usuario.getCedula());
									System.out.println("Usuario temporal"+utemp.toString());
									if (utemp==null) {
										usuarioDAO.create(usuario);
										sms =sms+ "<a href='./index.html'>Volver</a>"
												+ "<p>El usuario ha sido registrado</p>";
									}else {
										sms =sms+ "<p>El usuario ya se encuentra registrado con este numero de cédula.</p>";
									}
								} catch (Exception e) {
									System.out.println(e.getMessage());
									sms =sms+ "<p>El usuario ya se encuentra registrado.</p>";
								}
							} else {
								sms =sms+ "<p>Ingrese una contraseña</p>";
							}
						} else {
							sms = sms+ "<p>Ingrese un correo valido</p>";
						}
					} else {
						sms =sms+ "<p>Ingrese un apellido</p>";
					}
				} else {
					sms = sms+ "<p>Ingrese un nombre</p>";
				}
			} else {
				sms = sms+ "<p>Ingrese un número de cedula</p>";
			}
			sms=sms+"<body>";
			PrintWriter io = response.getWriter();
			io.println(sms);
			//url = "/index.html";
		} catch (Exception e) {
			url = "/JSPs/error.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * 
	 * @param x Cedula a vilidar
	 * @return true or false
	 */

	public static boolean valida(String x) {
		int suma = 0;
		if (x.length() == 9) {
			System.out.println("Ingrese su cedula de 10 digitos");
			return false;
		} else {
			int a[] = new int[x.length() / 2];
			int b[] = new int[(x.length() / 2)];
			int c = 0;
			int d = 1;
			for (int i = 0; i < x.length() / 2; i++) {
				a[i] = Integer.parseInt(String.valueOf(x.charAt(c)));
				c = c + 2;
				if (i < (x.length() / 2) - 1) {
					b[i] = Integer.parseInt(String.valueOf(x.charAt(d)));
					d = d + 2;
				}
			}

			for (int i = 0; i < a.length; i++) {
				a[i] = a[i] * 2;
				if (a[i] > 9) {
					a[i] = a[i] - 9;
				}
				suma = suma + a[i] + b[i];
			}
			int aux = suma / 10;
			int dec = (aux + 1) * 10;
			if ((dec - suma) == Integer.parseInt(String.valueOf(x.charAt(x.length() - 1))))
				return true;
			else if (suma % 10 == 0 && x.charAt(x.length() - 1) == '0') {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean valEmail(String mail) {
		if (mail != null) {
			String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
			Pattern pattern = Pattern.compile(emailPattern);
			Matcher matcher = pattern.matcher(mail);
			if (matcher.matches()) {
				return true;
			} else {
				return false;
			}
		}else {
			return false;
		}
	}
}

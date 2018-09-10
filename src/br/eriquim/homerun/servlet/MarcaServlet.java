package br.eriquim.homerun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.eriquim.homerun.dom.Marca;
import br.eriquim.homerun.service.MarcaServiceImpl;

@WebServlet("/marcaServlet")
public class MarcaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4855239982108790501L;

	private MarcaServiceImpl marcaService;

	public void init() throws ServletException {
		marcaService = new MarcaServiceImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String nome = request.getParameter("nome");
		String sigla = request.getParameter("sigla");
		
		Marca marca = new Marca();
		marca.setNome(nome);
		marca.setSigla(sigla);
		
		PrintWriter out = response.getWriter();
		try {
			marcaService.inserir(marca);
		} catch (SQLException e) {
			out.println("<h1> Erro ao inserir a marca. Verifique os dados e tente novamente.</h1>");
			e.printStackTrace();
		}

		out.println("<h1> Dado Inserido com sucesso.</h1>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void destroy() {
		marcaService = null;
	}

}

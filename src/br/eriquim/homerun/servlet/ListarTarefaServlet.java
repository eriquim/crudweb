package br.eriquim.homerun.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.eriquim.homerun.dom.Tarefa;
import br.eriquim.homerun.exception.BussinessException;
import br.eriquim.homerun.service.TarefaServiceImpl;

@WebServlet("/getTarefaAllServlet")
public class ListarTarefaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4855239982108790501L;

	private TarefaServiceImpl tarefaServlet;

	public void init() throws ServletException {
		tarefaServlet = new TarefaServiceImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String json = null;
		try {
			json = new Gson().toJson(tarefaServlet.findAll());
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void destroy() {
		tarefaServlet = null;
	}

}

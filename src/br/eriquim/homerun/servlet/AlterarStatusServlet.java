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

import br.eriquim.homerun.dom.StatusTarefa;
import br.eriquim.homerun.dom.Tarefa;
import br.eriquim.homerun.exception.BussinessException;
import br.eriquim.homerun.service.TarefaServiceImpl;

@WebServlet("/alterarStatus")
public class AlterarStatusServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4855239982108790501L;

	private TarefaServiceImpl tarefaServiceImpl;

	public void init() throws ServletException {
		tarefaServiceImpl = new TarefaServiceImpl();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String idTarefa = null;
		 String novoStatusTarefa = null;
		try {
			idTarefa =	request.getParameter("id");
			novoStatusTarefa =	request.getParameter("novoStatus");
			Tarefa t = tarefaServiceImpl.findById(Integer.parseInt(idTarefa));
			t.setStatusTarefa(StatusTarefa.valueOf(novoStatusTarefa.toUpperCase()));
			
			tarefaServiceImpl.update(t);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(new Gson().toJson(tarefaServiceImpl.findAll()));
			out.flush();
			
		} catch (BussinessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void destroy() {
		tarefaServiceImpl = null;
	}

}

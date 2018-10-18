package br.eriquim.homerun.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.eriquim.homerun.dom.StatusTarefa;
import br.eriquim.homerun.dom.Tarefa;

public class TarefaDAO extends GenericDAO<Tarefa> {

	private static final long serialVersionUID = -6668635906409414524L;

	@Override
	public List<Tarefa> getAll() throws SQLException {
		List<Tarefa> retorno = new ArrayList<Tarefa>();
		
		String sql = "select * from public.tarefa order by id";
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			pst = getConnection().prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setNome(rs.getString("nome"));
				tarefa.setResponsavel(rs.getString("responsavel"));
				tarefa.setStatusTarefa(StatusTarefa.valueOf(rs.getString("status")));
				
				retorno.add(tarefa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pst.close();
			rs.close();
		}
		return retorno;
	}

	@Override
	public void save(Tarefa t) throws SQLException {
		String sql = "insert into public.tarefa(nome,responsavel,status) values (?,?,?);";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1,t.getNome());
			pst.setString(2,t.getResponsavel());
			pst.setString(3,t.getStatusTarefa().toString());
			pst.execute();
		} finally {
			pst.close();
		}
	}

	@Override
	public void update(Tarefa t) throws SQLException {
		String sql = "update public.tarefa set nome =?, responsavel=? , status=? where id=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1,t.getNome());
			pst.setString(2,t.getResponsavel());
			pst.setString(3,t.getStatusTarefa().toString());
			pst.setInt(4,t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
		
	}

	@Override
	public void remove(Tarefa t) throws SQLException {
		String sql = "delete from public.tarefa where id=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setInt(1,t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
	}

	@Override
	public Tarefa findByPrimaryKey(Integer id) throws SQLException {
		String sql = "select * from public.tarefa where id=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		try {
			if(rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getInt("id"));
				tarefa.setNome(rs.getString("nome"));
				tarefa.setResponsavel(rs.getString("responsavel"));
				tarefa.setStatusTarefa(StatusTarefa.valueOf(rs.getString("status")));
				return tarefa;
			} else {
				return null;
			}
		} finally {
			rs.close();
			pst.close();
		}
		
	}


}

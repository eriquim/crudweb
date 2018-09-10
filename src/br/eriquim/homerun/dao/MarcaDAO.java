package br.eriquim.homerun.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.eriquim.homerun.dom.Marca;

public class MarcaDAO extends GenericDAO<Marca> {

	private static final long serialVersionUID = -6668635906409414524L;

	@Override
	public List<Marca> getAll() throws SQLException {
		List<Marca> retorno = new ArrayList<Marca>();
		
		String sql = "select * from public2.marca";
		PreparedStatement pst = null;
		ResultSet rs= null;
		try {
			pst = getConnection().prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()) {
				Marca marca = new Marca();
				marca.setId(rs.getInt("id_marca"));
				marca.setNome(rs.getString("nome"));
				marca.setSigla(rs.getString("sigla"));
				retorno.add(marca);
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
	public void save(Marca t) throws SQLException {
		String sql = "insert into public2.marca(nome,sigla) values (?,?);";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1,t.getNome());
			pst.setString(2,t.getSigla());
			pst.execute();
		} finally {
			pst.close();
		}
	}

	@Override
	public void update(Marca t) throws SQLException {
		String sql = "update public2.marca set nome =?, sigla=? where id_marca=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1,t.getNome());
			pst.setString(2,t.getSigla());
			pst.setInt(3,t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
		
	}

	@Override
	public void remove(Marca t) throws SQLException {
		String sql = "delete from public2.marca where id_marca=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setInt(1,t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
	}

	@Override
	public Marca findByPrimaryKey(Integer id) throws SQLException {
		String sql = "select * from public2.marca where id_marca=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		try {
			if(rs.next()) {
				Marca marca = new Marca();
				marca.setId(rs.getInt("id_marca"));
				marca.setNome(rs.getString("nome"));
				marca.setSigla(rs.getString("sigla"));
				return marca;
			} else {
				return null;
			}
		} finally {
			rs.close();
			pst.close();
		}
		
	}


}

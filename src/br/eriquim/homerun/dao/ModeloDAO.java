package br.eriquim.homerun.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.eriquim.homerun.dom.Modelo;

public class ModeloDAO extends GenericDAO<Modelo> {

	private static final long serialVersionUID = 8946828168029066803L;

	@Override
	public List<Modelo> getAll() throws SQLException {
		List<Modelo> retorno = new ArrayList();
		String sql = "select * from public2.modelo";
		MarcaDAO dao = new MarcaDAO();
		PreparedStatement pst = getConnection().prepareStatement(sql);
		
		try {
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Modelo modelo = new Modelo();
				modelo.setId(rs.getInt("id_modelo"));
				modelo.setNome(rs.getString("nome"));
				modelo.setAno(rs.getInt("ano"));
				modelo.setMarca(dao.findByPrimaryKey(rs.getInt("id_marca")));
				retorno.add(modelo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dao.close();
		}
		return retorno;
	}

	@Override
	public void save(Modelo t) throws SQLException {
		String sql = "insert into public2.modelo(nome,id_marca,ano) values (?,?,?)";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1, t.getNome());
			pst.setInt(2, t.getMarca().getId());
			pst.setInt(3, t.getAno());
			pst.execute();
		} finally {
			pst.close();
		}
	}

	@Override
	public void update(Modelo t) throws SQLException {
		String sql = "update public2.modelo set nome=?, id_marca=?, ano=? where id_modelo=?";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setString(1, t.getNome());
			pst.setInt(2, t.getMarca().getId());
			pst.setInt(3, t.getAno());
			pst.setInt(4, t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
		
		
	}

	@Override
	public void remove(Modelo t) throws SQLException {
		String sql = "delete from public2.modelo where id_modelo=? ";
		PreparedStatement pst = getConnection().prepareStatement(sql);
		try {
			pst.setInt(1, t.getId());
			pst.executeUpdate();
		} finally {
			pst.close();
		}
	}

	@Override
	public Modelo findByPrimaryKey(Integer id) throws SQLException {
		Modelo retorno = null;
		String sql = "select * from public2.modelo where id_modelo=?";
		MarcaDAO dao = new MarcaDAO();
		PreparedStatement pst = getConnection().prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		try {
			if(rs.next()) {
				retorno = new Modelo();
				retorno.setId(rs.getInt("id_modelo"));
				retorno.setNome(rs.getString("nome"));
				retorno.setMarca(dao.findByPrimaryKey(rs.getInt("id_marca")));
				retorno.setAno(rs.getInt("ano"));
			}
		} finally {
			dao.close();
			rs.close();
			pst.close();
		}
		return retorno;
	}

}

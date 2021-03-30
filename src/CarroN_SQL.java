

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarroN_SQL {
	

	public List<CarroN> listAll() throws Exception {
		ArrayList<CarroN> list = new ArrayList<CarroN>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT id, modelo,preco,marca,ano FROM CarroNovo");
			  while (rs.next()) {
			      CarroN cn = new CarroN();
				  cn.setId(rs.getInt("id"));
				 cn.setModelo(rs.getString("modelo"));
				  cn.setMarca(rs.getString("marca"));
				 cn.setAno(rs.getInt("ano"));
				 cn.setPreco(rs.getString("preco"));
				  list.add(cn);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public CarroN getCarroN(int id) throws Exception {
		CarroN cn = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					 "SELECT id, modelo,preco,marca,ano FROM CarroNovo where id = ?");
			stmt.setInt(1, id);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      cn = new CarroN();
			      cn.setId(rs.getInt("id"));
					 cn.setModelo(rs.getString("modelo"));
					  cn.setPreco(rs.getString("preco"));
					  cn.setMarca(rs.getString("marca"));
					  cn.setAno(rs.getInt("ano"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return cn;
	}

	
	public CarroN inserir(CarroN cn) throws Exception {
		String sqlInsert =
			"INSERT INTO CarroNovo (id, modelo,marca,ano,preco) VALUES ( ?,?,?,?,?)";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, cn.getId());
			ps.setString(2, cn.getModelo());
			ps.setString(3, cn.getMarca());
			ps.setInt(4, cn.getAno());
			ps.setString(5, cn.getPreco())
			;
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Carro Não Foi Inserido"); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cn;
	}

	public CarroN excluir(CarroN cn) throws Exception {
		String sqlExcluir =
			"DELETE FROM CarroNovo where id = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, cn.getId());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Carro Não Encontrado!"); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cn;
	}


}

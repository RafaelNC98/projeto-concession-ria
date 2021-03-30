

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CarroS_SQL {
	

	public List<CarroS> listAll() throws Exception {
		ArrayList<CarroS> list = new ArrayList<CarroS>();

		try {
			Connection con = AppConnection.getConnection();
			Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(
			          "SELECT id, modelo,preco,marca,ano FROM CarroSemi");
			  while (rs.next()) {
			      CarroS cs = new CarroS();
				  cs.setId(rs.getInt("id"));
				 cs.setModelo(rs.getString("modelo"));
				  cs.setMarca(rs.getString("marca"));
				 cs.setAno(rs.getInt("ano"));
				 cs.setPreco(rs.getString("preco"));
				  list.add(cs);
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return list;
	}
	
	public CarroS getCarroS(int id) throws Exception {
		CarroS cs = null;
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					 "SELECT id, modelo,preco,marca,ano FROM CarroSemi where id = ?");
			stmt.setInt(1, id);
			  ResultSet rs = stmt.executeQuery();
			  while (rs.next()) {
			      cs = new CarroS();
			      cs.setId(rs.getInt("id"));
					 cs.setModelo(rs.getString("modelo"));
					  cs.setPreco(rs.getString("preco"));
					  cs.setMarca(rs.getString("marca"));
					  cs.setAno(rs.getInt("ano"));
			  }
			  stmt.close();
			  rs.close();
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados:" + ex);
			throw ex;
		}
		return cs;
	}

	
	public CarroS inserir(CarroS cs) throws Exception {
		String sqlInsert =
			"INSERT INTO CarroSemi (id, modelo,marca,ano,preco) VALUES ( ?,?,?,?,?)";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlInsert);
			ps.setInt(1, cs.getId());
			ps.setString(2, cs.getModelo());
			ps.setString(3, cs.getMarca());
			ps.setInt(4, cs.getAno());
			ps.setString(5, cs.getPreco())
			;
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Carro Não Foi Inserido"); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cs;
	}

	public CarroS excluir(CarroS cs) throws Exception {
		String sqlExcluir =
			"DELETE FROM CarroSemi where id = ?";
		try {
			Connection con = AppConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sqlExcluir);
			ps.setInt(1, cs.getId());
			int ret = ps.executeUpdate();
			if (ret != 1) {
				throw new Exception("Carro Não Encontrado!"); 
			}
		} catch (Exception ex) {
			System.err.println("Erro ao obter os dados" + ex);
			throw ex;
		}
		return cs;
	}


}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
	
public class Cliente_SQL {
	public List<Cliente> listAll() throws Exception {
			ArrayList<Cliente> list = new ArrayList<Cliente>();

			try {
				Connection con = AppConnection.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(
				          "SELECT cpf, nome,nascimento FROM Cliente");
				  while (rs.next()) {
				      Cliente clt = new Cliente();
					  clt.setCpf(rs.getString("cpf"));
					  clt.setNome(rs.getString("nome"));
					  clt.setNascimento(rs.getString("nascimento"));
					  list.add(clt);
				  }
				  stmt.close();
				  rs.close();
			} catch (Exception ex) {
				System.err.println("Erro ao obter os dados:" + ex);
				throw ex;
			}
			return list;
		}
		
		public Cliente getCliente(String cpf) throws Exception {
			Cliente cli = null;
			try {
				Connection con = AppConnection.getConnection();
				PreparedStatement stmt = con.prepareStatement(
						 "SELECT cpf,nome,nascimento FROM Cliente where cpf = ?");
				stmt.setString(1, cpf);
				  ResultSet rs = stmt.executeQuery();
				  while (rs.next()) {
				      cli = new Cliente();
				      cli.setCpf(rs.getString("cpf"));
						 cli.setNome(rs.getString("nome"));
						  cli.setNascimento(rs.getString("nascimento"));
						  
				  }
				  stmt.close();
				  rs.close();
			} catch (Exception ex) {
				System.err.println("Erro ao obter os dados:" + ex);
				throw ex;
			}
			return cli;
		}

		
		public Cliente inserirCliente(Cliente clt) throws Exception {
			String sqlInsert =
				"INSERT INTO Cliente (cpf, nome, nascimento) VALUES ( ?,?,?)";
			try {
				Connection con = AppConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlInsert);
				ps.setString(1, clt.getCpf());
				ps.setString(2, clt.getNome());
				ps.setString(3, clt.getNascimento());
				
				int ret = ps.executeUpdate();
				if (ret != 1) {
					throw new Exception("Cliente Não Foi Inserido"); 
				}
			} catch (Exception ex) {
				System.err.println("Erro ao obter os dados" + ex);
				throw ex;
			}
			return clt;
		}

		public Cliente excluirCliente(Cliente clt) throws Exception {
			String sqlExcluir =
				"DELETE FROM Cliente where cpf = ?";
			try {
				Connection con = AppConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sqlExcluir);
				ps.setString(1, clt.getCpf());
				int ret = ps.executeUpdate();
				if (ret != 1) {
					throw new Exception("Cliente Não Encontrado!"); 
				}
			} catch (Exception ex) {
				System.err.println("Erro ao obter os dados" + ex);
				throw ex;
			}
			return clt;
		}


	}

	
}

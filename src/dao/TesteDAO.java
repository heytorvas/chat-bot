package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Teste;

public class TesteDAO extends DAO<Teste>  {

	@Override
	public List<Teste> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			return null;
		}
		
		List<Teste> listaTeste = new ArrayList<Teste>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM infodocs");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Teste teste = new Teste();
				teste.setId(rs.getInt("id"));
				teste.setNome(rs.getString("nome"));
				teste.setTipo(rs.getInt("tipo"));
				teste.setCurso(rs.getInt("curso"));
				teste.setAno(rs.getString("ano"));
				teste.setOrientador(rs.getString("orientador"));
				teste.setOrientado(rs.getString("orientado"));

				listaTeste.add(teste);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			listaTeste = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaTeste;
	}

	@Override
	public boolean create(Teste obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(Teste obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Teste findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String acharArquivo(String message) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			return null;
		}
		System.out.println(message);

		String[] frase_array = message.split(" ");

		List<String> listaSinonimoOnde = new ArrayList<String>();

		listaSinonimoOnde.add("onde");
		listaSinonimoOnde.add("aonde");

		List<String> listaSinonimosArquivo = new ArrayList<String>();

		listaSinonimosArquivo.add("arquivo");
		listaSinonimosArquivo.add("documento");

		for (String keywordArquivo : frase_array) {
			if (listaSinonimosArquivo.contains(keywordArquivo) == true) {
				System.out.println("chave 1: " + keywordArquivo);

				for (String keywordOnde : frase_array) {
					if (listaSinonimoOnde.contains(keywordOnde) == true) {
						System.out.println("chave 2: " + keywordOnde);
						return message;
					}
				}
			}

		}

		System.out.println("erro");
		return null;
	}
}


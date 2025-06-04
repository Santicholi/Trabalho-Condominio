package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
    
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/CONDOMINIO?useSSL=false";
    private static final String USUARIO = "root";
    private static final String SENHA = "1234";
    private static Connection conexao;
    
    //ESTAMOS USANDO O PADRAO SINGLETON NESTA CLASSE
    //UTILIZAMOS O PADRAO SINGLETON PARA EVITAR QUE HAJA MULTIPLAS
    //CONEXOES SIMULTANEAS, POIS ESTE PROJETO EM ESPECIFICO PRECISA, APENAS, 
    //DE UMA CONEXAO (ESTA CLASSE JA SUPRI A NECESSIDADE)

    private ConexaoDAO() {}

    public static Connection getConnection() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
        }
        return conexao;
    }
    
    public static void main(String[] args) {
        try (Connection conn = ConexaoDAO.getConnection()) {
            if (conn != null) {
                System.out.println("Conexão com o MySQL estabelecida com sucesso!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco de dados:");
            e.printStackTrace();
        }
    }
}


package Model;

public class Morador {
    private int codigo_morador;
    private String nome;
    private int idade;
    private String rg;
    private String cpf;
    private int moraEm; // FK para residencia(numero_casa)

    public Morador() {}

    public Morador(int codigo_morador, String nome, int idade, String rg, String cpf, int moraEm) {
        this.codigo_morador = codigo_morador;
        this.nome = nome;
        this.idade = idade;
        this.rg = rg;
        this.cpf = cpf;
        this.moraEm = moraEm;
    }

    // Getters e Setters
    public int getCodigo_morador() { return codigo_morador; }
    public void setCodigo_morador(int codigo_morador) { this.codigo_morador = codigo_morador; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }
    
    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public int getMoraEm() { return moraEm; }
    public void setMoraEm(int moraEm) { this.moraEm = moraEm; }
}
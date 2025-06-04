package Model;

public class Residencia {
    private int numeroCasa;
    private String rua;
    private String cep;
    private int proprietario;

    public Residencia(){}
    
    public Residencia(int numeroCasa, String rua, String cep, int proprietario) {
        this.numeroCasa = numeroCasa;
        this.rua = rua;
        this.cep = cep;
        this.proprietario = proprietario; //referencia a morador
    }

    public int getNumeroCasa() { return numeroCasa; }
    public void setNumeroCasa(int numeroCasa) { this.numeroCasa = numeroCasa; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public int getProprietario() {  return proprietario;  }
    public void setProprietario(int proprietario) {  this.proprietario = proprietario;  }

    
}
import java.util.Map;

class Cliente {
    String nome;
    String cpf;
    String cnpj;
    String endereco;
    Map<String, Double> contas;

    public Cliente(String nome, String cpf, String cnpj, String endereco, Map<String, Double> contas) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.contas = contas;
    }
}

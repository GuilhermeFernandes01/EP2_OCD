public class Estrutura {
  private String endereco;
  private String conteudo;

  public Estrutura(String endereco, String conteudo) {
    this.endereco = endereco;
    this.conteudo = conteudo;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getConteudo() {
    return conteudo;
  }

  public void setConteudo(String conteudo) {
    this.conteudo = conteudo;
  }
}
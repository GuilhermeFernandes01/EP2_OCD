/*
  Classe que representa a estrutura das palavras, possui endereço e a palavra,
  além de getters e setters
*/

public class Estrutura {
  private String endereco;
  private String palavra;

  public Estrutura(String endereco, String palavra) {
    this.endereco = endereco;
    this.palavra = palavra;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getpalavra() {
    return palavra;
  }

  public void setpalavra(String palavra) {
    this.palavra = palavra;
  }
}
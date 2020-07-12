
public class Label_jmp {

  String nom_label;
  int linha;
  int linha_jump;


  Label_jmp(String n, int l, int j) {
    this.nom_label = n;
    this.linha = l;
    this.linha_jump = j;
  }

  public String get_label() {
    return this.nom_label;
  }

  public int get_label_linha() {
    return this.linha;
  }

  public int get_label_linha_jump() {
    return this.linha_jump;
  }

  public void set_label(String n) {
    this.nom_label = n;
  }

  public void set_label_linha(int n) {
    this.linha = n;
  }

  public void set_label_linha_jump(int n) {
    this.linha_jump = n;
  }
}

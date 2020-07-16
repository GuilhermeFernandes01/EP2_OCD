import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Janela extends JFrame {

    private JPanel contentPane;
    static JTextField txt_s1;
    private static JTextField txt_s2;
    private static JTextField txt_s3;
    static JTextField txt_s4;
    private static JButton btn_rodar;
    private static JButton btn_reiniciar;
    private static JButton btn_limpar;
    private JTextArea txt_cod;
    private JTextArea txt_maquina;
    private JTextArea txt_ciclos;
    

    private static final long serialVersionUID = 42L;

    public int i = 0;
    Operacoes op = new Operacoes();
    Ciclos ciclos = new Ciclos();
    private static JTextField txt_s;
    private static JTextField txt_z;
    // ArrayList<Label_jmp> array_label = new ArrayList<Label_jmp>();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Janela janela = new Janela();
                    janela.setVisible(true);
                    txt_s1.setText("0000");
                    txt_s2.setText("0000");
                    txt_s3.setText("0000");
                    txt_s4.setText("0000");
                    txt_z.setText("");
                    txt_s.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int volta_linha_array(String l, int linha_jump) {
        int retorno_linha = -1;

        // for (Label_jmp s : array_label) {
        //     if (l.equals(s.get_label())) {
        //         retorno_linha = s.get_label_linha();

        //         /// procura no array a linha de acordo com a label do jnz
        //         s.set_label_linha_jump(linha_jump);

        //     }
        // }
        return retorno_linha;
    }

    public static String add_zero(String l) {
        String aux = "";

        if (l.length() < 4) {
            for (int a = 0; a < (4 - l.length()); a++) {
                aux = aux + "0";
            }
            aux = aux + l;
        } else {
            aux = l;
        }

        return l;
    }

    /* M�todo que retorna uma String a partir de uma ArrayList */
    public String retornaString(ArrayList<String> aux) {
        String x = "";
        //int y = 0;

        for (int b = 0; b < aux.size(); b++) {
            x += aux.get(b);
        }

        return x;
    }

    public void limpar() {
        // zera o contador das linhas
        i = 0;
        // zera o array dos jmps das labels
        // array_label.clear();
        // zera todos os campos de texto
        txt_s1.setText("0000");
        txt_s2.setText("0000");
        txt_s3.setText("0000");
        txt_s4.setText("0000");
        txt_z.setText("");
        txt_s.setText("");

        // zera todas as variaveis
        op.setS1(0);
        op.setS2(0);
        op.setS3(0);
        op.setS4(0);
        // op.setS("");
        // op.setZ("");
        txt_ciclos.setText("");
        txt_maquina.setText("");
    }

    public String verificaVariavel(String variavel) {
        switch (variavel) {
            case "$s1":
                return "00";
            case "$s2":
                return "01";
            case "$s3":
                return "10";
            case "$s4":
                return "11";
            default:
                return "";
        }
    }

    /**
     * Create the frame.
     */
    public Janela() {
        setTitle("Interpretador de Assembly ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 605);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbloperacoes = new JLabel("Digite as opera\u00E7\u00F5es em Assembly:");
        lbloperacoes.setBounds(10, 11, 217, 14);
        contentPane.add(lbloperacoes);

        JLabel lblmaquina = new JLabel("Convertido para linguagem de m\u00e1quina:");
        lblmaquina.setBounds(262, 11, 230, 14);
        contentPane.add(lblmaquina);

        JLabel lblciclos = new JLabel("Ciclo de execu\u00e7\u00e3o:");
        lblciclos.setBounds(700, 220, 210, 14);
        contentPane.add(lblciclos);

        btn_rodar = new JButton("Rodar");
        btn_rodar.setBounds(330, 505, 89, 23);

        btn_reiniciar = new JButton("Reiniciar");
        btn_reiniciar.setBounds(230, 515, 89, 23);
        btn_limpar = new JButton("Limpar");
        btn_limpar.setBounds(430, 515, 89, 23);

        btn_reiniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                limpar();
                btn_rodar.setEnabled(true);

            }
        });


        btn_limpar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                limpar();

                txt_cod.setText("");
                btn_rodar.setEnabled(true);
            }
        });

        btn_rodar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {


                String[] linhas = txt_cod.getText().split("\n");//Cada posi��o do array = 1 linha
                List<Estrutura> list = new LinkedList<>();
                Estrutura test = new Estrutura("test", "test");
                list.add(test);

                if (linhas[0].equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o c\u00f3digo");
                    return;
                }

                ArrayList<String> ins = new ArrayList<String>();//ArrayList da instru��o
                ArrayList<String> var = new ArrayList<String>();//ArrayList das vari�veis
                ArrayList<String> var2 = new ArrayList<String>();
                ArrayList<String> var3 = new ArrayList<String>();
                String insS, varS, var2S, var3S;
                int result = 0;

                String aux = linhas[i];//i = contador de cliques no bot�o, ou seja, executa a linha em quest�o
                ArrayList<String> txt = new ArrayList<String>();
                for (int j = 0; j < aux.length(); j++) {//Cria arraylist dos caracteres da linha
                    char x = aux.charAt(j);
                    txt.add(String.valueOf(x));
                }
                
                String[] str = new String[txt.size()];
                txt.toArray(str);
                
                int encontrou = 0;
                for (int i = 0; i < str.length; i++) {
                    if (encontrou == 0 && !str[i].equals(" ")) {
                        ins.add(str[i]);
                    } else if (encontrou == 1 && !str[i].equals(",")) {
                        var.add(str[i]);
                    } else if (encontrou == 3 && !str[i].equals(",")) {
                        var2.add(str[i]);
                    } else if (encontrou == 5) {
                        var3.add(str[i]);
                    } else if (str[i].equals(" ") || str[i].equals(",")) {
                        encontrou++;
                    }
                }            

                insS = retornaString(ins);
                varS = retornaString(var);
                var2S = retornaString(var2);
                var3S = retornaString(var3);

                // System.out.println(insS);
                // System.out.println(varS);
                // System.out.println(var2S);
                // System.out.println(var3S);                

                // Instruções
                if (insS.equals("add")) {
                    result = op.add(varS, var2S, var3S);
                } else if (insS.equals("sub")) {
                    result = op.sub(varS, var2S, var3S);
                } else if (insS.equals("li")) {
                    result = op.li(varS, var2S);
                } else if (insS.equals("mov")) {
                    result = op.mov(varS, var2S);
                } else if (insS.equals("beq")) {
                    result = op.beq(varS, var2S, var3S);
                    i = result;
                } else if (insS.equals("bne")) {
                    result = op.bne(varS, var2S, var3S);
                    i = result;
                } else if (insS.equals("slt")) {
                    result = op.slt(varS, var2S, var3S);
                } else if (insS.equals("jmp")) {
                    // jump pra pular a linha ate achar a label
                    // String label = varS;

                    for (int a = i; a < linhas.length; a++) {
                        if (linhas[a].contains(varS) == true) {
                            i = a;
                            System.out.println(linhas[i + 1]);
                        }

                    }
                    txt_ciclos.setText("");
                } else if (insS.equals("jnz")) {
                    // if (op.z != "�") {
                    //     // sempre verificar se na linha anterior o ins era um cmp 
                    //     // linha da label que esta depois do jnz 

                    //     // buscar a linha no array e setar 
                    //     // volta pra linha da label sempre para continuar at� a flag ser 0 
                    //     i = volta_linha_array(varS, i);
                    // }
                    txt_ciclos.setText("");
                } else if (insS.equals("jz")) {
                    // if (op.z == "�") {

                    //     String label = varS;

                    //     for (int a = i; a < linhas.length; a++) {
                    //         if (linhas[a].contains(varS) == true) {
                    //             i = a;
                    //             System.out.println(linhas[i + 1]);
                    //         }
                    //     }
                    // }
                    txt_ciclos.setText("");
                } else if (insS.equals("jl")) {
                    // if (op.s == "-") {

                    //     i = volta_linha_array(varS, i);
                    // }
                    txt_ciclos.setText("");
                } else if (insS.equals("jh")) {
                    // if (op.s == "+" && op.z != "�") {

                    //     i = volta_linha_array(varS, i);
                    // }
                    txt_ciclos.setText("");
                } else if (insS.equals("je")) {
                    // if (op.z == "�") {
                    //     String label = varS;

                    //     for (int a = i; a < linhas.length; a++) {
                    //         if (linhas[a].contains(varS) == true) {
                    //             i = a;
                    //             System.out.println(linhas[i + 1]);
                    //         }
                    //     }
                    // }
                    txt_ciclos.setText("");
                } else if (insS.equals("jne")) {
                    // if (op.z != "�") {

                    //     i = volta_linha_array(varS, i);
                    // }
                    txt_ciclos.setText("");
                } else {
                    // � uma label 
                    // Se for uma label, colocar ela em uma posi��o de array de objetos label

                    // Label_jmp label_nova = new Label_jmp(insS, i, 0);
                    // // Insere em um array list o nome da label e a sua linha 
                    // if (array_label.contains(label_nova.get_label())) {

                    // } else {
                    //     array_label.add(label_nova);

                    //     System.out.print(array_label.get(0).get_label());
                    //     System.out.print(array_label.get(0).get_label_linha());
                    // }

                }

                

                String traduzido = op.traduz(insS, varS, var2S, var3S);

                int proximaLinha = i + 1;

                String opCode = traduzido.split(" ")[1];                

                String palavraReg1 = "", palavraReg2 = "", palavraReg3 = "";
                
                palavraReg1 = verificaVariavel(varS);
                palavraReg2 = verificaVariavel(var2S);
                palavraReg3 = verificaVariavel(var3S);

                int numeroReg2 = 0, numeroReg3 = 0;

                try {
                    numeroReg2 = Integer.parseInt(var2S);
                    palavraReg2 = Integer.toBinaryString(numeroReg2);
                } catch (NumberFormatException e) {
                }

                try {
                    numeroReg3 = Integer.parseInt(var3S);
                    palavraReg3 = Integer.toBinaryString(numeroReg3);
                } catch (NumberFormatException e) {
                }

                String palavraSemEspaco = "";
                if (palavraReg3.equals("")) {
                    palavraSemEspaco = opCode + palavraReg1;
                } else {
                    palavraSemEspaco = opCode + palavraReg1 + palavraReg2 + palavraReg3;
                }

                txt_s.setText("");
                txt_z.setText("");

                int totalZerosPalavra = 32 - palavraSemEspaco.length();
                String zeros = new String(new char[32 - totalZerosPalavra - palavraSemEspaco.length()]).replace("\0", "0");
                if (numeroReg2 > Math.pow(2, totalZerosPalavra) - 1
                    || numeroReg3 > Math.pow(2, totalZerosPalavra) - 1) {
                    JOptionPane.showMessageDialog(null, "Numero maior que a quantidade de bits disponivel");
                    return;
                } else {
                    txt_maquina.append(traduzido);

                    txt_ciclos.setText(Ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao(insS, varS, var2S, var3S));

                    txt_s1.setText("" + add_zero(Integer.toString(op.s1)));

                    txt_s2.setText("" + add_zero(Integer.toString(op.s2)));

                    txt_s3.setText("" + add_zero(Integer.toString(op.s3)));

                    txt_s4.setText("" + add_zero(Integer.toString(op.s4)));

                    if (result < 0) {
                        txt_s.setText("1");
                    } else if (result > 0 && !insS.equals("beq")) {
                        txt_s.setText("0");
                    } else {
                        txt_z.setText("1");
                    }
                }
                
                String palavra = "";
                if (palavraReg3.equals("")) {
                    palavra = opCode + " " + palavraReg1 + " " + palavraReg2 + palavraReg3 + " " + zeros;
                } else {
                    palavra = opCode + " " + palavraReg1 + " " + palavraReg2 + " " + palavraReg3 + " " + zeros;
                }
                
                System.out.println("palavra: " + palavra);

                Estrutura elemento = new Estrutura(palavra, proximaLinha + "");

                list.add(elemento);

                System.out.println(elemento.getpalavra());
                System.out.println(elemento.getEndereco());
                
                // txt_z.setText("" + op.z);

                i++;//Pr�xima linha

                if (i >= txt_cod.getLineCount()) {
                    JOptionPane.showMessageDialog(null, "Fim!\n Aperte em limpar para digitar um novo c\u00f3digo ou reiniciar para ler o c\u00f3digo novamente!");
                    btn_rodar.setEnabled(false);
                }
            }
        });
        contentPane.add(btn_rodar);
        contentPane.add(btn_reiniciar);
        contentPane.add(btn_limpar);

        JLabel lblDec = new JLabel("DEC");
        lblDec.setBounds(810, 21, 46, 14);
        contentPane.add(lblDec);

        JLabel lbl_s1 = new JLabel("s1");
        lbl_s1.setBounds(770, 40, 46, 14);
        contentPane.add(lbl_s1);

        txt_s1 = new JTextField();
        txt_s1.setBounds(800, 38, 46, 20);
        contentPane.add(txt_s1);
        txt_s1.setColumns(10);

        JLabel lbl_s2 = new JLabel("s2");
        lbl_s2.setBounds(770, 70, 46, 14);
        contentPane.add(lbl_s2);

        txt_s2 = new JTextField();
        txt_s2.setBounds(800, 68, 46, 20);
        contentPane.add(txt_s2);
        txt_s2.setColumns(10);

        JLabel lbl_s3 = new JLabel("s3");
        lbl_s3.setBounds(770, 103, 46, 14);
        contentPane.add(lbl_s3);

        txt_s3 = new JTextField();
        txt_s3.setBounds(800, 101, 46, 20);
        contentPane.add(txt_s3);
        txt_s3.setColumns(10);

        JLabel lbl_s4 = new JLabel("s4");
        lbl_s4.setBounds(770, 135, 46, 14);
        contentPane.add(lbl_s4);

        txt_s4 = new JTextField();
        txt_s4.setBounds(800, 132, 46, 20);
        contentPane.add(txt_s4);
        txt_s4.setColumns(10);        

        JLabel lblFlags = new JLabel("Flags");
        lblFlags.setBounds(805, 163, 46, 14);
        contentPane.add(lblFlags);

        JLabel lblS = new JLabel("S");
        lblS.setBounds(780, 180, 25, 14);
        contentPane.add(lblS);

        txt_s = new JTextField();
        txt_s.setBounds(775, 196, 25, 20);
        contentPane.add(txt_s);
        txt_s.setColumns(10);        

        JLabel lblZ = new JLabel("Z");
        lblZ.setBounds(848, 180, 19, 14);
        contentPane.add(lblZ);

        txt_z = new JTextField();
        txt_z.setBounds(840, 196, 25, 20);
        contentPane.add(txt_z);
        txt_z.setColumns(10);

        txt_cod = new JTextArea();
        txt_cod.setBounds(10, 36, 240, 419);
        contentPane.add(txt_cod);

        txt_maquina = new JTextArea();
        txt_maquina.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_maquina.setBounds(261, 36, 417, 419);
        contentPane.add(txt_maquina);

        txt_ciclos = new JTextArea();
        txt_ciclos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_ciclos.setBounds(700, 236, 217, 219);
        contentPane.add(txt_ciclos);
    }
}

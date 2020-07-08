import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Janela extends JFrame {

    private JPanel contentPane;
    private static JTextField txt_s1;
    private static JTextField txt_s2;
    private static JTextField txt_s3;
    private static JTextField txt_s4;
    static JTextField txt_s1dec;
    private static JTextField txt_s2dec;
    private static JTextField txt_s3dec;
    static JTextField txt_s4dec;
    private static JButton btn_rodar;
    private static JButton btn_reiniciar;
    private static JButton btn_limpar;
    private JTextArea txt_cod;
    private JTextArea txt_maquina;
    private JTextArea txt_ciclos;

    public int i = 0;
    // Operacoes op = new Operacoes();
    // Ciclos ciclos = new Ciclos();
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
                    txt_s1dec.setText("0000");
                    txt_s2dec.setText("0000");
                    txt_s3dec.setText("0000");
                    txt_s4dec.setText("0000");
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

    /* M�todoo que cria arrayslists da instru��o e das vari�veis*/
    @SuppressWarnings("unchecked")
    public static void dividirEmArraysL(ArrayList txt, ArrayList ins, ArrayList var, ArrayList var2) {
        for (int u = 0; u < txt.size(); u++) {
            if (txt.get(u).equals(" ")) {
                u++;
                while (u < txt.size()) {
                    if (txt.get(u).equals(",")) {
                        u++;
                        while (u < txt.size()) {
                            var2.add(txt.get(u));
                            u++;
                        }
                    } else {
                        var.add(txt.get(u));
                    }
                    u++;
                }

            } else {
                ins.add(txt.get(u));

            }
        }

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
        int y = 0;

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
        txt_s1dec.setText("0000");
        txt_s2dec.setText("0000");
        txt_s3dec.setText("0000");
        txt_s4dec.setText("0000");
        txt_s1.setText("0000");
        txt_s2.setText("0000");
        txt_s3.setText("0000");
        txt_s4.setText("0000");
        txt_z.setText("");
        txt_s.setText("");

        // zera todas as variaveis
        // op.setAx(0);
        // op.setBx(0);
        // op.setCx(0);
        // op.setDx(0);
        // op.setS("");
        // op.setZ("");
        txt_ciclos.setText("");
        txt_maquina.setText("");
    }

    /**
     * Create the frame.
     */
    public Janela() {
        setTitle("Interpretador de Assembly ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 605);
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
        lblciclos.setBounds(500, 220, 210, 14);
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

                if (linhas[0].equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o c\u00f3digo");
                    return;
                }

                ArrayList<String> ins = new ArrayList<String>();//ArrayList da instru��o
                ArrayList<String> var = new ArrayList<String>();//ArrayList das vari�veis
                ArrayList<String> var2 = new ArrayList<String>();
                String insS, varS, var2S, resultado;
                int result = 0;

                String aux = linhas[i];//i = contador de cliques no bot�o, ou seja, executa a linha em quest�o
                ArrayList<String> txt = new ArrayList<String>();
                for (int j = 0; j < aux.length(); j++) {//Cria arraylist dos caracteres da linha
                    char x = aux.charAt(j);
                    txt.add(String.valueOf(x));
                }

                dividirEmArraysL(txt, ins, var, var2);

                insS = retornaString(ins);
                varS = retornaString(var);
                var2S = retornaString(var2);

                System.out.println(insS);
                System.out.println(varS);
                System.out.println(var2S);

                if (insS.equals("add")) {//Comparar instru��es
                    // result = op.add(varS, var2S);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("add", varS, var2S));
                } else if (insS.equals("div")) {
                    // result = op.div(varS, var2S);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("div", varS, var2S));
                } else if (insS.equals("sub")) {
                    // result = op.sub(varS, var2S);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("sub", varS, var2S));
                } else if (insS.equals("mul")) {
                    // result = op.mul(varS, var2S);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("mul", varS, var2S));
                } else if (insS.equals("mov")) {
                    // result = op.mov(varS, var2S);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("mov", varS, var2S));
                } else if (insS.equals("inc")) {
                    // result = op.inc(varS);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("inc", varS, var2S));
                } else if (insS.equals("dec")) {
                    // result = op.dec(varS);
                    // txt_ciclos.setText(ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao("dec", varS, var2S));
                } else if (insS.equals("cmp")) {
                    // resultado = op.cmp(varS, var2S);
                    txt_ciclos.setText("");
                } else if (insS.equals("jmp")) {
                    // jump pra pular a linha ate achar a label
                    String label = varS;

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

                // txt_s1dec.setText("" + add_zero(Integer.toString(op.ax)));
                // txt_s1.setText("" + add_zero(Integer.toHexString(op.ax)));

                // txt_s2dec.setText("" + add_zero(Integer.toString(op.bx)));
                // txt_s2.setText("" + add_zero(Integer.toHexString(op.bx)));

                // txt_s3dec.setText("" + add_zero(Integer.toString(op.cx)));
                // txt_s3.setText("" + add_zero(Integer.toHexString(op.cx)));

                // txt_s4dec.setText("" + add_zero(Integer.toString(op.dx)));
                // txt_s4.setText("" + add_zero(Integer.toHexString(op.dx)));

                // txt_s.setText("" + op.s);
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

        JLabel lblHex = new JLabel("HEX");
        lblHex.setBounds(535, 21, 46, 14);
        contentPane.add(lblHex);

        JLabel lblS1 = new JLabel("S1:");
        lblS1.setBounds(500, 41, 19, 14);
        contentPane.add(lblS1);

        txt_s1 = new JTextField();
        txt_s1.setBounds(529, 38, 46, 20);
        contentPane.add(txt_s1);
        txt_s1.setColumns(10);

        JLabel lblS2 = new JLabel("S2:");
        lblS2.setBounds(500, 71, 19, 14);
        contentPane.add(lblS2);

        txt_s2 = new JTextField();
        txt_s2.setBounds(529, 68, 46, 20);
        contentPane.add(txt_s2);
        txt_s2.setColumns(10);

        JLabel lblS3 = new JLabel("S3:");
        lblS3.setBounds(500, 104, 19, 14);
        contentPane.add(lblS3); 

        txt_s3 = new JTextField();
        txt_s3.setBounds(529, 101, 46, 20);
        contentPane.add(txt_s3);
        txt_s3.setColumns(10);

        JLabel lblS4 = new JLabel("S4:");
        lblS4.setBounds(500, 135, 19, 14);
        contentPane.add(lblS4);

        txt_s4 = new JTextField();
        txt_s4.setBounds(529, 132, 46, 20);
        contentPane.add(txt_s4);
        txt_s4.setColumns(10);        

        JLabel lblDec = new JLabel("DEC");
        lblDec.setBounds(610, 21, 46, 14);
        contentPane.add(lblDec);

        txt_s1dec = new JTextField();
        txt_s1dec.setBounds(600, 38, 46, 20);
        contentPane.add(txt_s1dec);
        txt_s1dec.setColumns(10);

        txt_s2dec = new JTextField();
        txt_s2dec.setBounds(600, 68, 46, 20);
        contentPane.add(txt_s2dec);
        txt_s2dec.setColumns(10);

        txt_s3dec = new JTextField();
        txt_s3dec.setBounds(600, 101, 46, 20);
        contentPane.add(txt_s3dec);
        txt_s3dec.setColumns(10);

        txt_s4dec = new JTextField();
        txt_s4dec.setBounds(600, 132, 46, 20);
        contentPane.add(txt_s4dec);
        txt_s4dec.setColumns(10);        

        JLabel lblFlags = new JLabel("Flags");
        lblFlags.setBounds(570, 163, 46, 14);
        contentPane.add(lblFlags);

        JLabel lblS = new JLabel("S");
        lblS.setBounds(557, 180, 25, 14);
        contentPane.add(lblS);

        txt_s = new JTextField();
        txt_s.setBounds(550, 196, 25, 20);
        contentPane.add(txt_s);
        txt_s.setColumns(10);        

        JLabel lblZ = new JLabel("Z");
        lblZ.setBounds(608, 180, 19, 14);
        contentPane.add(lblZ);

        txt_z = new JTextField();
        txt_z.setBounds(600, 196, 25, 20);
        contentPane.add(txt_z);
        txt_z.setColumns(10);

        txt_cod = new JTextArea();
        txt_cod.setBounds(10, 36, 240, 419);
        contentPane.add(txt_cod);

        txt_maquina = new JTextArea();
        txt_maquina.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_maquina.setBounds(261, 36, 217, 419);
        contentPane.add(txt_maquina);

        txt_ciclos = new JTextArea();
        txt_ciclos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_ciclos.setBounds(500, 236, 217, 219);
        contentPane.add(txt_ciclos);
    }
}

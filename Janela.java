import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Janela extends JFrame {

    private static JPanel contentPane;

    private static JTextField txt_s1;
    private static JTextField txt_s2;
    private static JTextField txt_s3;
    private static JTextField txt_s4;
    private static JTextField txt_s;
    private static JTextField txt_z;
    private static JTextArea txt_cod;
    private static JTextArea txt_maquina;
    private static JTextArea txt_ciclos;

    private static JButton btn_rodar;
    private static JButton btn_reiniciar;
    private static JButton btn_limpar;

    private static final long serialVersionUID = 42L;

    public int i = 0;
    Operacoes op = new Operacoes();
    Ciclos ciclos = new Ciclos();
    Traducao traducao = new Traducao();

    // Roda a aplicação
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Janela janela = new Janela();
                    janela.setVisible(true);
                    txt_s1.setText("0");
                    txt_s2.setText("0");
                    txt_s3.setText("0");
                    txt_s4.setText("0");
                    txt_z.setText("");
                    txt_s.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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

    // Retorna uma String a partir de uma ArrayList */
    public String retornaString(ArrayList<String> aux) {
        String x = "";

        for (int b = 0; b < aux.size(); b++) {
            x += aux.get(b);
        }

        return x;
    }

    public void limpar() {
        // Zera o contador das linhas
        i = 0;

        // Zera todos os campos de texto
        txt_s1.setText("0");
        txt_s2.setText("0");
        txt_s3.setText("0");
        txt_s4.setText("0");
        txt_z.setText("");
        txt_s.setText("");

        // Zera todas as variáveis
        op.setS1(0);
        op.setS2(0);
        op.setS3(0);
        op.setS4(0);
        txt_ciclos.setText("");
        txt_maquina.setText("");
    }

    // Retorna o opcode da variável
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

    // Cria o Frame
    public Janela() {
        // Configurações da Janela
        setTitle("Interpretador de Assembly");
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

                // Cada posição do array representa uma linha
                String[] linhas = txt_cod.getText().split("\n");

                // Lista ligada da estrutura das palavras
                List<Estrutura> estruturaPalavras = new LinkedList<>();

                // ArrayList da estrutura dos ciclos
                List<String> estruturaCiclos = new ArrayList<>();

                // Valida se não houve entrada
                if (linhas[0].equals("")) {
                    JOptionPane.showMessageDialog(null, "Digite o c\u00f3digo");
                    return;
                }

                // Operação
                ArrayList<String> listOperacao = new ArrayList<String>();

                // ArrayList do primeiro registrador
                ArrayList<String> reg1List = new ArrayList<String>();

                // ArrayList do segundo registrador ou valor
                ArrayList<String> reg2List = new ArrayList<String>();

                // ArrayList do terceiro registrador ou valor
                ArrayList<String> reg3List = new ArrayList<String>();
                String operacao, reg1, reg2, reg3;
                int result = 0;

                // i é o contador de cliques no botão
                String aux = linhas[i];
                ArrayList<String> texto = new ArrayList<String>();

                // ArrayList dos caracteres da linha
                for (int j = 0; j < aux.length(); j++) {
                    char x = aux.charAt(j);
                    texto.add(String.valueOf(x));
                }

                String[] linha = new String[texto.size()];
                texto.toArray(linha);

                // Separa a linha nas variáveis que precisamos
                int encontrou = 0;
                for (int i = 0; i < linha.length; i++) {
                    if (encontrou == 0 && !linha[i].equals(" ")) {
                        listOperacao.add(linha[i]);
                    } else if (encontrou == 1 && !linha[i].equals(",")) {
                        reg1List.add(linha[i]);
                    } else if (encontrou == 3 && !linha[i].equals(",")) {
                        reg2List.add(linha[i]);
                    } else if (encontrou == 5) {
                        reg3List.add(linha[i]);
                    } else if (linha[i].equals(" ") || linha[i].equals(",")) {
                        encontrou++;
                    }
                }

                // Torna em String as listas que tínhamos
                operacao = retornaString(listOperacao);
                reg1 = retornaString(reg1List);
                reg2 = retornaString(reg2List);
                reg3 = retornaString(reg3List);

                // Instruções
                if (operacao.equals("add")) {
                    result = op.add(reg1, reg2, reg3);
                } else if (operacao.equals("sub")) {
                    result = op.sub(reg1, reg2, reg3);
                } else if (operacao.equals("li")) {
                    result = op.li(reg1, reg2);
                } else if (operacao.equals("move")) {
                    result = op.move(reg1, reg2);
                } else if (operacao.equals("beq")) {
                    result = op.beq(reg1, reg2, reg3);
                    i = result;
                } else if (operacao.equals("bne")) {
                    result = op.bne(reg1, reg2, reg3);
                    i = result;
                } else if (operacao.equals("slt")) {
                    result = op.slt(reg1, reg2, reg3);
                } else if (operacao.equals("j")) {
                    i = Integer.parseInt(reg1) - 2;
                }

                // Traduz para linguagem de máquina a operação
                String traduzido = traducao.traduz(operacao, reg1, reg2, reg3);

                // Representa a próxima linha
                int proximaLinha = i + 1;

                // Pega o opcode
                String opCode = traduzido.split(" ")[1];

                String palavraReg1 = "", palavraReg2 = "", palavraReg3 = "";

                // Verifica a qual registrador a variável aponta
                palavraReg1 = verificaVariavel(reg1);
                palavraReg2 = verificaVariavel(reg2);
                palavraReg3 = verificaVariavel(reg3);

                int numeroReg2 = 0, numeroReg3 = 0;

                // Verifica se é um valor ou registrador
                try {
                    numeroReg2 = Integer.parseInt(reg2);
                    palavraReg2 = Integer.toBinaryString(numeroReg2);
                } catch (NumberFormatException e) {
                }

                try {
                    numeroReg3 = Integer.parseInt(reg3);
                    palavraReg3 = Integer.toBinaryString(numeroReg3);
                } catch (NumberFormatException e) {
                }

                // Formata a String
                String palavraSemEspaco = "";
                if (palavraReg3.equals("")) {
                    palavraSemEspaco = opCode + palavraReg1;
                } else {
                    palavraSemEspaco = opCode + palavraReg1 + palavraReg2 + palavraReg3;
                }

                txt_s.setText("");
                txt_z.setText("");

                // Valida o tamanho máximo de valores
                int totalZerosPalavra = 32 - palavraSemEspaco.length();
                String zeros = new String(new char[32 - totalZerosPalavra - palavraSemEspaco.length()]).replace("\0",
                        "0");

                if (numeroReg2 > Math.pow(2, totalZerosPalavra) - 1
                        || numeroReg3 > Math.pow(2, totalZerosPalavra) - 1) {
                    JOptionPane.showMessageDialog(null, "N\u00famero maior que a quantidade de bits dispon\u00edvel");
                    return;
                } else {
                    // Coloca nos respectivos campos seus respectivos valores
                    txt_maquina.append(traduzido);

                    txt_ciclos
                            .setText(Ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao(operacao, reg1, reg2, reg3));

                    txt_s1.setText("" + add_zero(Integer.toString(op.s1)));

                    txt_s2.setText("" + add_zero(Integer.toString(op.s2)));

                    txt_s3.setText("" + add_zero(Integer.toString(op.s3)));

                    txt_s4.setText("" + add_zero(Integer.toString(op.s4)));

                    if (result < 0) {
                        txt_s.setText("1");
                    } else if (result > 0 && !operacao.equals("beq")) {
                        txt_s.setText("0");
                    } else {
                        txt_z.setText("1");
                    }
                }

                // Formata String
                String palavra = "";
                if (palavraReg3.equals("")) {
                    palavra = opCode + " " + palavraReg1 + " " + palavraReg2 + palavraReg3 + " " + zeros;
                } else {
                    palavra = opCode + " " + palavraReg1 + " " + palavraReg2 + " " + palavraReg3 + " " + zeros;
                }

                // Cria o elemento da estrutura das palavras e o adiciona a sua respectiva lista
                Estrutura elemento = new Estrutura(palavra, proximaLinha + "");
                estruturaPalavras.add(elemento);

                // Cria o elemento da estrutura das ciclos e o adiciona a sua respectiva lista
                String ciclo = Ciclos.CicloDeBusca() + "\n" + ciclos.CicloDeExecucao(operacao, reg1, reg2, reg3);
                estruturaCiclos.add(ciclo);

                // Próxima linha
                i++;

                if (i >= txt_cod.getLineCount()) {
                    JOptionPane.showMessageDialog(null,
                            "Fim!\n Aperte em limpar para digitar um novo c\u00f3digo ou reiniciar para ler o c\u00f3digo novamente!");
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
        txt_s1.setEditable(false);

        JLabel lbl_s2 = new JLabel("s2");
        lbl_s2.setBounds(770, 70, 46, 14);
        contentPane.add(lbl_s2);

        txt_s2 = new JTextField();
        txt_s2.setBounds(800, 68, 46, 20);
        contentPane.add(txt_s2);
        txt_s2.setColumns(10);
        txt_s2.setEditable(false);

        JLabel lbl_s3 = new JLabel("s3");
        lbl_s3.setBounds(770, 103, 46, 14);
        contentPane.add(lbl_s3);

        txt_s3 = new JTextField();
        txt_s3.setBounds(800, 101, 46, 20);
        contentPane.add(txt_s3);
        txt_s3.setColumns(10);
        txt_s3.setEditable(false);

        JLabel lbl_s4 = new JLabel("s4");
        lbl_s4.setBounds(770, 135, 46, 14);
        contentPane.add(lbl_s4);

        txt_s4 = new JTextField();
        txt_s4.setBounds(800, 132, 46, 20);
        contentPane.add(txt_s4);
        txt_s4.setColumns(10);
        txt_s4.setEditable(false);

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
        txt_s.setEditable(false);

        JLabel lblZ = new JLabel("Z");
        lblZ.setBounds(848, 180, 19, 14);
        contentPane.add(lblZ);

        txt_z = new JTextField();
        txt_z.setBounds(840, 196, 25, 20);
        contentPane.add(txt_z);
        txt_z.setColumns(10);
        txt_z.setEditable(false);

        txt_cod = new JTextArea();
        txt_cod.setBounds(10, 36, 240, 419);
        contentPane.add(txt_cod);

        txt_maquina = new JTextArea();
        txt_maquina.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_maquina.setBounds(261, 36, 417, 419);
        txt_maquina.setEditable(false);
        contentPane.add(txt_maquina);

        txt_ciclos = new JTextArea();
        txt_ciclos.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txt_ciclos.setBounds(700, 236, 217, 219);
        txt_ciclos.setEditable(false);
        contentPane.add(txt_ciclos);
    }
}

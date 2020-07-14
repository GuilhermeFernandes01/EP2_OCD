public class Ciclos {

    private boolean ehNumero;

    public boolean testaNumero(String testado) {
        try {
            Integer.parseInt(testado);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    //Ciclo de Busca � igual em todos
    public static String CicloDeBusca() {
        return ("CICLO DE BUSCA:\n"
                + "T1:MAR <- PC \n"
                + "T2:MBR <- memoria \n"
                + "T3:ULA <- PC \n"
                + "T4:AC <- ULA \n"
                + "T5:PC <- AC \n"
                + "T6:IR <- MBR\n");
    }
  
    //Indirecao de reg1
    public static String CicloDeIndirecao1(String p) {
        String j = p;
        j = p.replace("[", " ");
        j = p.replace("]", " ");
        return ("CICLO DE INDIRECAO:\n"
                + "T1:MAR <-" + p.toUpperCase() + "\n"
                + "T2:MBR <- memoria \n"
                + "T3:P1(" + p.toUpperCase() + ") <- MBR\n");
        //System.out.println("P1: " + p.toUpperCase());
        //return p;
    }
  
    //Indirecao de reg2
    public static String CicloDeIndirecao2(String p) {
        String j = p;
        j = p.replace("[", " ");
        j = p.replace("]", " ");
        return ("CICLO DE INDIRECAO:\n"
                + "T1:MAR <-" + p.toUpperCase() + "\n"
                + "T2:MBR <- memoria \n"
                + "T3:P1(" + p.toUpperCase() + ") <- MBR\n");
        //System.out.println("P1: " + p.toUpperCase());
        //return p;
    }
  
    //Ciclo de execucao, que � especifico de cada operacao
    public String CicloDeExecucao(String operacao, String reg1, String reg2, String reg3) {
        switch (operacao) {
            case "add":
                ehNumero = testaNumero(reg3);
                if (!ehNumero) {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                    + "T1:X <- " + reg2 + "\n"
                    + "T2:ULA <- " + reg3 + "\n"
                    + " AC <- ULA(" + reg2 + " + " + reg3 + ")\n"
                    + "T3:" + reg1 + " <- AC \n");
                } else {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                    + "T1:X <- " + reg2 + "\n"
                    + "T2:ULA <- " + reg3 + "(P1)" + "\n"
                    + " AC <- ULA(" + reg2 + " + " + reg3 + ")\n"
                    + "T3:" + reg1 + " <- AC \n");
                }
            case "sub":
                ehNumero = testaNumero(reg3);
                if (!ehNumero) {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + reg2 + "\n"
                        + "T2:ULA <- " + reg3 + "\n"
                        + " AC <- ULA(" + reg2 + " - " + reg3 + ")\n"
                        + "T3:" + reg1 + " <- AC \n");
                } else {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + reg2 + "\n"
                        + "T2:ULA <- " + reg3 + "(P1)\n"
                        + " AC <- ULA(" + reg2 + " - " + reg3 + ")\n"
                        + "T3:" + reg1 + " <- AC \n");
                }
            case "li":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:" + reg1 + " <- " + reg2 + "(P1)\n");
            case "mov":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:" + reg1 + " <- " + reg2 + "\n");
            case "beq":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + reg1 + "\n"
                        + "T2:ULA <- " + reg2 + "\n"
                        + " AC <- ULA (" + reg1 + "-" + reg2 + ") \n")
                        + "T3:JUMP (if AC == 0)\n";
            case "bne":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                    + "T1:X <- " + reg1 + "\n"
                    + "T2:ULA <- " + reg2 + "\n"
                    + " AC <- ULA (" + reg1 + "-" + reg2 + ") \n")
                    + "T3:JUMP (if AC != 0)\n";
            case "slt":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + reg2 + "\n"
                        + "T2:ULA <- " + reg3 + "\n"
                        + "T3:AC <- ULA (" + reg2 + "<" + reg3 + ")\n"
                        + " " + reg1 + " <- AC\n");
            default:
        }
        return "";
    }
  
    //Aqui juntei todos os metodos em 1 s�, que contem o ciclo de busca, indirecao e execucao
    /*public static String TelaDePrint(String op, String reg1, String reg2) {
        CicloDeBusca();
        if ((reg1.contains("[")) && (reg1.contains("]"))) {
            reg1 = CicloDeIndirecao1(reg1);
            CicloDeExecucao(op, "P1", reg2);
        } else if ((reg2.contains("[")) && (reg2.contains("]"))) {
            reg2 = CicloDeIndirecao2(reg2);
            CicloDeExecucao(op, reg1, "P2");
        } else {
            CicloDeExecucao(op, reg1, reg2);
        }
    }*/
  
    /*public static void main(String[] args) {
  
        TelaDePrint("add", "[ax]", "100");
        TelaDePrint("div", "20", ".");
        TelaDePrint("sub", "cx", "50");
        TelaDePrint("mul", "3", ".");
        TelaDePrint("mov", "ax", "1");
        TelaDePrint("inc", "bx", ".");
  
        
       ai se o cara entra com a operacao, agt faz operacao.equals(add) seila
       e os outros arrays que receberiam por exemplo ax, 100
       agt faz separado, porque nao precisa fazer comparacao, � s� aplicar a operacao
         
    }*/
  
  }
  
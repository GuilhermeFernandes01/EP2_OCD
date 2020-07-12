public class Ciclos {

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
  
    //Indirecao de p1
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
  
    //Indirecao de p2
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
    public String CicloDeExecucao(String operacao, String p1, String p2) {
        switch (operacao) {
            case "add":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + p1.toUpperCase() + "\n"
                        + "T2:ULA <- " + p2 + "(P2)\n"
                        + "T3:AC <- ULA(" + p1.toUpperCase() + " + " + p2 + ")\n"
                        + "T4:" + p1.toUpperCase() + " <- AC \n");
            case "sub":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- " + p1.toUpperCase() + "\n"
                        + "T2:ULA <- " + p2 + "(P2)\n"
                        + "T3:AC <- ULA(" + p1 + " - " + p2 + ")\n"
                        + "T4:" + p1.toUpperCase() + " <- AC \n");
            case "div":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- AX \n"
                        + "T2:ULA <- " + p1.toUpperCase() + "\n"
                        + "T3:AC <- ULA(AX / " + p1.toUpperCase() + ")\n"
                        + "T4:AX <- AC \n"
                        + "T5:ULA <-" + p1.toUpperCase() + "\n"
                        + "T6:AC <- ULA(AX%" + p1.toUpperCase() + ")\n"
                        + "T7:DX <- AC\n");
            case "mul":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:X <- AX \n"
                        + "T2:ULA <- " + p1.toUpperCase() + "\n"
                        + "T3:AC <- ULA(AX * " + p1.toUpperCase() + ")\n"
                        + "T4:AX <- AC \n"
                        + "T5:DX <- AC \n"//se a multiplicacao tiver mais de 8 bits, o que excede vai pro DX
                );
            case "mov":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:" + p1.toUpperCase() + " <- " + p2.toUpperCase() + "\n");
            case "inc":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:ULA <- " + p1.toUpperCase() + "\n"
                        + "T2:AC <- ULA(" + p1.toUpperCase() + "+1)\n"
                        + "T3:" + p1.toUpperCase() + " <- AC \n");
            case "dec":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n"
                        + "T1:ULA <- " + p1.toUpperCase() + "\n"
                        + "T2:AC <- ULA(" + p1.toUpperCase() + "-1)\n"
                        + "T3:" + p1.toUpperCase() + " <- AC \n");
            default:
        }
        return "";
    }
  
    //Aqui juntei todos os metodos em 1 s�, que contem o ciclo de busca, indirecao e execucao
    /*public static String TelaDePrint(String op, String p1, String p2) {
        CicloDeBusca();
        if ((p1.contains("[")) && (p1.contains("]"))) {
            p1 = CicloDeIndirecao1(p1);
            CicloDeExecucao(op, "P1", p2);
        } else if ((p2.contains("[")) && (p2.contains("]"))) {
            p2 = CicloDeIndirecao2(p2);
            CicloDeExecucao(op, p1, "P2");
        } else {
            CicloDeExecucao(op, p1, p2);
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
  
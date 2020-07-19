public class Ciclos {

    private boolean ehNumero;

    // Verifica se é um valor númerico ou String
    public boolean testaNumero(String testado) {
        try {
            Integer.parseInt(testado);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    // Ciclo de busca
    public static String CicloDeBusca() {
        return ("CICLO DE BUSCA:\n" + "T1:MAR <- PC \n" + " ULA <- PC\n" + "T2:MBR <- memória \n" + " PC <- AC \n"
                + "T3:AC <- ULA \n" + "T4:IR <- MBR \n");
    }

    // Ciclo de Execução
    public String CicloDeExecucao(String operacao, String reg1, String reg2, String reg3) {
        switch (operacao) {
            case "add":
                ehNumero = testaNumero(reg3);
                if (!ehNumero) {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg2 + "\n" + "T2:ULA <- "
                            + reg3 + "\n" + " AC <- ULA(" + reg2 + " + " + reg3 + ")\n" + "T3:" + reg1 + " <- AC \n");
                } else {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg2 + "\n" + "T2:ULA <- "
                            + reg3 + "(P1)" + "\n" + " AC <- ULA(" + reg2 + " + " + reg3 + ")\n" + "T3:" + reg1
                            + " <- AC \n");
                }
            case "sub":
                ehNumero = testaNumero(reg3);
                if (!ehNumero) {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg2 + "\n" + "T2:ULA <- "
                            + reg3 + "\n" + " AC <- ULA(" + reg2 + " - " + reg3 + ")\n" + "T3:" + reg1 + " <- AC \n");
                } else {
                    return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg2 + "\n" + "T2:ULA <- "
                            + reg3 + "(P1)\n" + " AC <- ULA(" + reg2 + " - " + reg3 + ")\n" + "T3:" + reg1
                            + " <- AC \n");
                }
            case "li":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:" + reg1 + " <- " + reg2 + "(P1)\n");
            case "move":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:" + reg1 + " <- " + reg2 + "\n");
            case "beq":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg1 + "\n" + "T2:ULA <- " + reg2
                        + "\n" + " AC <- ULA (" + reg1 + "-" + reg2 + ") \n") + "T3:JUMP (if AC == 0)\n";
            case "bne":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg1 + "\n" + "T2:ULA <- " + reg2
                        + "\n" + " AC <- ULA (" + reg1 + "-" + reg2 + ") \n") + "T3:JUMP (if AC != 0)\n";
            case "slt":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1:X <- " + reg2 + "\n" + "T2:ULA <- " + reg3
                        + "\n" + "T3:AC <- ULA (" + reg2 + "<" + reg3 + ")\n" + " " + reg1 + " <- AC\n");
            case "j":
                return ("CICLO DE " + operacao.toUpperCase() + ":\n" + "T1: MBR <- MEM\n" + " PC <- MBR\n");
            default:
                break;
        }
        return "";
    }
}

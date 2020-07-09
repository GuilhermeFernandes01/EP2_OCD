public class Operacoes {
    public int s1 = 0000;
    public int s2 = 0000;
    public int s3 = 0000;
    public int s4 = 0000;
    public String s = "";
    public String z = "";

    public void setS1(int s1) {
        this.s1 = s1;
    }

    public int getS1() {
        return s1;
    }

    public void setS2(int s2) {
        this.s2 = s2;
    }

    public int getS2() {
        return s2;
    }

    public void setS3(int s3) {
        this.s3 = s3;
    }

    public int getS3() {
        return s3;
    }

    public void setS4(int s4) {
        this.s4 = s4;
    }

    public int getS4() {
        return s4;
    }

    public int add(String armazenado, String regSoma, String valor) {
        String reg3 = "";
        boolean ehNumero = false;
        int valueReg2 = 0, valueReg3 = 0, soma = 0;

        try {
            valueReg3 = Integer.parseInt(valor);
            ehNumero = true;
        } catch (NumberFormatException e) {
            reg3 = valor;
        }
        
        if (!ehNumero) {
            switch (reg3) {
                case "s1":
                    valueReg3 = getS1();
                    break;
                case "s2":
                    valueReg3 = getS2();
                    break;
                case "s3":
                    valueReg3 = getS3();
                    break;
                case "s4":
                    valueReg3 = getS4();
                    break;
                default:
                    break;
            }
        }
        
        switch (regSoma) {
            case "s1":
                valueReg2 = getS1();
                break;
            case "s2":
                valueReg2 = getS2();
                break;
            case "s3":
                valueReg2 = getS3();
                break;
            case "s4":
                valueReg2 = getS4();
                break;
            default:
                break;
        }

        switch (armazenado) {
            case "s1":
                setS1(valueReg2 + valueReg3);
                soma = getS1();
                break;
            case "s2":
                setS2(valueReg2 + valueReg3);
                soma = getS2();
                break;
            case "s3":
                setS3(valueReg2 + valueReg3);
                soma = getS3();
                break;
            case "s4":
                setS4(valueReg2 + valueReg3);
                soma = getS4();
                break;
            default:
                break;
        }
        

        return soma;
    }
    
    // public static int strParaInt(String variable) {
    //     try {
    //         return Integer.parseInt(variable);

    //     } catch (NumberFormatException e) {
    //         return -1;
    //     }
    // }

    // public int add(String varS, String var2S) {
    //     int response = strParaInt(var2S), result;
    //     if (response == -1) {//� String (com registradores)
    //         String x = compararReg(varS, var2S);
    //         if (x == "ab") {
    //             setAx(getAx() + getBx());
    //             return getAx();
    //         } else if (x == "ba") {
    //             setBx(getAx() + getBx());
    //             return getBx();
    //         } else if (x == "ac") {
    //             setAx(getAx() + getCx());
    //             return getAx();
    //         } else if (x == "ca") {
    //             setCx(getAx() + getCx());
    //             return getCx();
    //         } else if (x == "ad") {
    //             setAx(getAx() + getDx());
    //             return getAx();
    //         } else if (x == "da") {
    //             setDx(getAx() + getDx());
    //             return getDx();
    //         } else if (x == "aa") {
    //             setAx(getAx() + getAx());
    //             return getAx();
    //         } else if (x == "bc") {
    //             setBx(getBx() + getCx());
    //             return getBx();
    //         } else if (x == "cb") {
    //             setCx(getBx() + getCx());
    //             return getCx();
    //         } else if (x == "bd") {
    //             setBx(getBx() + getDx());
    //             return getBx();
    //         } else if (x == "db") {
    //             setDx(getBx() + getDx());
    //             return getDx();
    //         } else if (x == "bb") {
    //             setBx(getBx() + getBx());
    //             return getBx();
    //         } else if (x == "cd") {
    //             setCx(getCx() + getDx());
    //             return getCx();
    //         } else if (x == "dc") {
    //             setDx(getCx() + getDx());
    //             return getDx();
    //         } else if (x == "cc") {
    //             setCx(getCx() + getCx());
    //             return getCx();
    //         } else if (x == "dd") {
    //             setDx(getDx() + getDx());
    //             return getDx();
    //         }

    //     } else {
    //         String x = compararReg(varS, "");
    //         if (x == "a") {
    //             setAx(getAx() + response);
    //             return getAx();
    //         } else if (x == "b") {
    //             setBx(getBx() + response);
    //             return getBx();
    //         } else if (x == "c") {
    //             setCx(getCx() + response);
    //             return getCx();
    //         } else if (x == "d") {
    //             setDx(getDx() + response);
    //             return getDx();
    //         }

    //     }

    //     return 0;
    // }
}
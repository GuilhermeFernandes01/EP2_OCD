public class Operacoes {
    public int s1 = 0;
    public int s2 = 0;
    public int s3 = 0;
    public int s4 = 0;
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
            case "$s1":
                valueReg2 = getS1();
                break;
            case "$s2":
                valueReg2 = getS2();
                break;
            case "$s3":
                valueReg2 = getS3();
                break;
            case "$s4":
                valueReg2 = getS4();
                break;
            default:
                break;
        }

        switch (armazenado) {
            case "$s1":
                setS1(valueReg2 + valueReg3);
                soma = getS1();
                break;
            case "$s2":
                setS2(valueReg2 + valueReg3);
                soma = getS2();
                break;
            case "$s3":
                setS3(valueReg2 + valueReg3);
                soma = getS3();
                break;
            case "$s4":
                setS4(valueReg2 + valueReg3);
                soma = getS4();
                break;
            default:
                break;
        }

        return soma;
    }

    public int sub(String armazenado, String regSub, String valor) {
        String reg3 = "";
        boolean ehNumero = false;
        int valueReg2 = 0, valueReg3 = 0, sub = 0;

        try {
            valueReg3 = Integer.parseInt(valor);
            ehNumero = true;
        } catch (NumberFormatException e) {
            reg3 = valor;
        }

        if (!ehNumero) {
            switch (reg3) {
                case "$s1":
                    valueReg3 = getS1();
                    break;
                case "$s2":
                    valueReg3 = getS2();
                    break;
                case "$s3":
                    valueReg3 = getS3();
                    break;
                case "$s4":
                    valueReg3 = getS4();
                    break;
                default:
                    break;
            }
        }

        switch (regSub) {
            case "$s1":
                valueReg2 = getS1();
                break;
            case "$s2":
                valueReg2 = getS2();
                break;
            case "$s3":
                valueReg2 = getS3();
                break;
            case "$s4":
                valueReg2 = getS4();
                break;
            default:
                break;
        }

        switch (armazenado) {
            case "$s1":
                setS1(valueReg2 - valueReg3);
                sub = getS1();
                break;
            case "$s2":
                setS2(valueReg2 - valueReg3);
                sub = getS2();
                break;
            case "$s3":
                setS3(valueReg2 - valueReg3);
                sub = getS3();
                break;
            case "$s4":
                setS4(valueReg2 - valueReg3);
                sub = getS4();
                break;
            default:
                break;
        }

        return sub;
    }

    public int li(String reg, String valor) {
        int valueReg = 0;
        valueReg = Integer.parseInt(valor);

        switch (reg) {
            case "$s1":
                setS1(valueReg);
                valueReg = getS1();
                break;
            case "$s2":
                setS2(valueReg);
                valueReg = getS2();
                break;
            case "$s3":
                setS3(valueReg);
                valueReg = getS3();
                break;
            case "$s4":
                setS4(valueReg);
                valueReg = getS4();
                break;
            default:
                break;
        }

        return valueReg;
    }

    public int mov(String destino, String origem) {
        int valueOrigem = 0;
        int valueDestino = 0;

        switch (origem) {
            case "$s1":
                valueOrigem = getS1();
                break;
            case "$s2":
                valueOrigem = getS2();
                break;
            case "$s3":
                valueOrigem = getS3();
                break;
            case "$s4":
                valueOrigem = getS4();
                break;
            default:
                break;
        }

        switch (destino) {
            case "$s1":
                setS1(valueOrigem);
                valueDestino = getS1();
                break;
            case "$s2":
                setS2(valueOrigem);
                valueDestino = getS2();
                break;
            case "$s3":
                setS3(valueOrigem);
                valueDestino = getS3();
                break;
            case "$s4":
                setS4(valueOrigem);
                valueDestino = getS4();
                break;
            default:
                break;
        }

        return valueDestino;
    }

    public int beq(String reg, String comparador, String linha) {
        int numeroLinha = 0;

        int valorComparador = 0;

        switch (comparador) {
            case "$s1":
                valorComparador = getS1();
                break;
            case "$s2":
                valorComparador = getS2();
                break;
            case "$s3":
                valorComparador = getS3();
                break;
            case "$s4":
                valorComparador = getS4();
                break;
            default:
                break;
        }

        switch (reg) {
            case "$s1":
                if (getS1() == valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s2":
                if (getS2() == valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s3":
                if (getS3() == valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s4":
                if (getS4() == valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            default:
                break;
        }

        return numeroLinha - 2;
    }

    public int bne(String reg, String comparador, String linha) {
        int numeroLinha = 0;

        int valorComparador = 0;

        switch (comparador) {
            case "$s1":
                valorComparador = getS1();
                break;
            case "$s2":
                valorComparador = getS2();
                break;
            case "$s3":
                valorComparador = getS3();
                break;
            case "$s4":
                valorComparador = getS4();
                break;
            default:
                break;
        }

        switch (reg) {
            case "$s1":
                if (getS1() != valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s2":
                if (getS2() != valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s3":
                if (getS3() != valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            case "$s4":
                if (getS4() != valorComparador) {
                    numeroLinha = Integer.parseInt(linha);
                }
                break;
            default:
                break;
        }

        return numeroLinha - 2;
    }

    public int slt(String armazenador, String comparador, String comparado) {
        int comparadorValue = 0;
        int comparadoValue = 0;
        int armazenadorValue = 0;

        switch (comparador) {
            case "$s1":
                comparadorValue = getS1();
                break;
            case "$s2":
                comparadorValue = getS2();
                break;
            case "$s3":
                comparadorValue = getS3();
                break;
            case "$s4":
                comparadorValue = getS4();
                break;
            default:
                break;
        }

        switch (comparado) {
            case "$s1":
                comparadoValue = getS1();
                break;
            case "$s2":
                comparadoValue = getS2();
                break;
            case "$s3":
                comparadoValue = getS3();
                break;
            case "$s4":
                comparadoValue = getS4();
                break;
            default:
                break;
        }

        switch (armazenador) {
            case "$s1":
                if (comparadorValue < comparadoValue) {
                    setS1(1);
                } else {
                    setS1(0);
                }

                armazenadorValue = getS1();
                break;
            case "$s2":
                if (comparadorValue < comparadoValue) {
                    setS2(1);
                } else {
                    setS2(0);
                }

                armazenadorValue = getS2();
                break;
            case "$s3":
                if (comparadorValue < comparadoValue) {
                    setS3(1);
                } else {
                    setS3(0);
                }

                armazenadorValue = getS3();
                break;
            case "$s4":
                if (comparadorValue < comparadoValue) {
                    setS4(1);
                } else {
                    setS4(0);
                }

                armazenadorValue = getS4();
                break;
            default:
                break;
        }

        return armazenadorValue;
    }
}
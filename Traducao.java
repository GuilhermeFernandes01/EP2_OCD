public class Traducao extends Operacoes {

  // Dado um Array de Inteiros, transforma em String
  public String arrayToString(int[] array) {
    String retorno = "";

    for (int i = 0; i < array.length; i++) {
      retorno += String.valueOf(array[i]);
    }

    return retorno;
  }

  // Dado um Array de inteiros, retorna um novo somente com zeros
  public int[] zeraArray(int[] array) {
    int[] novoArray = new int[array.length];

    for (int i = 0; i < array.length; i++) {
      array[i] = 0;
    }

    return novoArray;
  }

  public String montaPalavra(int[] arraySinaisControle, int[] arrayOpCodes, int[] arrayMemoria, int[] arrayPulo,
      int[] arrayEnderecoPulo) {
    return arrayToString(arraySinaisControle) + " " + arrayToString(arrayOpCodes) + " " + arrayToString(arrayMemoria)
        + " " + arrayToString(arrayPulo) + " " + arrayToString(arrayEnderecoPulo) + "\n";
  }

  /*
   * Traduz para linguagem de máquina As posições dos arrays estão representadas
   * como [posição - 1] para facilitar a visualização, já que a posição real é a
   * [da arquitetura desenhada - 1]
   */
  public String traduz(String operacao, String reg1, String reg2, String reg3) {
    // Define os arrays de cada parte da palavra
    int arraySinaisControle[] = new int[28];
    int arrayOpCodes[] = new int[4];
    int arrayMemoria[] = new int[2];
    int arrayPulo[] = new int[2];
    int arrayEnderecoPulo[] = new int[7];

    // Zera todos os arrays
    arraySinaisControle = zeraArray(arraySinaisControle);
    arrayOpCodes = zeraArray(arrayOpCodes);
    arrayMemoria = zeraArray(arrayMemoria);
    arrayPulo = zeraArray(arrayPulo);
    arrayEnderecoPulo = zeraArray(arrayEnderecoPulo);

    String palavra = "";

    switch (operacao) {
      case "li":
        arraySinaisControle[19 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
        break;
      case "move":
        arrayOpCodes[3 - 1] = 1;
        arrayOpCodes[4 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        break;
      case "beq":
        arrayOpCodes[1 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[22 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arrayPulo[1 - 1] = 1;

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle[28 - 1] = 1;
        arraySinaisControle[26 - 1] = 1;
        arraySinaisControle[5 - 1] = 1;
        arraySinaisControle[1 - 1] = 1;

        arrayOpCodes = zeraArray(arrayOpCodes);
        arrayOpCodes[1 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;
        break;
      case "bne":
        arrayOpCodes[1 - 1] = 1;
        arrayOpCodes[4 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        int valueReg1 = 0, valueReg2 = 0;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            valueReg2 = getS1();
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            valueReg2 = getS2();
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            valueReg2 = getS3();
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            valueReg2 = getS4();
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[22 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            valueReg1 = getS1();
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            valueReg1 = getS2();
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            valueReg1 = getS3();
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            valueReg1 = getS4();
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);

        if (valueReg1 - valueReg2 < 0) {
          arrayPulo[2 - 1] = 1;
        } else {
          arrayPulo[2 - 1] = 0;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle[28 - 1] = 1;
        arraySinaisControle[26 - 1] = 1;
        arraySinaisControle[5 - 1] = 1;
        arraySinaisControle[1 - 1] = 1;

        arrayOpCodes = zeraArray(arrayOpCodes);
        arrayOpCodes[1 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;
        break;
      case "j":
        arraySinaisControle[28 - 1] = 1;
        arraySinaisControle[26 - 1] = 1;
        arraySinaisControle[5 - 1] = 1;
        arraySinaisControle[1 - 1] = 1;

        arrayOpCodes[1 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;

        arrayPulo[1 - 1] = 1;
        arrayPulo[2 - 1] = 1;
        break;
      case "slt":
        arrayOpCodes[1 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;
        arrayOpCodes[4 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[22 - 1] = 1;

        switch (reg3) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[23 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }

    if (operacao.equals("add")) {
      boolean ehNumero = false;
      try {
        Integer.parseInt(reg3);
        ehNumero = true;
      } catch (NumberFormatException e) {
      }

      if (ehNumero) {
        arrayOpCodes[2 - 1] = 1;
        arrayOpCodes[4 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[19 - 1] = 1;
        arraySinaisControle[22 - 1] = 1;

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[23 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
      } else {
        arrayOpCodes[2 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[22 - 1] = 1;

        switch (reg3) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[23 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
      }
    }

    if (operacao.equals("sub")) {
      boolean ehNumero = false;
      try {
        Integer.parseInt(reg3);
        ehNumero = true;
      } catch (NumberFormatException e) {
      }

      if (ehNumero) {
        arrayOpCodes[2 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;
        arrayOpCodes[4 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[19 - 1] = 1;
        arraySinaisControle[22 - 1] = 1;

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[23 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
      } else {
        arrayOpCodes[2 - 1] = 1;
        arrayOpCodes[3 - 1] = 1;
        arraySinaisControle[21 - 1] = 1;

        switch (reg2) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[22 - 1] = 1;

        switch (reg3) {
          case "$s1":
            arraySinaisControle[7 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[9 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[11 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[13 - 1] = 1;
            break;
          default:
            break;
        }

        palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

        arraySinaisControle = zeraArray(arraySinaisControle);
        arraySinaisControle[23 - 1] = 1;

        switch (reg1) {
          case "$s1":
            arraySinaisControle[6 - 1] = 1;
            break;
          case "$s2":
            arraySinaisControle[8 - 1] = 1;
            break;
          case "$s3":
            arraySinaisControle[10 - 1] = 1;
            break;
          case "$s4":
            arraySinaisControle[12 - 1] = 1;
            break;
          default:
            break;
        }
      }
    }

    palavra += montaPalavra(arraySinaisControle, arrayOpCodes, arrayMemoria, arrayPulo, arrayEnderecoPulo);

    return palavra;
  }
}
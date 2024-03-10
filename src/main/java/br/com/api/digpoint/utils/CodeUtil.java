package br.com.api.digpoint.utils;

import java.util.Random;

public class CodeUtil {
  public String codeGeneration() {
    Random random = new Random();
    int codigoInteiro = random.nextInt(10000);

    // Formatando o número como uma string de 4 dígitos com zeros à esquerda, se necessário
    return String.format("%04d", codigoInteiro);
}
}

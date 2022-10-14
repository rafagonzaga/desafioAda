package desafio06;

import java.util.Scanner;

public class PrestacaooAnual {
	
	public static void main(String[] args) {
		
		System.out.println("Bem-vindo ao programa de cálculo de uma prestação anual");
		System.out.print("\nEntre com o valor do salário: ");
		Scanner entrada = new Scanner(System.in);
		
		double salario = entrada.nextDouble();
	
		double salarioDescontado, decimoTerceiro, tercoFerias, contribuicaoSalario,
		contribuicaoFerias, totalContribuicao, aliquotaEfetiva;
			
		contribuicaoSalario = calculaContribuicao(salario)[4];
		contribuicaoFerias = calculaContribuicao(salario / 3)[4];
		totalContribuicao = contribuicaoSalario * 13 + contribuicaoFerias;
				
		salarioDescontado = salario - contribuicaoSalario;
		decimoTerceiro = salarioDescontado;
		tercoFerias = salario/3 - calculaContribuicao(salario/3)[4];

		double vencimentoAnual = 12 * salario + salario + salario / 3;
		double prestacaoAnual = 12 * salarioDescontado + decimoTerceiro + tercoFerias;
		double vencimentoAnualMaximoParaFinsInss = 7087.22 * 13 + 7087.22 / 3;
		
		if(vencimentoAnual > vencimentoAnualMaximoParaFinsInss) {
			aliquotaEfetiva = totalContribuicao / vencimentoAnualMaximoParaFinsInss;
		} else {
			aliquotaEfetiva = totalContribuicao / vencimentoAnual;
		}
		
		System.out.println("\nPara o salário informado:");
		System.out.println("\n-- Vencimentos (+) --");
		System.out.printf("Total dos salários: R$ %.2f\n", (salario * 12));
		System.out.printf("Terço de férias: R$ %.2f\n", (salario / 3));
		System.out.println("Décimo terceiro salário: " + salario);
		System.out.printf("Total dos vencimentos: R$ %.2f", ((salario * 12)+(salario / 3)+ salario));
		System.out.println("\n\n-- Descontos (-) --");
		System.out.printf("Aliquota do INSS: %.2f%%", aliquotaEfetiva * 100);
		System.out.printf("\nINSS: %.2f", (totalContribuicao));
		System.out.printf("\nTotal dos descontos: R$ %.2f", totalContribuicao);
		System.out.printf("\nO valor de uma prestação anual com o salário de R$ %.2f é de R$ %.2f", salario, prestacaoAnual);
		System.out.println("\n--- Fim do programa ---");
		entrada.close();
		
	}
	
	public static double [] calculaContribuicao(double salario) {
		double inss;
		double [] contribuicao = new double[5];
		
		if(salario > 1212.00) {
			inss = 0.075;
			contribuicao[0] = 1212 * inss;
			if(salario > 2427.35) {
				inss = 0.09;
				contribuicao[1] = (2427.35 - 1212.01) * inss;
				if(salario > 3641.03) {
					inss = 0.12;
					contribuicao[2] = (3641.03 - 2427.36) * inss;
					if(salario > 7087.22) {
						inss = 0.14;
						contribuicao[3] = (7087.22 - 3641.03) * inss;
					} else {
						inss = 0.14;
						contribuicao[3] = (salario - 3641.04) * inss;
					}
				} else {
					inss = 0.12;
					contribuicao[2] = (salario - 2427.35) * inss;
				}
			} else {
				inss = 0.09;
				contribuicao[1] = (salario - 1212) * inss;;
			}
		} else {
			inss = 0.075;
			contribuicao[0] = salario * inss;
		} 
		double soma = 0.0;
		for(int i=0; i < contribuicao.length; i++) {
			if(contribuicao[i] >= 0) {
				soma += contribuicao[i];
			}
		}
		contribuicao[4] = soma;
		
		return contribuicao;
	}
}



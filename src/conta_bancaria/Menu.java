package conta_bancaria;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import conta_bancaria.controller.ContaController;
import conta_bancaria.model.Conta;
import conta_bancaria.model.ContaCorrente;
import conta_bancaria.model.ContaPoupanca;
import conta_bancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);
		
		ContaController contas = new ContaController();
		
		int opcao, numero, agencia, tipo, aniversario;
		String titular;
		float saldo, limite;
		
		// Dados para teste
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "João da Silva", 1000.00f, 100.00f);
		contas.cadastrar(cc1);
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 123, 2, "Maria da Silva", 1000.00f, 12);
		contas.cadastrar(cp1);
		
		while(true) {
			
			System.out.print(Cores.ANSI_BLACK_BACKGROUND + Cores.TEXT_BLUE_BOLD);
			System.out.println("*****************************************************");
			System.out.println("                                                     " + Cores.TEXT_WHITE_BOLD_BRIGHT);
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     " + Cores.TEXT_BLUE);
			System.out.println("*****************************************************");
			System.out.println("                                                     " + Cores.TEXT_WHITE);
			System.out.println("         1 - Criar Conta                             ");
			System.out.println("         2 - Listar todas as Contas                  ");
			System.out.println("         3 - Buscar Conta por Numero                 ");
			System.out.println("         4 - Atualizar Dados da Conta                ");
			System.out.println("         5 - Apagar Conta                            ");
			System.out.println("         6 - Sacar                                   ");
			System.out.println("         7 - Depositar                               ");
			System.out.println("         8 - Transferir valores entre Contas         ");
			System.out.println("         9 - Sair                                    ");
			System.out.println("                                                     " + Cores.TEXT_BLUE_BOLD);
			System.out.println("*****************************************************" + Cores.TEXT_WHITE);
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			opcao = leia.nextInt();
			
			if(opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				sobre();
				leia.close();
				System.exit(0);
			}
			
			switch(opcao) {
				case 1:
					System.out.println("Criar Conta\n\n");
					
					System.out.print("Digite o número da Agência: ");
					agencia = leia.nextInt();
					
					System.out.print("Digite o nome do Titular: ");
					leia.skip("\\R");
					titular = leia.nextLine();
					
					System.out.print("Digite o tipo da Conta (1 - Conta Corrente | 2 - Conta Poupança): ");
					tipo = leia.nextInt();
					
					System.out.print("Digite o Saldo inicial da Conta: ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
						case 1 ->{
							System.out.print("Digite o limite da Conta: ");
							limite = leia.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
						}
						case 2 ->{
							System.out.print("Digite o dia do aniversário do Titular: ");
							aniversario = leia.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
						}
					}
					
					keyPress();
					break;
				case 2:
					System.out.println("Listar todas as Contas\n\n");
					
					contas.listarTodas();
					
					keyPress();
					break;
				case 3:
					System.out.println("Buscar Conta por Numero\n\n");
					
					System.out.print("Digite o número da Conta que deseja encontrar: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
				case 4:
					System.out.println("Atualizar Dados da Conta\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					Optional<Conta> conta = contas.buscarNaCollection(numero);
					
					if(conta.isPresent()) {
						System.out.print("Digite o número da Agência: ");
						agencia = leia.nextInt();
						
						System.out.print("Digite o nome do Titular: ");
						leia.skip("\\R");
						titular = leia.nextLine();
						
						tipo = conta.get().getTipo();
						
						System.out.print("Digite o Saldo da Conta: ");
						saldo = leia.nextFloat();
						
						switch(tipo) {
						case 1 ->{
							System.out.print("Digite o limite da Conta: ");
							limite = leia.nextFloat();
							contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						}
						case 2 ->{
							System.out.print("Digite o dia do aniversário do Titular: ");
							aniversario = leia.nextInt();
							contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
						}
					}
					} else
						System.out.printf("\nA conta do número %d não existe!", numero);
					
					keyPress();
					break;
				case 5:
					System.out.println("Apagar Conta\n\n");
					
					System.out.println("Digite o número da conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);
					
					keyPress();
					break;
				case 6:
					System.out.println("Sacar\n\n");
					
					keyPress();
					break;
				case 7:
					System.out.println("Depositar\n\n");
					
					keyPress();
					break;
				case 8:
					System.out.println("Transferir valores entre Contas\n\n");
					
					keyPress();
					break;
				default:
					System.out.println("Opção inválida!\n\n");
					
					keyPress();
					break;
			}
			
			
			
		}
		


	}
	
	public static void sobre() {
		System.out.println("\n*********************************************************");
		System.out.println("Projeto Desenvolvido por: Luan Queiroz");
		System.out.println("Luan Q. - luan.queiroz2003@gmail.com");
		System.out.println("https://github.com/Luuh03");
		System.out.println("*********************************************************");
	}
	
	public static void keyPress() {
		 
		try {
 
			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();
 
		} catch (IOException e) {
 
			System.err.println("Você pressionou uma tecla diferente de enter!");
 
		}
	}
 

}

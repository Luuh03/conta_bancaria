package conta_bancaria;

import java.util.Scanner;

import conta_bancaria.model.Conta;
import conta_bancaria.util.Cores;

public class Menu {

	public static void main(String[] args) {

		Scanner leia = new Scanner(System.in);
		
		int opcao;
		
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
					break;
				case 2:
					System.out.println("Listar todas as Contas\n\n");
					break;
				case 3:
					System.out.println("Buscar Conta por Numero\n\n");
					break;
				case 4:
					System.out.println("Atualizar Dados da Conta\n\n");
					break;
				case 5:
					System.out.println("Apagar Conta\n\n");
					break;
				case 6:
					System.out.println("Sacar\n\n");
					break;
				case 7:
					System.out.println("Depositar\n\n");
					break;
				case 8:
					System.out.println("Transferir valores entre Contas\n\n");
					break;
				default:
					System.out.println("Opção inválida!\n\n");
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

}

package vendingMachine;

import java.util.ArrayList;
import java.util.Scanner;

public class Gerenciadora {

		static Bebidas bebidaseEntrega;
		private static double saldo = 20;
		private static double troco = 0;
		private static double valorTotal = 0.0;
		private static int numeroVendas = 0;
		
		static ArrayList<Bebidas> bebidas = new ArrayList<Bebidas>(100);

		public double getSaldo() {
			return saldo;
		}

		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}

		public Gerenciadora () {

		}

		public static void main(String args[]) {

			Bebidas coca = new Bebidas();
			coca.setMarca("Coca");
			coca.setValor(4.00);
			coca.setQntEstoque(10);

			Bebidas sprite = new Bebidas();
			sprite.setMarca("Sprite");
			sprite.setValor(3.00);
			sprite.setQntEstoque(10);
			
			Bebidas pepsi = new Bebidas();
			pepsi.setMarca("Pepsi");
			pepsi.setValor(3.00);
			pepsi.setQntEstoque(10);
			
			Bebidas guarana = new Bebidas();
			guarana.setMarca("Guarana");
			guarana.setValor(3.00);
			guarana.setQntEstoque(10);

			Bebidas ch = new Bebidas();
			ch.setMarca("Cha");
			ch.setValor(2.00);
			ch.setQntEstoque(10);

			Bebidas suco = new Bebidas();
			suco.setMarca("Suco de Laranja");
			suco.setValor(4.00);
			suco.setQntEstoque(10);

			Bebidas agua = new Bebidas();
			agua.setMarca("agua");
			agua.setValor(2.00);
			agua.setQntEstoque(10);

			for (int i = 0; i < 1; i++) {
				bebidas.add(coca);
				bebidas.add(sprite);
				bebidas.add(pepsi);
				bebidas.add(guarana);
				bebidas.add(ch);
				bebidas.add(suco);
				bebidas.add(agua);
			}

			Scanner sc = new Scanner(System.in);
			
			int opcao = -1;
			int num = -1;
			do {
				
				System.out.println("\nQuantidade de Bebidas(s): " + bebidas.size());
				System.out.println("Saldo da Maquina: "+saldo +" R$\n");
				
				System.out.println(" / MÁQUINA DE BEBIDAS  /\n");
				
				for (int i = 0; i < bebidas.size(); i++) {
					System.out.println("Bebida: "+bebidas.get(i).getMarca() +" - Valor R$:"+bebidas.get(i).getValor() + "| Quantidade no Estoque: " + bebidas.get(i).getQntEstoque());
				}			

				System.out.println(" \nMenu /-----/ \n  0 - Sair\n  1 -Entrar \n  2- Ver Relatorio\n  Infome a opção desejada: ");
				opcao = sc.nextInt();
				
				if(opcao == 1){
				
					System.out.print("Entre com o valor depositado (ex: 3):");
					double valorDepositado = sc.nextDouble();
					System.out.print("Selecione sua bebida (ex: sprite): ");
					String bebida = sc.next();
					System.out.println("\nVerificando...\n");
					boolean tem = vericarDisponibilidadeBebida(bebida);
					
					if (tem == true) {
						
						System.out.println("Temos a sua bebida \\0/ \n Bebida: "+ bebida +", Valor R$" +bebidaseEntrega.getValor()+". Podemos prosseguir com a compra?\n 0 - Sair\n 1 - Continuar");
						num = sc.nextInt();

						if (num == 1) {
							entregarBebida(bebida, valorDepositado);
							gerarRelatorio();
	
							
						} else {
							num = 0;
						}

					} else {
						System.out.println("/0\\ - Infelizmente não temos a sua bebida: "+bebida);
						System.out.println("Seu Troco: R$ "+valorDepositado);
						System.out.println("----------------------------------");
					}				
					
				} else if(opcao == 2){
					mostrarRelatorio();
				}
				else {
					num = 0;
				}
			} while(num != 0 );
					
			System.out.println("Saldo da Maquina: "+saldo +" R$");
			System.out.println("Qnt de Bebidas(s): " + bebidas.size());
		}


		public double receberCredito(double credito) {

			double totalCreditos = 0;

			totalCreditos = totalCreditos + credito;

			return credito;
		}


		public double valorTotalMaquina() {
			return saldo;
		}


		public double saldoMaquina(double valor) {
			return saldo = saldo + valor;
		}


		public static boolean vericarDisponibilidadeBebida(String drink) {

			boolean tem = false;
			int i = 0;
	        while( bebidas.size() > i){
				tem = bebidas.get(i).getMarca().equalsIgnoreCase(drink);
				bebidaseEntrega = bebidas.get(i);
				if(tem == true){
					i = 0;
					return true;
				} else {
					i++;
				}
			}
			return false;
		}



		public static void entregarBebida(String drink, double valor) {
			
	  
			for (int j = 0; j < bebidas.size(); j++) {
				if(bebidas.get(j).getMarca().equalsIgnoreCase(drink) == true){
					
					bebidaseEntrega = bebidas.get(j);
					
					if(valor == bebidaseEntrega.getValor()){
						saldo = saldo + valor;	
						
						tirarBebidas(drink, bebidas);
						
						j = bebidas.size();
						
					} else if(valor > bebidaseEntrega.getValor()){
						
						saldo = saldo + bebidaseEntrega.getValor();
						troco = valor - bebidaseEntrega.getValor();
						
						tirarBebidas(drink, bebidas);
						
						j = bebidas.size();
						
						System.out.println("Seu Troco: "+troco+" R$");
						
					} else if( valor < bebidaseEntrega.getValor() ){
						double valorFaltante = bebidaseEntrega.getValor() - valor;
						System.out.println("Quantidade insuficiente de dinheiro depositado! \nAdicione o valor faltante ( R$" + valorFaltante + "), ou digite 0 para sair\\n 0 - Sair ");
						Scanner scanner = new Scanner(System.in);
						double valorAdicionado = scanner.nextDouble();
						valor += valorAdicionado;
						if(valorAdicionado == 0.0) {
							j = bebidas.size();
							System.out.println("Dinheiro devolvido: R$ "+ valor);
						}
						if(valor == bebidaseEntrega.getValor()) {
							saldo = saldo + valor;
							tirarBebidas(drink, bebidas);
							j = bebidas.size();
						} else if(valor > bebidaseEntrega.getValor()) {
							saldo = saldo + bebidaseEntrega.getValor();
							troco = valor - bebidaseEntrega.getValor();
							
							tirarBebidas(drink, bebidas);
							
							j = bebidas.size();
							
							System.out.println("Seu Troco: "+troco+" R$");
						}
						
						
					}
					System.out.println();
					System.out.println("Obrigado e volte sempre!!!");
					System.out.println("----------------------------------");
				}
			}
		}
		
		public static void tirarBebidas(String drink, ArrayList<Bebidas> bebidas){
			int i = 0;
	        while( bebidas.size() > i){
	        	if (bebidas.get(i).getMarca().equalsIgnoreCase(drink)) {
	        		bebidas.get(i).setQntEstoque(bebidas.get(i).getQntEstoque() - 1);
	        	}
	        	i++;
	        }
				
		}
		
		public static void gerarRelatorio() {
			valorTotal += bebidaseEntrega.getValor();
			numeroVendas++;
		}
		
		public static void mostrarRelatorio() {
			System.out.println("--------------------------------------- Relatorio ---------------------------------------\n\n");
			System.out.println("Valor Total de itens vendidos: R$" + valorTotal + " \nQuantidade de itens vendidos: " + numeroVendas);
			System.out.println("\n-----------------------------------------------------------------------------------------\n\n");
		}
		
	}




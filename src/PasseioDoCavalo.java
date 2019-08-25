import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Character.toLowerCase;
public class PasseioDoCavalo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner e = new Scanner(System.in);
		int continuacao=0;
		char xInicial = 'C', xFinal = 'A';
		int yInicial = 5, yFinal = 1;
		
		//in�cio do jogo, cavalo se encontra na posi��o c5.
		System.out.println("Bem vindo ao jogo! Aqui est� o tabuleiro: \n\nSeu cavalo se encontra na posi��o X (C5)\n");
		//printar o tabuleiro
		System.out.println("\tA\tB\tC\tD\tE\tF\tG\tH");
		System.out.println("\n\n8\n\n7\n\n6\n\n5\t\t\tX\n\n4\n\n3\n\n2\n\n1");
		
		
		//inicio do gameloop
		do {
			//Obs: Por algum motivo, meus try/catches s� funcionam devidamente 
			//a partir da 2a rodada do jogo, se der erro na primeira 
			// rodada o jogo encerra com a msg de erro.
			try {
				System.out.println("\nDigite a coordenada X (letra Mai�scula) para onde voc� quer se mover: ");
				xFinal = e.next(".").charAt(0);
				System.out.println("Digite a coordenada Y (n�mero) para onde voc� quer se mover: ");
				yFinal = e.nextInt();
				if(yFinal<1 || yFinal>8) {
					//exce��o criada para impedir que o usu�rio tente ultrapassar o eixo Y
					throw new OpcaoInvalidaException("");
				}
				
				// checar se cavalo pode ou n�o ir para aquela localiza��o, obedecendo suas limita��es.
				if(ePossivel(xFinal, xInicial, yFinal, yInicial) == false){
					//fazer com que o cavalo seja restrito a sua movimenta��o, e n�o possa "andar para onde quiser"
					throw new MovimentacaoInvalidaException("");
				}
				
				xInicial = xFinal;
				yInicial = yFinal;
					
				if(xInicial == 'C' && yInicial == 5) {
					//impedir que o usu�rio volte a sua posi��o inicial c5
					throw new PosicaoInicialException("");
				}
							
				//printar nova localiza��o do cavalo seguindo a nota��o
				System.out.println("C"+toLowerCase(xInicial)+yInicial);
				System.out.println("Caso queira encerrar o jogo, basta digitar 0. Caso contr�rio, digite qualquer outro n�mero.");
				continuacao = e.nextInt();
			}
			catch (OpcaoInvalidaException s){
				System.out.println("Certifique-se que voc� est� digitando uma letra entre A e H (Maiusculas, por favor) para o eixo X"
						+ " e um valor entre 1 e 8 para o eixo Y.");
			}
			catch(PosicaoInicialException j) {
				System.out.println("Voc� n�o pode retornar � sua posi��o inicial! (C5)");
			}
			catch(MovimentacaoInvalidaException m) {
				System.out.println("O cavalo n�o pode ir para a localiza��o desejada.");
			}
		} while (continuacao!=0);

		
	}
	
	//fun��o criada para converter as letras da coordenada X em numeros para facilitar os calculos de trajetoria.
	public static int converterLetras(char xFinal) throws OpcaoInvalidaException {
		int conversorNumero = 0;
		
		if(xFinal == 'A') {
			conversorNumero = 1;
		}else if(xFinal == 'B') {
			conversorNumero = 2;
		}else if(xFinal == 'C') {
			conversorNumero = 3;
		}else if(xFinal == 'D') {
			conversorNumero = 4;
		}else if(xFinal == 'E') {
			conversorNumero = 5;
		}else if(xFinal == 'F') {
			conversorNumero = 6;
		}else if(xFinal == 'G') {
			conversorNumero = 7;
		}else if(xFinal == 'H') {
			conversorNumero = 8;
		}else {
				throw new OpcaoInvalidaException("");
		}
		return conversorNumero;
	}
	
	public static boolean ePossivel(char xFinal, char xInicial, int yFinal, int yInicial) throws OpcaoInvalidaException {
		boolean podeIr = true;
		
		//if(abs(converterLetras(xFinal)-converterLetras(xInicial))>2 || abs(yFinal - yInicial)>2) {
		if(abs(converterLetras(xFinal)-converterLetras(xInicial))>2 || abs(converterLetras(xFinal)-converterLetras(xInicial))<1 || abs(yFinal - yInicial)>2 || abs(yFinal - yInicial) < 1 ) {
			podeIr = false;
		}
		return podeIr;
		
	}

}

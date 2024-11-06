package estacionamento;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import estacionamentoObjetos.RegistroVeiculo;
import estacionamentoObjetos.Vagas;
import estacionamentoObjetos.Veiculos;

public class PrincipalEstacionamento {
	
	static Scanner scan = new Scanner(System.in);
    static List<Vagas> vagas = new ArrayList<>();
    static List<Veiculos> veiculos = new ArrayList<>();
    static List<RegistroVeiculo> registros = new ArrayList<>();

    public static void main(String[] args) {

        menuPrincipal();
    }
    
      static public void menuPrincipal() {
		
		while (true) {
			
			System.out.println("-------------------------------------------------------------");
			
			System.out.println("Escolha a Opção a seguir para navegar no sistema:");
			System.out.printf("\n");
			System.out.println("1 - Cadastrar Vaga.");
			System.out.println("2 - Cadastrar Veículo.");
			System.out.println("3 - Registrar Entrada.");
			System.out.println("4 - Registrar Saída.");
			System.out.println("5 - Relatório de vagas ocupadas.");
			System.out.println("6 - Histórico de permanência.");
			System.out.println("7 - Sair.");
			
			int escolhaUsuario = scan.nextInt();
			scan.nextLine();
			
			if(escolhaUsuario == 1) {
				cadastrarVaga();
			} else if (escolhaUsuario == 2) {
				cadastrarVeiculo();
			} else if (escolhaUsuario == 3) {
				registrarEntrada();
			} else if (escolhaUsuario == 4) {
				registrarSaida();
			} else if ( escolhaUsuario == 5) {
				relatorioVagas();
			} else if (escolhaUsuario == 6) {
				historico();
			} else if (escolhaUsuario == 7) {
				System.out.println("Deseja mesmo sar do sistema?");
				System.out.println("1 - Para sair e encerrar.");
				System.out.println("2 - Para voltar ao menu principal.");
				int escolhaSair = scan.nextInt();
				scan.nextLine();
				
				if(escolhaSair == 1) {
					System.out.println("Saindo do sistema...");
					System.out.println("Sistema encerrado.");
					
					System.exit(0);
				} else if (escolhaSair == 2) {
					menuPrincipal();
				} else {
					System.out.println("Opção inválida, tente novamente.");
					
				}
				
			} else {
				System.out.println("Opção inválida, tente novamente entre 1 a 7.");
			}
		}
		
	  }
      
      public static void cadastrarVaga() {
          Vagas vaga = new Vagas();

          System.out.print("Número da vaga: ");
          vaga.setNumero(scan.nextInt());
          scan.nextLine();

          System.out.print("Tamanho da vaga (pequeno, medio, grande): ");
          vaga.setTamanho(scan.nextLine());

          vaga.setDisponivel(true);
          vagas.add(vaga);

          System.out.println("Vaga cadastrada com sucesso!");
      }
      
      public static void cadastrarVeiculo() {
          Veiculos veiculo = new Veiculos();

          System.out.print("Placa do veículo: ");
          veiculo.setPlaca(scan.next());
          scan.nextLine(); 

          System.out.print("Modelo do veículo: ");
          veiculo.setModelo(scan.nextLine());

          System.out.print("Tamanho do veículo (pequeno, medio, grande): ");
          veiculo.setTamanho(scan.nextLine());


          veiculos.add(veiculo);
          System.out.println("Veículo cadastrado com sucesso!");
      }
      
      public static void registrarEntrada() {
          System.out.print("Placa do veículo: ");
          String placa = scan.next();

          Veiculos veiculo = encontrarVeiculo(placa);
          if (veiculo == null) {
              System.out.println("Veículo não cadastrado!");
              return;
          }

          Vagas vaga = encontrarVagaDisponivel(veiculo.getTamanho());
          if (vaga == null) {
              System.out.println("Não há vagas disponíveis para este tamanho de veículo.");
              return;
          }


          vaga.setDisponivel(false);


          RegistroVeiculo registro = new RegistroVeiculo(veiculo, vaga, LocalDateTime.now());
          registros.add(registro);

          System.out.println("Entrada registrada com sucesso! Vaga número: " + vaga.getNumero());

      }
      
      public static void registrarSaida() {

          System.out.print("Placa do veículo: ");
          String placa = scan.next();


          Veiculos veiculo = encontrarVeiculo(placa);
          if (veiculo == null) {
              System.out.println("Veículo não encontrado!");
              return;
          }


          RegistroVeiculo registro = encontrarRegistroAberto(veiculo);



          if (registro == null) {
              System.out.println("Não há registro de entrada para este veículo.");
              return;
          }

          registro.setDataSaida(LocalDateTime.now());
          Vagas vaga = registro.getVagas();
          vaga.setDisponivel(true);


          long horas = ChronoUnit.HOURS.between(registro.getDataEntrada(), registro.getDataSaida());
          double valor = calcularValor(horas);

          registro.setValorPago(valor);
          System.out.println("Saída registrada com sucesso!");
          System.out.printf("Tempo de permanência: %.0f horas%n Valor a ser pago: R$ %.2f", horas, valor );


      }
      
      public static void relatorioVagas() {
          System.out.println("\nRelatório de Vagas Ocupadas:");
          for (RegistroVeiculo registro : registros) {

              if (registro.getDataSaida() == null) {
                  Vagas vaga = registro.getVagas();
                  Veiculos veiculo = registro.getVeiculos();
                  System.out.println("Vaga: " + vaga.getNumero() +
                          ", Tamanho: " + vaga.getTamanho() +
                          ", Placa: " + veiculo.getPlaca());
              }

          }
      }
      
      public static void historico() {
          System.out.print("Placa do veículo: ");
          String placa = scan.next();
          scan.nextLine();

          Veiculos veiculo = encontrarVeiculo(placa);

          if (veiculo == null) {
              System.out.println("Veículo não encontrado!");
              return;
          }

          System.out.println("\nHistórico de Permanência - Veículo: " + veiculo.getPlaca());


          for (RegistroVeiculo registro : registros) {
              if (registro.getVeiculos().equals(veiculo)) {
                  System.out.println("Entrada: " + registro.getDataEntrada().toString());
                  if (registro.getDataSaida() != null) {
                      System.out.println("Saída: " + registro.getDataSaida().toString());
                      System.out.println("Valor Pago: R$ " + String.format("%.2f", registro.getValorPago()));
                  } else {
                      System.out.println("Saída: Ainda não registrado");
                  }
                  System.out.println("--------------------");
              }
          }
      }
      
      private static Vagas encontrarVagaDisponivel(String tamanhoVeiculo) {
          for (Vagas vaga : vagas) {
              if (vaga.isDisponivel() && vaga.getTamanho().equals(tamanhoVeiculo)) {
                  return vaga;
              }
          }
          return null;
      }
      
      private static Veiculos encontrarVeiculo(String placa) {
          for (Veiculos veiculo : veiculos) {
              if (veiculo.getPlaca().equals(placa)) {
                  return veiculo;
              }
          }
          return null;
      }
      
      private static RegistroVeiculo encontrarRegistroAberto(Veiculos veiculo) {
          for (RegistroVeiculo registro : registros) {
              if (registro.getVeiculos().equals(veiculo) && registro.getDataSaida() == null) {
                  return registro;
              }
          }
          return null;
      }
      
      private static double calcularValor(long horas) {
          if (horas <= 1) {
              return 5.0;
          } else if (horas <= 3) {
              return 10.0;
          } else {
              return 15.0;
          }
      }
      
     


}

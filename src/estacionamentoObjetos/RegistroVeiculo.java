package estacionamentoObjetos;
import java.time.LocalDateTime;

public class RegistroVeiculo {
	
	private Veiculos veiculo;
    private Vagas vaga;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private double valorPago;

    public RegistroVeiculo(Veiculos veiculo, Vagas vaga, LocalDateTime dataEntrada) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.dataEntrada = dataEntrada;
    }
    
    public Veiculos getVeiculos() {
        return veiculo;
    }


    public Vagas getVagas() {
        return vaga;
    }


    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }


    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

	@Override
	public String toString() {
		return "RegistroVeiculo [veiculo=" + veiculo + ", vaga=" + vaga + ", dataEntrada=" + dataEntrada
				+ ", dataSaida=" + dataSaida + ", valorPago=" + valorPago + "]";
	}
    
    


}

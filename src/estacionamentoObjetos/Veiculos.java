package estacionamentoObjetos;
import java.time.LocalDateTime;


public class Veiculos {
	
	private String placa;
    private String modelo;
    private String tamanho;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;


    public Veiculos() {

    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }


    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }

	@Override
	public String toString() {
		return "Veiculos [placa=" + placa + ", modelo=" + modelo + ", tamanho=" + tamanho + ", horaEntrada="
				+ horaEntrada + ", horaSaida=" + horaSaida + "]";
	}

    

}

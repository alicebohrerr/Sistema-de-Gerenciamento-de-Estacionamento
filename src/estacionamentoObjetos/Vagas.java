package estacionamentoObjetos;

public class Vagas {
	
	private int numero;
    private String tamanho;
    private boolean disponivel;



    public Vagas() {
        
    }

    public Vagas(int numero, String tamanho, boolean disponivel) {

        this.numero = numero;
        this.tamanho = tamanho;
        this.disponivel = disponivel;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

	@Override
	public String toString() {
		return "Vagas [numero=" + numero + ", tamanho=" + tamanho + ", disponivel=" + disponivel + "]";
	}
    
    

}

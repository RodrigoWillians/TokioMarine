package fin.api.transacoesfinanceiro.dto;

import java.io.Serializable;
import java.time.LocalDate;


public class TransferenciaDTO implements Serializable {
    

    private String contaOrigem;

    private String contaDestino;

    private Double valorTransferencia;

    private LocalDate dataTransferencia;


    public TransferenciaDTO(String contaOrigem, String contaDestino, Double valorTransferencia,
    LocalDate dataAgendamento, LocalDate dataTransferencia) {
    this.contaOrigem = contaOrigem;
    this.contaDestino = contaDestino;
    this.valorTransferencia = valorTransferencia;
    this.dataTransferencia = dataTransferencia;
}

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Double getValorTransferencia() {
        return valorTransferencia;
    }

    public void setValorTransferencia(Double valorTransferencia) {
        this.valorTransferencia = valorTransferencia;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }


}

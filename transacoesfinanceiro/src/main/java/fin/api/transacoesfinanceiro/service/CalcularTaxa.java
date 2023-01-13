package fin.api.transacoesfinanceiro.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import fin.api.transacoesfinanceiro.exception.TaxaCalculoException;
import fin.api.transacoesfinanceiro.model.Transferencia;

@Service
public class CalcularTaxa {
    public Long calcularDias(Transferencia tranferencia){
        Long dias = ChronoUnit.DAYS.between(LocalDate.now(), tranferencia.getDataTransferencia());
        return dias;
        
    }
    public void tipoTaxa(Long dias, Transferencia transferencia) throws TaxaCalculoException {

        Double taxaFixa;
        Double porcentagem;
        if (dias < 0 || transferencia.getValorTransferencia() <= 0) {
            throw new TaxaCalculoException("Erro ao calcular a taxa");
        } 
        else if (dias == 0 || transferencia.getValorTransferencia() <= 1000) {
            taxaFixa = 3.00;
            porcentagem = 0.03;
            transferencia.setTaxa( calcularTaxa(porcentagem, transferencia) + taxaFixa);
        }
        else if (dias <= 10 || transferencia.getValorTransferencia() <= 2000) {
            taxaFixa = 12.00;
            transferencia.setTaxa(taxaFixa);
        }
        else {
            if (dias <= 20) {
                porcentagem = 0.082;
                transferencia.setTaxa( calcularTaxa(porcentagem, transferencia));
            }
            else if (dias <= 30) {
                porcentagem = 0.069;
                transferencia.setTaxa( calcularTaxa(porcentagem, transferencia));
            }
            else if (dias <= 40) {
                porcentagem = 0.047;
                transferencia.setTaxa( calcularTaxa(porcentagem, transferencia));
            }
            else if (dias > 40) {
                porcentagem = 0.017;
                transferencia.setTaxa( calcularTaxa(porcentagem, transferencia));           
            }
        }
    }
    public Double calcularTaxa(Double porcentagem, Transferencia transferencia) {
        return (transferencia.getValorTransferencia() * porcentagem);
    }
}
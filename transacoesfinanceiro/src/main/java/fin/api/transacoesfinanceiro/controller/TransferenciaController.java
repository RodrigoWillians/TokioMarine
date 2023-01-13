package fin.api.transacoesfinanceiro.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fin.api.transacoesfinanceiro.dto.TransferenciaDTO;
import fin.api.transacoesfinanceiro.exception.TaxaCalculoException;
import fin.api.transacoesfinanceiro.model.Transferencia;
import fin.api.transacoesfinanceiro.service.CalcularTaxa;
import fin.api.transacoesfinanceiro.service.TransferenciaService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/transferencia")
public class TransferenciaController {

    @Autowired
    private CalcularTaxa _calcularTaxa;

    @Autowired
    private TransferenciaService _transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> cadastrarAgendamento(@RequestBody @Valid TransferenciaDTO dto)
            throws TaxaCalculoException {

        Transferencia transferencia = new Transferencia();
        transferencia.setContaOrigem(dto.getContaOrigem());
        transferencia.setContaDestino(dto.getContaDestino());
        transferencia.setDataAgendamento(LocalDate.now());
        transferencia.setDataTransferencia(dto.getDataTransferencia());
        transferencia.setValorTransferencia(dto.getValorTransferencia());

        Long dias = _calcularTaxa.calcularDias(transferencia);
        _calcularTaxa.tipoTaxa(dias, transferencia);
        _transferenciaService.cadastrar(transferencia);

        return ResponseEntity.status(201).body(transferencia);
    }
    
    @GetMapping
    public ResponseEntity<List<Transferencia>> ConsultarTodasTransacoes() {

        List lista = _transferenciaService.consultar();
        return ResponseEntity.status(200).body(lista);
    }
    
}

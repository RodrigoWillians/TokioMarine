package fin.api.transacoesfinanceiro.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fin.api.transacoesfinanceiro.model.Transferencia;
import fin.api.transacoesfinanceiro.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository _repository;

    public Transferencia cadastrar(Transferencia transferencia) {

        _repository.save(transferencia);
        return transferencia;
    }

    public List<Transferencia> consultar() {
        return _repository.findAll();
    }
    
    
}

package fin.api.transacoesfinanceiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fin.api.transacoesfinanceiro.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    }



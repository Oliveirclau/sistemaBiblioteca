package tech.ada.sistemabiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.sistemabiblioteca.model.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
}

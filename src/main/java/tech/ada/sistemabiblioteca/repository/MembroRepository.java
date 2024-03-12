package tech.ada.sistemabiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.ada.sistemabiblioteca.model.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long> {
}

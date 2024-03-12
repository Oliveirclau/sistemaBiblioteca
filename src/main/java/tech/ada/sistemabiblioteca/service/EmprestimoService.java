package tech.ada.sistemabiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.sistemabiblioteca.dto.EmprestimoDTO;
import tech.ada.sistemabiblioteca.model.Emprestimo;
import tech.ada.sistemabiblioteca.repository.EmprestimoRepository;
import tech.ada.sistemabiblioteca.repository.LivroRepository;
import tech.ada.sistemabiblioteca.repository.MembroRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private MembroRepository membroRepository;

    public EmprestimoDTO registrarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livroRepository.findById(emprestimoDTO.getLivroId()).orElseThrow());
        emprestimo.setMembro(membroRepository.findById(emprestimoDTO.getMembroId()).orElseThrow());
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());
        emprestimo.setDataPrevistaDevolucao(emprestimoDTO.getDataPrevistaDevolucao());
        emprestimo = emprestimoRepository.save(emprestimo);
        return convertToDTO(emprestimo);
    }

    public void registrarDevolucao(Long emprestimoId, LocalDate dataDevolucao) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow();
        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimoRepository.save(emprestimo);
    }

    public double calcularMulta(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId).orElseThrow();
        if (emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
            long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevistaDevolucao(), emprestimo.getDataDevolucao());
            return diasAtraso * 0.50; // Supondo uma multa de 0.50 por dia de atraso
        }
        return 0.0;
    }

    private EmprestimoDTO convertToDTO(Emprestimo emprestimo) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(emprestimo.getId());
        dto.setLivroId(emprestimo.getLivro().getId());
        dto.setMembroId(emprestimo.getMembro().getId());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataPrevistaDevolucao(emprestimo.getDataPrevistaDevolucao());
        dto.setDataDevolucao(emprestimo.getDataDevolucao());
        return dto;
    }
}




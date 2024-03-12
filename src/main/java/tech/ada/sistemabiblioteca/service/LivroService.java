package tech.ada.sistemabiblioteca.service;

import tech.ada.sistemabiblioteca.dto.LivroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.sistemabiblioteca.model.Livro;
import tech.ada.sistemabiblioteca.repository.LivroRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    private Livro convertToEntity(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        return livro;
    }

    private LivroDTO convertToDto(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setTitulo(livro.getTitulo());
        livroDTO.setAutor(livro.getAutor());
        return livroDTO;
    }

    public LivroDTO salvarLivro(LivroDTO livroDTO) {
        Livro livro = convertToEntity(livroDTO);
        livro = livroRepository.save(livro);
        return convertToDto(livro);
    }

    public LivroDTO atualizarLivro(LivroDTO livroDTO, Long id) {
        Livro livro = convertToEntity(livroDTO);
        livro.setId(id);
        livro = livroRepository.save(livro);
        return convertToDto(livro);
    }

    public LivroDTO buscarPorId(Long id) {
        return livroRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }
}

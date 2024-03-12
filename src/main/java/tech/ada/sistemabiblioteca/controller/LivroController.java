package tech.ada.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.sistemabiblioteca.dto.LivroDTO;
import tech.ada.sistemabiblioteca.service.LivroService;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<LivroDTO> listarTodos() {
        return livroService.listarTodos();
    }

    @PostMapping
    public LivroDTO adicionar(@RequestBody LivroDTO livroDTO) {
        return livroService.salvarLivro(livroDTO);
    }

    @PutMapping("/{id}")
    public LivroDTO atualizar(@RequestBody LivroDTO livroDTO, @PathVariable Long id) {
        return livroService.atualizarLivro(livroDTO, id);
    }

    @GetMapping("/{id}")
    public LivroDTO buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        livroService.deletarLivro(id);
    }
}

package tech.ada.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.sistemabiblioteca.dto.MembroDTO;
import tech.ada.sistemabiblioteca.service.MembroService;

import java.util.List;

@RestController
@RequestMapping("/membros")
public class MembroController {

    private final MembroService membroService;

    @Autowired
    public MembroController(MembroService membroService) {
        this.membroService = membroService;
    }

    @GetMapping
    public List<MembroDTO> listarTodos() {
        return membroService.listarTodos();
    }

    @PostMapping
    public MembroDTO adicionar(@RequestBody MembroDTO membroDTO) {
        return membroService.salvarMembro(membroDTO);
    }

    @PutMapping("/{id}")
    public MembroDTO atualizar(@RequestBody MembroDTO membroDTO, @PathVariable Long id) {
        return membroService.atualizarMembro(membroDTO, id);
    }

    @GetMapping("/{id}")
    public MembroDTO buscarPorId(@PathVariable Long id) {
        return membroService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        membroService.deletarMembro(id);
    }
}

package tech.ada.sistemabiblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.ada.sistemabiblioteca.dto.EmprestimoDTO;
import tech.ada.sistemabiblioteca.service.EmprestimoService;

import java.time.LocalDate;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public EmprestimoDTO registrarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        return emprestimoService.registrarEmprestimo(emprestimoDTO);
    }

    @PostMapping("/{id}/devolucao")
    public void registrarDevolucao(@PathVariable Long id, @RequestParam("dataDevolucao") LocalDate dataDevolucao) {
        emprestimoService.registrarDevolucao(id, dataDevolucao);
    }

    @GetMapping("/{id}/multa")
    public double calcularMulta(@PathVariable Long id) {
        return emprestimoService.calcularMulta(id);
    }
}

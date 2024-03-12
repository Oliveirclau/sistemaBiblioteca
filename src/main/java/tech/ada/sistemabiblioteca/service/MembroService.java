package tech.ada.sistemabiblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ada.sistemabiblioteca.dto.MembroDTO;
import tech.ada.sistemabiblioteca.model.Membro;
import tech.ada.sistemabiblioteca.repository.MembroRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MembroService {

    private final MembroRepository membroRepository;

    public MembroService(MembroRepository membroRepository) {
        this.membroRepository = membroRepository;
    }

    private Membro convertToEntity(MembroDTO membroDTO) {
        Membro membro = new Membro();
        membro.setId(membroDTO.getId());
        membro.setNome(membroDTO.getNome());
        membro.setEndereco(membro.getEndereco());
        return membro;
    }

    private MembroDTO convertToDto(Membro membro) {
        MembroDTO membroDTO = new MembroDTO();
        membro.setId(membroDTO.getId());
        membro.setNome(membroDTO.getNome());
        membro.setEndereco(membro.getEndereco());
        return membroDTO;
    }

    public MembroDTO salvarMembro(MembroDTO membroDTO) {
        Membro membro = convertToEntity(membroDTO);
        membro = membroRepository.save(membro);
        return convertToDto(membro);
    }

    public MembroDTO atualizarMembro(MembroDTO membroDTO, Long id) {
        Membro membro = convertToEntity(membroDTO);
        membro.setId(id);
        membro = membroRepository.save(membro);
        return convertToDto(membro);
    }

    public MembroDTO buscarPorId(Long id) {
        return membroRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public List<MembroDTO> listarTodos() {
        return membroRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void deletarMembro(Long id) {
        membroRepository.deleteById(id);
    }
}

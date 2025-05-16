package com.example.restcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class RestcrudController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // R todos
    @GetMapping
    // crud repo nao recebe lista, só iterable
    public Iterable<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // R
    @GetMapping("/{id}")
    public Usuario consultarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));
    }

    // C
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // U
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + id));

        usuarioExistente.setNome(usuarioAtualizado.getNome());


        return usuarioRepository.save(usuarioExistente);
    }

    // D
    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectosena.elsol.controlador;

import com.proyectosena.elsol.modelo.Usuario;
import com.proyectosena.elsol.repositorio.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class CrudControlador {

    @Autowired
    UsuarioRepositorio repositorioElsol;
/*crear el metodo de busqueda de usuarios*/
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> traerUsuarios(@RequestParam(required = false) String nombre) {
        try {
            List<Usuario> usuarios = new ArrayList<Usuario>();
            if (nombre == null) {
                repositorioElsol.findAll().forEach(usuarios::add);
            } else {
                repositorioElsol.findByNombre(nombre).forEach(usuarios::add);
            }
            if (usuarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        
        
    }
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        try{
            Usuario _usuario = repositorioElsol.save(new Usuario(usuario.getId(),usuario.getDocumento(),usuario.getNombre(),usuario.getCorreo(),usuario.getPassword(),usuario.getRol(),usuario.getEstado()));
            return new ResponseEntity<>(_usuario,HttpStatus.CREATED);
            
        }catch(Exception ex){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
    

}

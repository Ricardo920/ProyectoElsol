/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectosena.elsol.repositorio;
import com.proyectosena.elsol.modelo.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author user
 */
public interface UsuarioRepositorio extends JpaRepository <Usuario,Long> {
    
    List<Usuario> findByNombre(String nombre);
    void deleteByCorreo(String correo);
    
}

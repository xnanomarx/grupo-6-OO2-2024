package com.unla.grupo3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lote")
public class LoteController {

    @GetMapping("/ingresoLote")
    public String mostrarFormularioIngresoLote(){
        return "lote/ingresoLote";
    }
    /*
    @GetMapping("/register")
    public String mostrarFormularioRegistro() {
        System.out.println("Mostrando formulario de registro de producto");
        return "producto/initialRegister";
    }
     */
}

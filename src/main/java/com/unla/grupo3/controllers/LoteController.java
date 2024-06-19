package com.unla.grupo3.controllers;

import com.unla.grupo3.services.implementation.LoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lotes")
public class LoteController {

    @Autowired
    private LoteService loteService;

    @GetMapping("/lotes")
    public String verLotes(Model model){
        model.addAttribute("lotes", loteService.getAllLotes());
        return "lote/lotes";
    }

    @GetMapping("/ingresoLote")
    public String mostrarFormularioIngresoLote(){
        return "lote/ingresoLote";
    }

    @PostMapping("/actualizar-stock")
    public String actualizarStock(@RequestParam("loteId") int loteId){
        loteService.actualizarStock(loteId);
        loteService.borrarLoteActualizado(loteId);
        return "lote/confirmacionLote";
    }
}

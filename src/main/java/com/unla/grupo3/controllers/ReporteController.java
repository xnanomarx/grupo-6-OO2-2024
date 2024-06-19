package com.unla.grupo3.controllers;

import com.unla.grupo3.entities.Stock;
import com.unla.grupo3.services.implementation.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/reportes")
    public String mostrarReportes(Model model){

        Stock stockConMasStock = reporteService.getProductoConMasStock();
        model.addAttribute("stockConMasStock", stockConMasStock);
        model.addAttribute("productoConMasStock", stockConMasStock != null ? stockConMasStock.getProducto() : null);
<<<<<<< Updated upstream
        //model.addAttribute("productoConMasStock", reporteService.getProductoConMasStock());
=======
>>>>>>> Stashed changes
        model.addAttribute("totalVentas", reporteService.getTotalVentas());

        return "reportes/reportes";
    }

}

package com.adso.practica2.Controller;

import com.adso.practica2.repository.RepositoryRegistro;
import com.adso.practica2.model.Registro;
import com.adso.practica2.service.ServiceRegistro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Controller
public class   UserController {
    @Autowired
    private ServiceRegistro serviceRegistro;

    @GetMapping("/")
    public String prueba(){
        return "/pages/Index";
    }

    @GetMapping("/register/new")
    public String formRegister(Model model){
        model.addAttribute("registro" , new Registro());
        return "pages/registro";
    }

    @PostMapping("/registro")
    public String createRegister(@ModelAttribute Registro registro){
        serviceRegistro.saveRegister(registro);
        return "redirect:/register/new";
    }

    @GetMapping("/registro")
    public String listRegister(Model model){
        model.addAttribute("result", serviceRegistro.GetAllRegistro());
        return "pages/lista";
    }
    @RequestMapping("/lista")
    public String lista(Model model){
        model.addAttribute("result", serviceRegistro.GetAllRegistro());
        return "pages/lista";
    }
    @GetMapping("/registro/delete/{id}")
    public String deleteRegister(@PathVariable("id") Long id) {
        serviceRegistro.deleteRegister(id);
        return "redirect:/lista";
    }
    @GetMapping("/registro/edit/{id}")
    public String editRegistro(@PathVariable Long id, Model model) {
        Registro registro = serviceRegistro.getRegistro(id).orElseThrow(() -> new IllegalArgumentException("ID de registro inválido: " + id));
        model.addAttribute("registro", registro);
        return "fragments/editRegistro";
    }

    @PostMapping("/registro/update/{id}")
    public String updateRegister(@PathVariable Long id, @ModelAttribute Registro registro) {
        registro.setId(id);
        serviceRegistro.updateRegister(registro);
        return "redirect:/lista";
    }
    //Buscar
    @GetMapping("/registro/search")
    public String searchRegistroById(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            Optional<Registro> registro = serviceRegistro.getRegistro(id);
            if (((Optional<?>) registro).isPresent()) {
                model.addAttribute("result", Collections.singletonList(registro.get()));
            } else {
                model.addAttribute("result", Collections.emptyList());
            }
        } else {
            model.addAttribute("result", serviceRegistro.GetAllRegistro());
        }
        return "pages/lista";
    }

}

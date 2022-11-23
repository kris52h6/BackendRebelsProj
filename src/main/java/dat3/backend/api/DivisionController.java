package dat3.backend.api;

import dat3.backend.dto.DivisionDTO;
import dat3.backend.service.DivisionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/divisions")
@CrossOrigin
public class DivisionController {
    DivisionService divisionService;

    public DivisionController(DivisionService divisionService){
        this.divisionService = divisionService;
    }

    @GetMapping()
    public List<DivisionDTO> getAllDivisions(){
        return divisionService.getAllDivisions();
    }

}

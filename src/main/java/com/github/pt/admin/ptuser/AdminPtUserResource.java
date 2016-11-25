package com.github.pt.admin.ptuser;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/pt-user")
class AdminPtUserResource {

    private final AdminPtUserService ptUserService;
    
    @Autowired
    AdminPtUserResource(AdminPtUserService ptUserService) {
        this.ptUserService = ptUserService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<PtUserResponseDTO> findAll() {
        return ptUserService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    PtUserResponseDTO findOne(@PathVariable Long id) {
        return ptUserService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    PtUserResponseDTO create(@RequestBody PtUserRequestDTO ptUserRequestDTO) {
        return ptUserService.create(ptUserRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    PtUserResponseDTO update(@PathVariable Long id, @RequestBody PtUserRequestDTO ptUserRequestDTO) {
        return ptUserService.update(id, ptUserRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    PtUserResponseDTO delete(@PathVariable Long id) {
        return ptUserService.delete(id);
    }

}

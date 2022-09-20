package com.israel.bookstore.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.israel.bookstore.dtos.CepDTO;
import com.israel.bookstore.service.CepService;

@CrossOrigin("*")
@RestController
//@RequestMapping(value = "/consultaCep")
public class CepResources {

    @Autowired
	private CepService cepService;

    @ResponseBody
    @GetMapping(value = "/consultaCep/{cep}")
    public ResponseEntity<CepDTO> consultaCep(@PathVariable("cep") String cep){

       // CepDTO cepDTO = cepService.consultaCep(cep);

      return new ResponseEntity<CepDTO>(cepService.consultaCep(cep), HttpStatus.OK);
    }
    

}

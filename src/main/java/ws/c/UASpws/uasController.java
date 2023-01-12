/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.UASpws;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Abian Ayatullah Fikri
 * 20200140130
 * 
 */
@RestController
@ResponseBody
public class uasController {
    
    // Membuat objek untuk JPA Controller
    FinaluasJpaController dctrl = new FinaluasJpaController();
    
    
    // Membuat Method GET
    @GetMapping("/GET")
    public List<Finaluas> Data(){
        
        // Membuat Array list untuk menampilkan data pada database
        List<Finaluas> list = new ArrayList<>();
        
        try{
            list = dctrl.findFinaluasEntities();
            
            
        }catch(Exception e){
            
        }
        return list;
    }
    
    // Membuat Method  Post mapping data untuk menambahkan data
    @PostMapping("/POST")
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        // Membuat objek baru untuk finaluas
        Finaluas data = new Finaluas();
        
        
        data= mapper.readValue(json_receive, Finaluas.class);
        dctrl.create(data); // memanggil method create pada JPA controller
        
        
        return json_receive;
    }
    
    // Membuat Method PUT mapping untuk edit data
    @PutMapping("/PUT")
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        Finaluas data = new Finaluas();
        
        data= mapper.readValue(json_receive, Finaluas.class);
        dctrl.edit(data); // memanggil method edit pada jpa Controller
        
        
        return json_receive;
    }
    
    // Membuat method Delete mapping data untuk menghapus data pada database
    @DeleteMapping("/DELETE")
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        String message = "no action";
        
        ObjectMapper mapper = new ObjectMapper();
        Finaluas data = new Finaluas();
        
        data= mapper.readValue(json_receive, Finaluas.class);
        dctrl.destroy(data.getId()); // memanggil method destroy atau hapus pada jpa Controller
        
        message = "id = " + data.getId().toString() + " Deleted"; // membuat pesan notifikasi untuk pengguna
        
        return message;
    }
    
    
    
}

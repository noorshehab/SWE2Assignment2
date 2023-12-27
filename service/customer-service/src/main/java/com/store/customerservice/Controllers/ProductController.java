package com.store.customerservice.Controllers;

import com.store.customerservice.Models.LineItem;
import com.store.customerservice.Models.Product;
import com.store.customerservice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
   @PostMapping("/addNew")
    public LineItem AddNewProduct(@RequestBody Product newp){
       return productService.createProduct(newp);
   }
   @PutMapping("/updateQuantity/{serialnumber}/{newquantity}")
    public LineItem UpdateQuantity(@PathVariable("serialnumber") String num,@PathVariable("newquantity") int newq ){
       return productService.ChangeQuantity(num,newq);
   }

   @GetMapping("/AllProducts")
    public Collection<LineItem> GetAllItems(){
       return productService.getAllLineItems();
   }
}

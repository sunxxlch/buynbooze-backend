package com.buynbooze.CheckoutService.Controllers;

import com.buynbooze.CheckoutService.DTO.OrderDTO;
import com.buynbooze.CheckoutService.DTO.ResponseDTO;
import com.buynbooze.CheckoutService.Entities.CheckoutEntity;
import com.buynbooze.CheckoutService.Services.CheckoutIMPL;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutIMPL checkoutIMPL;

    public CheckoutController(CheckoutIMPL checkoutIMPL) {
        this.checkoutIMPL = checkoutIMPL;
    }

    @PostMapping("/placeOrder/{transactionId}")
    public ResponseEntity<ResponseDTO> placeOrder(@PathVariable Long transactionId,HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .orderId(checkoutIMPL.placeOrder(transactionId,request))
                        .statusMsg("Order Placed Successfully")
                        .build()
        );
    }
    
    @PutMapping("/updateOrder")
    public ResponseEntity<ResponseDTO> updateOrderAddress(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .orderId(checkoutIMPL.updateAddress(orderDTO))
                        .statusMsg("Address is update")
                        .build()
        );
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable Long orderId){
        checkoutIMPL.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .orderId(orderId)
                        .statusMsg("Order Cancelled")
                        .build()
        );
    }

    @PutMapping("/updateStatus/{orderId}")
    public ResponseEntity<Object> updatestatus(@PathVariable Long orderId){
        checkoutIMPL.updateStatus(orderId);
        return  ResponseEntity.ok("updated status");
    }

    @PostMapping("/getOrders")
    public List<CheckoutEntity> getAllOrders(@RequestBody List<Long> orderIds){
        return checkoutIMPL.getAllOrderDetails(orderIds);
    }

}
package com.chan.customer.client;

import com.chan.customer.common.Message;
import com.chan.customer.dto.SellerOrderRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="seller", url="${seller.ribbon.listOfServers}")
public interface SellerClient {

    @PostMapping("/seller/order")
    Message requestOrder(@RequestBody SellerOrderRequestDto sellerOrderRequestDto);

}

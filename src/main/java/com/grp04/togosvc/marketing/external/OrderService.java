package com.grp04.togosvc.marketing.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="Order", url="http://order:8080")
//@FeignClient(name="order", url="http://localhost:8082")
public interface OrderService {
    @RequestMapping(method= RequestMethod.GET, path="/orders/search/removeByPlanId?planId={id}")
    public void returnToGo(@PathVariable long id);
}
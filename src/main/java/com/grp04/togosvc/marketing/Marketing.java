package com.grp04.togosvc.marketing;

import java.util.Date;
import javax.persistence.*;

import com.grp04.togosvc.marketing.external.Order;
import com.grp04.togosvc.marketing.external.OrderService;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Marketing_table")
public class Marketing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private Long planQty;
    private Date startDate;
    private Date endDate;
    private String planTitle;

    @PostPersist
    public void onPostPersist(){
        ServiceStarted serviceStarted = new ServiceStarted();
        BeanUtils.copyProperties(this, serviceStarted);
        serviceStarted.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove(){

        Order order = new Order();
        order.setPlanId(this.id);
        order.setProductId(this.productId);
        order.setReturnDate(this.endDate);

        OrderService orderService = MarketingApplication.applicationContext.getBean(OrderService.class);
        orderService.returnToGo(this.id);

        System.out.println("\n\n##### Marketing: Marketing " + this.id + " ended. Remove all associated orders.\n\n");

        // ServiceEnded serviceEnded = new ServiceEnded();
        // BeanUtils.copyProperties(this, serviceEnded);
        // serviceEnded.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getPlanQty() {
        return planQty;
    }

    public void setPlanQty(Long planQty) {
        this.planQty = planQty;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }
}

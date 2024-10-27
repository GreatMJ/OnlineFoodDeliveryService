package com.example.OnlineFoodDeliveryService.service;

import com.example.OnlineFoodDeliveryService.dto.request.OrderRequest;
import com.example.OnlineFoodDeliveryService.dto.request.OrderedItemRequest;
import com.example.OnlineFoodDeliveryService.dto.response.OrderResponse;
import com.example.OnlineFoodDeliveryService.dto.response.OrderedItemResponse;
import com.example.OnlineFoodDeliveryService.enums.OrderStatus;
import com.example.OnlineFoodDeliveryService.exceptions.ResourceNotFoundException;
import com.example.OnlineFoodDeliveryService.models.*;
import com.example.OnlineFoodDeliveryService.repository.*;
import com.example.OnlineFoodDeliveryService.transformer.OrderItemTransformer;
import com.example.OnlineFoodDeliveryService.transformer.OrderTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private  final RestrauntRepository restrauntRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuCardRepository menuCardRepository;

   // method to place the order

  public String placeoOrder(OrderRequest orderRequest){
      // fetch the customer
      Customer customer=customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(()->new ResourceNotFoundException(String.format("Customer with id: %d not found. Verify id and try again.",orderRequest.getCustomerId())));

      // fetch restraunt
      Restraunt restraunt=restrauntRepository.findById(orderRequest.getRestrauntId()).orElseThrow(()->new ResourceNotFoundException(String.format("Restraunt with id: %d not found. Verify id and try again.",orderRequest.getRestrauntId())));
      String restrauntName=restraunt.getName();
      // fetch menu card
      MenuCard menuCard=restraunt.getMenuCard();
      int menuCardId=menuCard.getId();

      // create order
      Order order=new Order();
      // create list of order items
      List<OrderItem> orderItemList=createOrderItemList(orderRequest.getOrderedItems(),menuCardId,order,restrauntName);

      // set the properties
      order.setCustomer(customer);
      order.setRestraunt(restraunt);
      order.setOrderStatus(OrderStatus.CONFIRMED);
      order.setOrderTime(LocalDateTime.now());
      order.setAddress(customer.getAddress());
      order.setAmount(calculateTotalAmount(orderItemList));
      order.setOrderItemList(orderItemList);

      // save the order
      orderRepository.save(order);

      return "Order placed successfully!";
  }

// helper method to create list of order items
  private List<OrderItem> createOrderItemList(List<OrderedItemRequest> orderedItemRequestList,int menuCardId,Order order,String restrauntName){
      List<OrderItem> orderItemList=new ArrayList<>();

            for(OrderedItemRequest orderedItemRequest:orderedItemRequestList){
                // fetch menu item
          int menuItemId=orderedItemRequest.getMenuItemId();
          MenuItem menuItem=menuItemRepository.getMenuItemFromMenuCardById(menuCardId,menuItemId).orElseThrow(()->new ResourceNotFoundException(String.format("MenuItem with id: %s not found in menucard of %s.",menuItemId,restrauntName)));
          // create order item
          OrderItem orderItem=new OrderItem();
          orderItem.setName(menuItem.getName());
          orderItem.setQuantity(orderedItemRequest.getQuantity());
          orderItem.setPrice(menuItem.getPrice()*orderedItemRequest.getQuantity());
          orderItem.setOrder(order);

          // add order in list
                orderItemList.add(orderItem);
      }

            // return the list;
      return orderItemList;
  }

  // helper method to calculate total amount of the order
    public float calculateTotalAmount(List<OrderItem> orderItemList){
      float totalAmount=0;

      for(OrderItem orderItem:orderItemList){
          totalAmount+=orderItem.getPrice();
      }

      return totalAmount;
    }



    // method to get order by id

    public OrderResponse getOrderById(UUID id){
      // fetch the order from repository
        Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Order with id: %s not found.",id)));

        // create orderItemResponseList
        List<OrderedItemResponse> orderedItemResponseList=createOrderItemResponseList(order.getOrderItemList());

        // convert order to order response dto
        OrderResponse orderResponse=OrderTransformer.orderToOrderResponse(order,orderedItemResponseList);

        return orderResponse;

    }


    // helper method to create orderItemResponse's list from orderItem list

    private List<OrderedItemResponse> createOrderItemResponseList(List<OrderItem> orderItemList){
      List<OrderedItemResponse> orderedItemResponseList=new ArrayList<>();

      for(OrderItem orderItem:orderItemList){
          // convert orderItem to orderItemResponse
          OrderedItemResponse orderedItemResponse= OrderItemTransformer.orderItemToOrderItemResponse(orderItem);
          orderedItemResponseList.add(orderedItemResponse);
      }

      return orderedItemResponseList;
    }


    // method to delete order by id

    public String deleteOrderById(UUID id){
      // fetch the order
      Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Order with id: %s not found",id)));
      // delete the order
        orderRepository.delete(order);
        return "Order deleted";
    }


    // method to get all orders placed by specific customer
    public List<OrderResponse> getCustomerOrders(int id){
      // fetch the customer
        Customer customer=customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Customer with id: %s not found.",id)));
        // fetch the orders
        List<Order> orders=customer.getOrderList();
        if(orders==null||orders.isEmpty()) return Collections.emptyList();
        // initialize the list for orderResponse
        List<OrderResponse> orderResponseList=new ArrayList<>();
        //conver all orders to order response list;
        for(Order order:orders){
            // first create orderItemResponseList
            List<OrderedItemResponse> orderedItemResponseList=createOrderItemResponseList(order.getOrderItemList());
            // using transformer convert order to orderResponse
            OrderResponse orderResponse=OrderTransformer.orderToOrderResponse(order,orderedItemResponseList);
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }


    // method to cancel the order
    public void cancelOrderById(UUID id){
      //fetch the order from repository
      Order order=orderRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format("Order with id: %s not found.",id)));

      // mark the order cancelled
        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

    }
}

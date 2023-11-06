package com.stock.stock.Controller;

import com.stock.stock.Service.EstoqueService;
import com.stock.stock.Service.OrderService;
import com.stock.stock.entity.Conta;
import com.stock.stock.entity.Order;
import com.stock.stock.repository.ContaRepository;
import com.stock.stock.repository.OrderRepository;
import com.stock.stock.responses.OrderNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/notify")

public class     Notify {


    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public ContaRepository contaRepository;


    @Autowired
    public OrderService service;

    @Autowired
    public EstoqueService estoqueService;


    @PostMapping()
    public ResponseEntity  NOTIFY(
            @RequestBody OrderNotify orderNotify
            ) {
        System.out.println("TESTE NOTIFY TESTE");
        System.out.println(orderNotify.getResource());
        System.out.println(orderNotify.getTopic());
        System.out.println(orderNotify.getApplication_id());
        System.out.println(orderNotify.getUser_id());

        if (orderNotify.getTopic().contains("orders_v2")){

            String mlbId = orderNotify.getResource().substring(8);
            Long contaid = orderNotify.getUser_id();

            Optional<Conta> conta = contaRepository.findContaByContaid(contaid);

            Optional<Order> optionalOrder = orderRepository.findByMlbid(Long.valueOf(mlbId));

                //se a order ja existir
            if ( !optionalOrder.isPresent()){

               Order novaOrder =  service.newOrder(Long.valueOf(mlbId), conta.get());
                estoqueService.AtualizaEstoque(novaOrder);

            }
            else  {
//                orderRepository.save(optionalOrder.get());
//                estoqueService.AtualizaEstoque(optionalOrder.get());
                return ok().build();
            }



        }

        return ok().build();

    };


    ///////////////  NOTIFY ///////////////
    //COMPRA
//    {
//        "id": 2000006582420036,
//            "date_created": "2023-10-02T13:05:34.000-04:00",
//            "last_updated": "2023-10-02T13:21:57.000-04:00",
//            "expiration_date": "2023-10-30T13:05:35.000-04:00",
//            "date_closed": "2023-10-02T13:05:35.000-04:00",
//            "comment": null,
//            "pack_id": null,
//            "pickup_id": null,
//            "fulfilled": null,
//            "hidden_for_seller": null,
//            "buying_mode": "buy_equals_pay",
//            "shipping_cost": null,
//            "application_id": null,
//            "mediations": [
//        {
//            "id": 5220269673
//        }
//    ],
//        "total_amount": 999.00,
//            "paid_amount": 999.00,
//            "coupon": {
//        "amount": 0.00,
//                "id": null
//    },
//        "order_items": [
//        {
//            "item": {
//            "id": "MLB3463946927",
//                    "title": "Item De Teste Por Favor, Não Ofertar! Mouse De Muié",
//                    "category_id": "MLB3530",
//                    "variation_id": null,
//                    "seller_custom_field": null,
//                    "variation_attributes": [],
//            "warranty": "Sem garantia",
//                    "condition": "new",
//                    "seller_sku": "MMUIE",
//                    "global_price": null,
//                    "net_weight": null
//        },
//            "quantity": 1,
//                "requested_quantity": {
//            "value": 1,
//                    "measure": "unit"
//        },
//            "picked_quantity": null,
//                "unit_price": 999.00,
//                "full_unit_price": 999.00,
//                "currency_id": "BRL",
//                "manufacturing_days": null,
//                "sale_fee": 0.00,
//                "listing_type_id": "free",
//                "base_exchange_rate": null,
//                "base_currency_id": null,
//                "element_id": null,
//                "discounts": null,
//                "bundle": null,
//                "compat_id": null
//        }
//    ],
//        "currency_id": "BRL",
//            "payments": [
//        {
//            "id": 64614689490,
//                "order_id": 2000006582420036,
//                "payer_id": 1495425250,
//                "collector": {
//            "id": 1496386128
//        },
//            "card_id": null,
//                "reason": "Item De Teste Por Favor, Não Ofertar! Mouse De Muié",
//                "site_id": "MLB",
//                "payment_method_id": "master",
//                "currency_id": "BRL",
//                "installments": 1,
//                "issuer_id": "24",
//                "atm_transfer_reference": {
//            "transaction_id": null,
//                    "company_id": null
//        },
//            "coupon_id": null,
//                "activation_uri": null,
//                "operation_type": "regular_payment",
//                "payment_type": "credit_card",
//                "available_actions": [
//            "refund"
//            ],
//            "status": "approved",
//                "status_code": null,
//                "status_detail": "accredited",
//                "transaction_amount": 999.00,
//                "transaction_amount_refunded": 0.00,
//                "taxes_amount": 0.00,
//                "shipping_cost": 0.00,
//                "coupon_amount": 0.00,
//                "overpaid_amount": 0.00,
//                "total_paid_amount": 999.00,
//                "installment_amount": 999.00,
//                "deferred_period": null,
//                "date_approved": "2023-10-02T13:05:35.000-04:00",
//                "transaction_order_id": null,
//                "date_created": "2023-10-02T13:05:35.000-04:00",
//                "date_last_modified": "2023-10-02T13:05:35.000-04:00",
//                "marketplace_fee": 0.00,
//                "reference_id": null,
//                "authorization_code": "301299"
//        }
//    ],
//        "shipping": {
//        "id": 42665489622
//    },
//        "status": "paid",
//            "status_detail": null,
//            "tags": [
//                 "paid",
//                "not_delivered",
//                "test_order"
//    ],
//        "internal_tags": [],
//        "feedback": {
//        "seller": null,
//                "buyer": null
//    },
//        "context": {
//        "channel": "marketplace",
//                "site": "MLB",
//                "flows": [],
//        "application": null,
//                "product_id": null,
//                "store_id": null
//    },
//        "seller": {
//        "id": 1496386128
//    },
//        "buyer": {
//        "id": 1495425250,
//                "nickname": "TESTUSER114483680",
//                "first_name": "Test",
//                "last_name": "Test"
//    },
//        "taxes": {
//        "amount": null,
//                "currency_id": null,
//                "id": null
//    },
//        "cancel_detail": null,
//            "manufacturing_ending_date": null,
//            "order_request": {
//        "change": null,
//                "return": null
//    }
//    }

}

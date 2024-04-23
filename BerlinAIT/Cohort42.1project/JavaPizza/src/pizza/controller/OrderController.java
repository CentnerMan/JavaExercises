package pizza.controller;

import pizza.domain.*;
import pizza.service.CustomerService;
import pizza.service.ExtComponentService;
import pizza.service.OrderService;
import pizza.service.PizzaService;

import java.util.Scanner;

/**
 * Pizza service controller
 * Implements a menu for working with order service
 *
 * @author Sergey Iryupin
 * @version 18-Apr-24
 */
public class OrderController {
    private OrderService orderService;
    private CustomerService customerService;
    private PizzaService pizzaService;
    private ExtComponentService extComponentService;
    private Scanner scanner;

    public OrderController(OrderService orderService, CustomerService customerService, PizzaService pizzaService,
                           ExtComponentService extComponentService, Scanner scanner) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.pizzaService = pizzaService;
        this.extComponentService = extComponentService;
        this.scanner = scanner;
    }

    public void run() {
        char cmd;
        int id;
        do {
            System.out.print("Order service: [a]dd, [u]pdate, [d]elete, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'a':
                    System.out.print("Order service: add order: customer id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    Customer customer = customerService.get(id);
                    orderService.add(customer);
                    break;
                case 'u':
                    System.out.print("Order service: update: order id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    orderControllerUpdate(id);
                    break;
                case 'd':
                    System.out.print("Order service: delete: order id: ");
                    id = Integer.valueOf(scanner.nextLine());
                    orderService.delete(id);
                    break;
                case 'p':
                    orderService.print();
                    break;
                case 'b':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'b');
    }

    private void orderControllerUpdate(int orderId) {
        Order order = orderService.get(orderId);
        int pizzaId, orderPizzaId, componentId, price;
        ExtСomponent component;
        String[] input;
        char cmd;
        do {
            System.out.print("Order update: add p[i]zza | [c]omponent, delete pi[z]za | c[o]mponent, [s]et state, s[e]t delivery price, [p]rint, [b]ack: ");
            cmd = scanner.nextLine().charAt(0);
            switch (cmd) {
                case 'i':
                    System.out.print("Order update: add pizza: pizzaId: ");
                    pizzaId = Integer.valueOf(scanner.nextLine().trim());
                    Pizza pizza = pizzaService.get(pizzaId);
                    orderService.addOrderPizza(order, pizza);
                    break;
                case 'c':
                    System.out.print("Order update: add component: orderPizzaId & componentId: ");
                    input = scanner.nextLine().split("&");
                    orderPizzaId = Integer.valueOf(input[0].trim());
                    componentId = Integer.valueOf(input[1].trim());
                    component = extComponentService.get(componentId);
                    orderService.addExtСomponent(order, orderPizzaId, component);
                    break;
                case 'z':
                    System.out.print("Order update: delete order pizza: orderPizzaId: ");
                    orderPizzaId = Integer.valueOf(scanner.nextLine().trim());
                    orderService.deleteOrderPizza(order, orderPizzaId);
                    break;
                case 'o':
                    System.out.print("Order update: delete component: orderPizzaId & componentId: ");
                    input = scanner.nextLine().split("&");
                    orderPizzaId = Integer.valueOf(input[0].trim());
                    componentId = Integer.valueOf(input[1].trim());
                    component = extComponentService.get(componentId);
                    orderService.deleteExtСomponent(order, orderPizzaId, component);
                    break;
                case 'e':
                    System.out.print("Order update: set delivery price: price: ");
                    price = Integer.valueOf(scanner.nextLine().trim());
                    orderService.setDeliveryPrice(order, price);
                    break;
                case 's':
                    System.out.print("Order update: set order status: [n]ew, [p]aid, [r]eceived, [c]anceled: ");
                    char charState = scanner.nextLine().trim().charAt(0);
                    orderService.setState(order, charState);
                    break;
                case 'p':
                    System.out.println(order);
                    break;
                case 'b':
                    break;
                default:
                    System.out.println("Unrecognized command: " + cmd);
            }
        } while (cmd != 'b');
    }
}

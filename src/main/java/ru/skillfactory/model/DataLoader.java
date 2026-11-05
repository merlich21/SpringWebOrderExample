//package ru.skillfactory.model;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ru.skillfactory.repository.OrderService;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    private final OrderService repo;
//
//    public DataLoader(OrderService repo) {
//        this.repo = repo;
//    }
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        MyOrder order = new MyOrder();
//
////        order.setId(1L);
//        order.setName("first");
//        repo.save(order);
//        System.out.println(repo.count());
//    }
//}

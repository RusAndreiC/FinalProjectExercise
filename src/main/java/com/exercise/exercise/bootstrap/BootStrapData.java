package com.exercise.exercise.bootstrap;//package ro.sda.echipa3.bootstrap;
//
//import org.hibernate.dialect.DB2Dialect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import ro.sda.echipa3.model.product.Product;
//import ro.sda.echipa3.model.product.ProductCategory;
//import ro.sda.echipa3.model.user.Address;
//import ro.sda.echipa3.model.user.User;
//import ro.sda.echipa3.repository.*;
//
//import java.util.List;
//import java.util.Properties;
//
//@Component
//public class BootStrapData implements CommandLineRunner {
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private ProductRepository productRepository;
//    @Autowired
//    private ProductCategoryRepository productCategoryRepository;
//    @Autowired
//    private AddressRepository addressRepository;
//    @Autowired
//    private UserRepository userRepository;
//
//
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Product pizza = new Product( 30, "Pizza Al Tonno", "Sos roșii," +
//                " mozzarella, ton, măsline kalamata, ceapă, ulei de măsline." +
//                " Din aer, apă și pământ, am cules de peste tot ingrediente pentru a te încânta cu o pizza gustoasă." +
//                " Are mozzarella, ton, măsline, ceapă și acea aromă deplină pe care doar " +
//                "combinația potrivită de ingrediente o poate reda. Te lăsăm pe tine să descoperi Pizza Al Tono!", "http", 29.9);
//
//        Product burger = new Product(20, "Burger", "Carne vită, bacon, brânză Gouda, salată verde," +
//                " ceapă roșie, roșii, castraveți murați, ketchup, maioneză. Varianta perfectă de hamburger o creăm strat cu strat," +
//                " cântărind ingredientele, alăturând aromele, condimentând totul la final pentru acel efect de ”wow - cât e de bun!”", "http", 33.5);
//
//        Product salad = new Product(25, "Salata Caesar", "Salată verde, anchois, piept de pui grill, crutoane, iaurt." +
//                " Salata Caesar e una dintre cele mai celebre salate din lume, fiind compusă din salată verde, crutoane, anchois," +
//                " iaurt și piept de pui făcut ușor pe grătar!", "http", 23.6);
//
//        Product cheesecake = new Product(16, "Cheesecake", "Biscuiţi, mascarpone, ouă, zahăr, fructe pădure, vanilie." +
//                " Desertul este ca un basm al bucătăriei mereu după ce termini să-l mănânci simți acel „final fericit” de care toți eroii au parte.", "http", 16.7);
//
//        ProductCategory productCategory1 = new ProductCategory();
//        productCategoryRepository.save(productCategory1);
//
//        pizza.setCategory(productCategory1);
//
//        productRepository.save(pizza);
//        productRepository.save(burger);
//        productRepository.save(salad);
//        productRepository.save(cheesecake);
//
//        Address address1 = new Address();
//        address1.setCity("Buc");
//        address1.setStreet("Ceva");
//        address1.setStreetNumber(2);
//        address1.setBlock("1");
//        address1.setAp("5c");
//        addressRepository.save(address1);
//
//        User user1 = new User();
//        user1.setEmail("asd");
//        user1.setPassword("asdasd");
//        userRepository.save(user1);
//
//        address1.setUser(user1);
//        addressRepository.save(address1);
//
//        //intai faci obiectele (user) si le salvezi in DB
//        //2 faci legatura intre ele: adaugand
//
//        //formular definire produs
//        //html, add, define
//
//TODO dupa ce facem DB init - fiecare cate un sql
//TODO facem rest controllere de PRODUCT INTAI ca sa legam de ce face Teo V
//TODO order, facem controllere pentru order add to cart etc
//
////        user1.setAddressList();
//
//
////        CartEntry cartEntry1 = new CartEntry();
////        cartEntry1.setProduct(pizza);
////        cartEntry1.setQuantity(2);
////        cartEntryRepository.save(cartEntry1);
////        Cart cart1 = new Cart(OrderStatus.RECEIVED, 1);
////        cart1.getEntries().add(cartEntry1);
////        cartRepository.save(cart1);
////
////        Order order1 = new Order(10.0, address1, cart1, user1, OrderStatus.RECEIVED);
//
////        orderRepository.save(order1);
//
//    //TODO - initializarea bazei de ddate se face cu sql-uri, vezi resursele de pe baelung
//
//
//
//
//
//    }
//}

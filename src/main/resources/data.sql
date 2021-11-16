insert into product_category
values (1, 'Pizza');
insert into product_category
values (3, 'Burgeri');
insert into product_category
values (2, 'Salate');
insert into product_category
values (4, 'Deserturi');
insert into product
values (1, 'Sos roșii, mozzarella, ton, măsline kalamata, ceapă, ulei de măsline. Din aer, apă și pământ,
        am cules de peste tot ingrediente pentru a te încânta cu o pizza gustoasă. Are mozzarella, ton, măsline,
        ceapă și acea aromă deplină pe care doar combinația potrivită de ingrediente o poate reda. Te lăsăm pe tine să descoperi Pizza Al Tono!',
        'Pizza Al Tonno', 35, 15, 'https://cdn.pixabay.com/photo/2017/12/09/08/18/pizza-3007395__480.jpg', 1);
insert into product
values (2, 'Salată verde, anchois, piept de pui grill, crutoane, iaurt.
            Salata Caesar e una dintre cele mai celebre salate din lume, fiind compusă din salată verde, crutoane, anchois,
            iaurt și piept de pui făcut ușor pe grătar!', 'Salata Caesar', 23, 50, 'https://static4.libertatea.ro/wp-content/uploads/2017/08/38662643_l.jpg', 3);
insert into product
values (3, 'Carne vită, bacon, brânză Gouda, salată verde,
                 ceapă roșie, roșii, castraveți murați, ketchup, maioneză. Varianta perfectă de hamburger o creăm strat cu strat,
           cântărind ingredientele, alăturând aromele, condimentând totul la final pentru acel efect de ”wow - cât e de bun!”',
        'Burger', 33.5, 12, 'https://media-cdn.tripadvisor.com/media/photo-s/0b/71/fd/db/aria-gourmet-burger.jpg', 2);
insert into product
values (4,
        'Biscuiţi, mascarpone, ouă, zahăr, fructe pădure, vanilie. Desertul este ca un basm al bucătăriei mereu după ce termini să-l mănânci simți acel „final fericit” de care toți eroii au parte',
        'Cheesecake',
        16.7, 20, 'https://static.onecms.io/wp-content/uploads/sites/43/2020/06/04/cheesecake-topped-with-berries-photo-by-verdina-anna-GettyImages-1145682957-cropped.jpg', 4);

-- parola1
insert into user
values (1, 'gogu@gmail.com', 'gogu', '$2a$10$SoH3pawKCTAlMjDA5GKPpOU3bFodACbndY6x4l49Z1WzfochfTT2O', 'SMS', 'ADMIN', 'https');
-- love
insert into user
values (2, 'camelia@fanel.com', 'Cami', '$2a$10$4pWDP/awmmmJHeXNndeYpeWLmdm9BFF5h5QtTlMP6Ezt0A0.oJpAm', 'EMAIL', 'USER', 'https');

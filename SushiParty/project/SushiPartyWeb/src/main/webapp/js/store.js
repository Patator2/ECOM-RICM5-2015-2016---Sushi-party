//----------------------------------------------------------------
// classe boutique (contient les produits)
//----------------------------------------------------------------
function store() {
    this.products = [
        new product("Lunch Boxes", "Lunch Box Du Mois By Marie Luv Pink", "Salade de Choux, Edamame, 4 Sashimi Saumon, 6 Spring Concombre Cheese, 3 California Ebi Tempura, 3 Salmon Roll", 16, 14.90),
        new product("Lunch Boxes", "Lunch box A", "Salade de Choux, Edamame, 3 maki salmon roll, 3 maki concombre, 3 spring roll saumon avocat, 3 california saumo avocat", 12, 11.90),
        new product("Lunch Boxes", "Lunch box B", "Salade de Choux, Edamame, 6 California Saumon Avocat, 5 Sushi Saumon", 12, 11.90),
        new product("Lunch Boxes", "Lunch box C", "Salade de Choux, Edamame, 3 California Surimi Avocat, 3 Spring Avocat Cheese, 3 Maki Saumon, 3 Sushi Saumon", 12, 11.90),
        new product("Lunch Boxes", "Lunch box D", "Salade de Choux, Edamame, 6 California Saumon Avocat, 3 Spring Saumon Avocat, 3 Maki Saumon, 3 Sushi Saumon", 12, 14.90),
        new product("Signature Rolls", "Rock n Roll", "Dessus : Saumon Laqué, Chips, Piment, Sauce Teriyaki. À L’Intérieur : Thon, Asperge, Avocat, Masago, Sauce Épicée", 8, 9.90),
        new product("Signature Rolls", "Fire Roll", "Dessus : Thon, Sauce Épicée. À L’Intérieur : Avocat, Crevette Tempura, Sauce Mayonnaise Ponzu.", 8, 9.90),
        new product("Signature Rolls", "Rainbow Roll", "Dessus : Saumon, Thon, Avocat, Piment. À L’Intérieur : Chair De Crabe, Avocat, Sauce Mayonnaise Ponzu.", 8, 11.90),
        new product("Signature Rolls", "Dragon Roll", "Dessus : Avocat, Oeufs De Saumon, Sauce Mayonnaise Teriyaki, Piment. À L’Intérieur : Ceviche Daurade.", 8, 11.90),
        new product("Spring Rolls", "Spring Rolls Saumon Avocat", "Le Spring rolls Saumon est une vraie bouchée de plaisir avec la menthe et la coriandre accentuant le goût du saumon et de l'avocat.", 6, 6.50),
        new product("Spring Rolls", "Spring Rolls Avocat cheese", "Succombez à l'onctuosité du Spring rolls Avocat Cheese et faites de votre dégustation un moment de plaisir.", 6, 4.90),
        new product("Spring Rolls", "Spring Rolls Fried chicken Avocat", "Laissez vous séduire par l'onctuosité de l'avocat et le croustillant du poulet frit avec le Spring rolls Fried Chicken Avocat.", 6, 6.50),
        new product("Spring Rolls", "Spring Rolls Concombre cheese", "Alliant la fraîcheur du cheese et le croquant du concombre, le Spring rolls Concombre Cheese ravira vos papilles.", 6, 4.90),
        new product("California Rolls", "California Saumon avocat", "L'onctuosité de l'avocat associé au saumon fait du california rolls Saumon Avocat un met savoureux qui ravira vos papilles.", 6, 4.90),
        new product("California Rolls", "California Chicken caesar", "Le California rolls Chicken Caesar est un réel plaisir tant pour vos yeux que pour vos papilles. Alliant le croustillant et la douceur du cœur de laitue, laissez-vous séduire par le Chicken Caesar.", 6, 6.50),
        new product("California Rolls", "California Pacific", "Le California rolls Pacific est l'alliance parfaite entre fondant et piquant, son délicieux tartare de saumon parfaitement relevé à la sauce spicy réveillera vos papilles.", 6, 5.50),
        new product("California Rolls", "California Thon cuit avocat", "Le california rolls Thon Cuit Avocat est une alternative au california rolls Thon Avocat, tout autant savoureux mais plus onctueux.", 6, 4.90),
        new product("Maki", "Maki saumon", "Le Maki Saumon est à l'image du concept, simple, frais et de qualité. Succombez à cette bouchée de fraîcheur.", 6, 4.50),
        new product("Maki", "Maki Cheese avocat", "Alliant le fondant du cheese frais à celui de l'avocat, le Maki Cheese Avocat, ravira vos papilles.", 6, 3.90),
        new product("Maki", "Maki Cheese concombre", "Alliant le fondant du cheese frais au croquant des lamelles de concombre, le Maki Cheese Concombre rafraîchira vos papilles.", 6, 3.90),
        new product("Maki", "Maki thon", "Le Maki Thon est à l'image du concept, simple, frais et de qualité. Succombez à cette bouchée de fraîcheur.", 6, 4.90),
		new product("Temaki", "Temaki saumon avocat", "Le Temaki Saumon Avocat allie la fraîcheur du saumon à l'onctuosité de l'avocat, cette association ravira vos papilles.", 1, 4.50),
        new product("Temaki", "Temaki saumon spicy", "Le Temaki Saumon Spicy allie la fraîcheur du saumon au croquant du concombre et électrisera vos papilles grâce à sa sauce épicée.", 1, 4.90),
        new product("Temaki", "Temaki thon avocat", "Le Temaki Thon Avocat allie la fraîcheur du thon à l'onctuosité de l'avocat, cette association ravira vos papilles.", 1, 4.90),
        new product("Temaki", "Temaki California", "Le Temaki California allie la chair de crabe à l'avocat et fait de ce Temaki le plus onctueux avec sa mayonnaise, pour votre plus grand plaisir.", 1, 4.90),
		new product("Desserts", "Mangue Fraîche", "Terminez votre dégustation de sushi et maki par une note de fraîcheur avec la salade de mangue fraîche. (selon saison)", 1, 4.50),
        new product("Desserts", "Mochi Coco", "Coeur glacé enrobé d'une pâte à base de Lait de Coco", 2, 3.90),
        new product("Desserts", "Cheesecake", "Laissez-vous tenter par un délicieux cheesecake pour terminer votre dégustation de sushi et maki sur une note sucrée.", 1, 3.50),
        new product("Desserts", "Moelleux au chocolat", "Laissez-vous tenter par un délicieux mi-cuit chocolat caramel pour terminer votre dégustation de sushi et maki sur une note sucrée. (à réchauffer avant dégustation)", 1, 3.90),
		new product("Boissons", "Evian 50cl", "", 1, 2.20),
        new product("Boissons", "Badoit 50cl", "", 1, 2.20),
        new product("Boissons", "Coca Cola 33cl", "", 1, 2.20),
        new product("Boissons", "Coca Cola 33cl light", "", 1, 2.20)
    ];
}
store.prototype.getProduct = function (name) {
    for (var i = 0; i < this.products.length; i++) {
        if (this.products[i].name == name)
            return this.products[i];
    }
    return null;
}

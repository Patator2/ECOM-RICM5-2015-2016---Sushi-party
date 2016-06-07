'use strict';

// création d'un module AngularJS (c'est l'application)
// la directive ng-app montre que l'on utilise AngularJS
// on analyse la route et on injecte la page appropriée
var storeApp = angular.module('AngularStore', ['ngResource']);

storeApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/store', {
        templateUrl: 'partials/store.htm',
        controller: storeController 
      }).      
      when('/cart', {
        templateUrl: 'partials/shoppingCart.htm',
        controller: storeController
      }).   
      when('/livraison', {
        templateUrl: 'partials/livraison.htm',
        controller: storeController
      }).
      when('/validation', {
        templateUrl: 'partials/validation.htm'
      }).
      when('/adminPage', {
        templateUrl: 'partials/adminPage.htm',
        controller: storeController
      }).
      when('/modifierArticle', {
        templateUrl: 'partials/modifierArticle.htm'
      }).
      otherwise({
        redirectTo: '/store'
      });
}]);

storeApp.factory('Categorie', function($resource) {
	return $resource('resources/common/categorie/:id'); 
});

storeApp.factory('Produit', function($resource) {
	return $resource('resources/common/produit/:id');
})

storeApp.factory('Magasin', function($resource) {
	return $resource('resources/common/magasin/:id');
});

storeApp.factory('User', function($resource) {
	return $resource('resources/admin/utilisateur/:id');
});

storeApp.factory('Panier', function($resource) {
	return $resource('resources/client/panier/:id');
});


//Contrôleur utilisé par toutes les vues de l'application
function storeController($scope, $routeParams, DataService, Categorie, Magasin, Produit, User, Panier) {

 // récupération du store et du cart avec le service
	$scope.store = DataService.store;	
	$scope.cart = DataService.cart;

	$scope.loading = true;
	$scope.states = {};  
    $scope.panier = new Panier();
    $scope.panier.produits = [];
    $scope.panier.id=420484336;
    $scope.panier.dateLivraison = new Date();
    $scope.panier.dateValidation = new Date();
    $scope.panier.dateCreation = new Date();
    $scope.panier.modeLivraison = true;
    $scope.panier.modeReglement = '';
    $scope.panier.client={};
    $scope.panier.client.adresse2 = '';


      
    $scope.loading = true;
    var listeCategories = Categorie.query(function() {
    	$scope.items = listeCategories;
    	$scope.states.activeItem = $scope.items[0].designation;
    	$scope.loading = false;
	});

    $scope.items=[];

    $scope.panier.client = User.get(2);
    
    $scope.loading = true;
	var listeProduits = Produit.query(function() {	    
		$scope.produits =  listeProduits;
		$scope.loading = false;
	}); 
	

    $scope.loading = true;
    var listeMagasins = Magasin.query(function() {      
        $scope.magasins =  listeMagasins;
        $scope.loading = false;
    });

    var currentuser = User.get({id:1}, function() {
      $scope.panier.client = currentuser;
    });

	
	//var users = User.query(function() {	    
	//	$scope.users =  users;	
	//}); 
	
	
	$scope.ParCategorie = function (item) {
		if($scope.states.activeItem==$scope.items[0].designation) {
			return item;
		} else {
			if(item.categorie.designation==$scope.states.activeItem)
			return item;
		}
    };

    $scope.loading = true;
    var utilisateur = Produit.query(function() {      
        $scope.produits =  utilisateur;
        $scope.loading = false;
    }); 
    
    // cette méthode sera appelée lorsque l'on souhaite enregistrer. Je le placerai dans le bouton submit
    
    $scope.savePanier = function(){
        $scope.panier.$save(function(){
            console.log("Youpi,  Order Saved !!!!")
        })
    };   

    

    // charge les articles du local storage
    $scope.loadItems = function () {
        var tmp = localStorage != null ? localStorage[$scope.panier] : null;
        if (tmp != null && JSON != null) {
            try {
                $scope.panier = JSON.parse(tmp);
            }
            catch (err) {
                // on ignore les erreurs lors du chargement
            }
        }
        else{
        $scope.panier = new Panier();
        $scope.panier.produits = [];
        $scope.panier.id=420484336;
        $scope.panier.dateLivraison = new Date();
        $scope.panier.dateValidation = new Date();
        $scope.panier.dateCreation = new Date();
        $scope.panier.modeLivraison = true;
        $scope.panier.modeReglement = '';
        $scope.panier.client={};
        $scope.panier.client.adresse2 = '';
        $scope.saveItems();
        }
    }

    $scope.loadItems();

    // sauvegarde les articles dans le local storage
    $scope.saveItems = function () {
        if (localStorage != null && JSON != null) {
            localStorage[$scope.panier] = JSON.stringify($scope.panier);
        }
    }

    $scope.toNumber = function (value) {
        value = value * 1;
        return isNaN(value) ? 0 : value;
    }

    $scope.getTotalCount = function (name) {
    var count = 0;
    for (var i = 0; i < $scope.panier.produits.length; i++) {
        var item = $scope.panier.produits[i];
        if (name == null || item.produit.designation == name) {
            count += this.toNumber(item.quantite);
        }
    }
    return count;
    }


    $scope.addItem = function(product,quantity){
            quantity =  $scope.toNumber(quantity);
            var price =  $scope.toNumber(product.prixVente);
        if (quantity != 0) {

            // met à jour la quantité pour l'article existent
            var found = false;
            for (var i = 0; i < $scope.panier.produits.length && !found; i++) {
                var item = $scope.panier.produits[i].produit;
                if (item.designation == product.designation) {
                    found = true;
                    $scope.panier.produits[i].quantite =  $scope.toNumber($scope.panier.produits[i].quantite + quantity);
                    if ($scope.panier.produits[i].quantite <= 0) {
                        $scope.panier.produits.splice(i, 1);
                    }
                }
            }

            // ajout d'un nouvel article
            if (!found) {
                var item = {
                    produit : product,
                    quantite : quantity,
                    prixTotal : quantity * price
                }
                $scope.panier.produits.push(item);
            }
        }
        $scope.panier.montant=$scope.getTotalCount();
        $scope.saveItems();
    }

    $scope.clearItems = function () {
        $scope.panier = new Panier();
        $scope.panier.produits = [];
        $scope.panier.id=420484336;
        $scope.panier.dateLivraison = new Date();
        $scope.panier.dateValidation = new Date();
        $scope.panier.dateCreation = new Date();
        $scope.panier.modeLivraison = true;
        $scope.panier.modeReglement = '';
        $scope.panier.client={};
        $scope.panier.client.adresse2 = '';
        $scope.saveItems();
    }

    $scope.getTotalPrice = function (name) {
        var total = 0;
        for (var i = 0; i < $scope.panier.produits.length; i++) {
            var item = $scope.panier.produits[i].produit;
            if (name == null || item.designation == name) {
                total += $scope.toNumber($scope.panier.produits[i].quantite * item.prixVente);
            }
        }
        return total;
    }

    
  

}

// création d'un data service qui fournie un store et un cart qui sera partagé par toutes les vues (au lieu d'en créer un nouveau pour chaque vue)
storeApp.factory("DataService", function (Produit) {
	
	// création de la boutique
    var myStore = new store();
    
    
    // création du panier d'achat
    var myCart = new shoppingCart("AngularStore");

    // enable PayPal checkout
    // note: the second parameter identifies the merchant; in order to use the 
    // shopping cart with PayPal, you have to create a merchant account with 
    // PayPal. You can do that here:
    // https://www.paypal.com/webapps/mpp/merchant
    myCart.addCheckoutParameters("PayPal", "paypaluser@youremail.com");

    // enable Google Wallet checkout
    // note: the second parameter identifies the merchant; in order to use the 
    // shopping cart with Google Wallet, you have to create a merchant account with 
    // Google. You can do that here:
    // https://developers.google.com/commerce/wallet/digital/training/getting-started/merchant-setup
    myCart.addCheckoutParameters("Google", "xxxxxxx",
    {
            ship_method_name_1: "UPS Next Day Air",
            ship_method_price_1: "20.00",
            ship_method_currency_1: "USD",
            ship_method_name_2: "UPS Ground",
            ship_method_price_2: "15.00",
            ship_method_currency_2: "USD"
    }
    );

    // enable Stripe checkout
    // note: the second parameter identifies your publishable key; in order to use the 
    // shopping cart with Stripe, you have to create a merchant account with 
    // Stripe. You can do that here:
    // https://manage.stripe.com/register
    myCart.addCheckoutParameters("Stripe", "pk_test_xxxx",
    {
            chargeurl: "https://localhost:1234/processStripe.aspx"
    }
    );

    // retourne un objet contenant le store et le cart
    return {
        store: myStore,
        cart: myCart
    };
});
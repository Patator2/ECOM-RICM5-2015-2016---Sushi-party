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
      otherwise({
        redirectTo: '/store'
      });
}]);

storeApp.factory('Categorie', function($resource) {
	return $resource('resources/common/categorie/:id'); 
});

storeApp.factory('Produit', function($resource) {
	return $resource('resources/common/produit/:id');
});

storeApp.factory('User', function($resource) {
	return $resource('resources/admin/utilisateur/:id');
});



//le storeController contient 2 objets :
//- store: contient la liste des produits
//- cart: le panier de shopping avec les objets
//il sera utilisé par toutes les vues de l'application
function storeController($scope, $routeParams, DataService, Categorie, Produit, User) {

 // récupération du store et du cart avec le service
	$scope.store = DataService.store;	
	$scope.cart = DataService.cart;

	$scope.loading = true;
	$scope.states = {};    
    
    var listeCategories = Categorie.query(function() {
    	$scope.items = listeCategories;
    	$scope.states.activeItem = $scope.items[0].designation;
    	$scope.loading = false;
	});
    
    $scope.loading = true;
	var listeProduits = Produit.query(function() {	    
		$scope.produits =  listeProduits;
		$scope.loading = false;
	}); 
	
	
	var users = User.query(function() {	    
		$scope.users =  users;	
	}); 
	
	
	$scope.ParCategorie = function (item) {
		if($scope.states.activeItem==$scope.items[0].designation) {
			return item;
		} else {
			if(item.categorie.designation==$scope.states.activeItem)
			return item;
		}
    };

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
'use strict';

// création d'un module AngularJS (c'est l'application)
// la directive ng-app montre que l'on utilise AngularJS
// on analyse la route et on injecte la page appropriée
var storeApp = angular.module('AngularStore', []);

storeApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
      when('/store', {
        templateUrl: 'partials/store.htm',
        controller: storeController 
      }).
      /*when('/products/:productSku', {
        //templateUrl: 'partials/product.htm',
        //controller: storeController
      }).*/
      when('/cart', {
        templateUrl: 'partials/shoppingCart.htm',
        controller: storeController
      }).
      otherwise({
        redirectTo: '/store'
      });
}]);

storeApp.controller('NavigationController', function ($scope) {
    // Must use a wrapper object, otherwise "activeItem" won't work
    $scope.states = {};
    $scope.states.activeItem = 'Tout afficher';
    $scope.items = [{
        id: 'Tout afficher',
        title: 'Tout afficher'
    }, {
        id: 'Lunch Boxes',
        title: 'Lunch Boxes'
    }, {
        id: 'Signature Rolls',
        title: 'Signature Rolls'
    }, {
        id: 'Spring Rolls',
        title: 'Spring Rolls'
    }, {
        id: 'California Rolls',
        title: 'California Rolls'
    }, {
        id: 'Maki',
        title: 'Maki'
    }, {
        id: 'Temaki',
        title: 'Temaki'
    }, {
        id: 'Desserts',
        title: 'Desserts'
    }, {
        id: 'Boissons',
        title: 'Boissons'
    }, {
    }];
	
	$scope.ParCategorie = function (item) {
		if($scope.states.activeItem=='Tout afficher')
		{
			return item;
		}
		else
		{
			if(item.categorie==$scope.states.activeItem)
			return item;
		}
    };
});

// création d'un data service qui fournie un store et un cart qui sera partagé par toutes les vues (au lieu d'en créer un nouveau pour chaque vue)
storeApp.factory("DataService", function () {

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
'use strict';

// le storeController contient 2 objets :
// - store: contient la liste des produits
// - cart: le panier de shopping avec les objets
// il sera utilisé par toutes les vues de l'application
function storeController($scope, $routeParams, DataService) {

    // récupération du store et du cart avec le service
    $scope.store = DataService.store;
    $scope.cart = DataService.cart;

    // utilisation du routage pour choisir le produit sélectionné
    /*if ($routeParams.productSku != null) {
        $scope.product = $scope.store.getProduct($routeParams.productSku);
    }*/
	
	/*$scope.data = {
		availableOptions: [
			{id:'1',name:'OptionA'},
			{id:'2',name:'OptionB'},
			{id:'3',name:'OptionC'}
		],
		selectedOption: {id:'3',name:'OptionC'}
	};*/
}

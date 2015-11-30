//----------------------------------------------------------------
// classe boutique (contient les produits)
//----------------------------------------------------------------
function store() {
    this.products = [];
}
store.prototype.getProduct = function (name) {
    for (var i = 0; i < this.products.length; i++) {
        if (this.products[i].name == name)
            return this.products[i];
    }
    return null;
}

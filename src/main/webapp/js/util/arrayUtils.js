Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (angular.equals(this[i], obj)) {
            return true;
        }
    }
    return false;
};

Array.prototype.removeElement = function(obj) {
    var i = this.length;
    while (i--) {
        if (angular.equals(this[i], obj)) {
        	this.splice(i,1);
        }
    }
};

Array.prototype.removeElementByProperty = function(chave,obj) {
    var i = this.length;
    while (i--) {
    	if (angular.equals(this[i][chave], obj)) {
        	this.splice(i,1);
        }
    }
};

Array.prototype.containsElementByProperty = function(chave,obj) {
    var i = this.length;
    while (i--) {
    	if (angular.equals(this[i][chave], obj)) {
            return true;
        }
    }
    return false;
};
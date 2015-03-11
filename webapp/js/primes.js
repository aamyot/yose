Primes = function(container, ajax) {
    this.container = container;
    this.ajax = ajax;
};

Primes.prototype = {

    send: function(number) {
        var self = this;
        this.ajax.onload = function() {
            var response = JSON.parse(self.ajax.responseText);
            self.container.querySelector('#result').innerHTML = self.render(response.number, response.decomposition);
        };

        this.ajax.open('POST', '/primeFactors', true);
        this.ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        this.ajax.send('number=' + this.container.querySelector('input#number').value);
    },

    render: function(number, primes) {
        return number + " = " + primes.join(" x ");
    }

};



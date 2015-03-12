
primes = {
    init: function(container, ajax) {
        this.container = container;
        this.ajax = ajax;

        this.container.querySelector('form#primes').addEventListener('submit', function(event) {
            event.preventDefault();
            primes.send();
        });
    },

    send: function() {
        var self = this;
        this.ajax.onload = function() {
            var response = JSON.parse(self.ajax.responseText);
            self.container.querySelector('#result').innerHTML = self.renderResponse(response);
        };

        this.ajax.open('POST', '/primeFactors', true);
        this.ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        this.ajax.send('number=' + this.container.querySelector('input#number').value);
    },

    renderResponse: function(response) {
        if (response.decomposition) {
            return this.renderPrimes(response.number, response.decomposition);
        } else if (response.error === 'not a number') {
            return response.number + ' is not a number';
        } else if (response.error) {
            return response.error;
        }

        return '';
    },

    renderPrimes: function(number, primes) {
        return number + " = " + primes.join(" x ");
    }
};



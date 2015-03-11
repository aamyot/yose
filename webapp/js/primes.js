
primes = {
    init: function(container, ajax) {
        this.container = container;
        this.ajax = ajax;

        container.addEventListener('DOMContentLoaded', function() {
            container.querySelector('form#primes').addEventListener('submit', function(event) {
                event.preventDefault();
                primes.send();
            });
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
        return response.error ? this.errorResponse(response.error) : this.successfulResponse(response.number, response.decomposition);
    },

    errorResponse: function(error) {
        return error;
    },

    successfulResponse: function(number, primes) {
        return number + " = " + primes.join(" x ");
    }
};




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
            if (response instanceof Array) {
                self.container.querySelector('#results').innerHTML = self.renderMultiple(response);
            } else {
                self.container.querySelector('#result').innerHTML = self.renderSingle(response);
            }

        };

        this.ajax.open('POST', '/primeFactors', true);
        this.ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        this.ajax.send(this.data(this.container.querySelector('input#number').value));
    },

    data: function(input) {
        return input.split(',').map(function(number) { return 'number=' + number.trim() }).join('&');
    },

    renderMultiple: function(results) {
        var decompositions = '';
        var self = this;
        results.forEach(function(single) {
            decompositions += '<li>' + self.renderSingle(single) + '</li>';
        });
        return decompositions;
    },

    renderSingle: function(result) {
        if (result.error === 'not a number') {
            return result.number + ' is not a number';
        } else if (result.error) {
            return result.error;
        }

        return this.renderPrimes(result.number, result.decomposition);;
    },

    renderPrimes: function(number, primes) {
        return number + " = " + primes.join(" x ");
    }
};



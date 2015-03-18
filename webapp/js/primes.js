primes = {
    renderMultiple: function (results) {
        var decompositions = [];
        var self = this;
        results.forEach(function (single) {
            decompositions.push(self.renderSingle(single));
        });
        return decompositions;
    },

    renderSingle: function (result) {
        if (result.error === 'not a number') {
            return result.number + ' is not a number';
        } else if (result.error) {
            return result.error;
        }

        return result.number + " = " + result.decomposition.join(" x ");
    }
};

primes.form = {
    init: function (container, ajax) {
        var send = this.send;
        container.querySelector('form#primes').addEventListener('submit', function (event) {
            event.preventDefault();
            send(container, ajax);
        });
    },

    send: function (container, ajax) {
        ajax.onload = function () {
            var results = JSON.parse(ajax.responseText);
            if (results instanceof Array) {
                container.querySelector('#results').innerHTML = primes.form.formatMultiple(primes.renderMultiple(results));
            } else {
                container.querySelector('#result').innerHTML = primes.renderSingle(results);
            }
        };

        ajax.open('POST', '/primeFactors', true);
        ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        ajax.send(primes.form.data(container.querySelector('input#number').value));
    },

    data: function (input) {
        return input.split(',').map(function (number) {
            return 'number=' + number.trim()
        }).join('&');
    },

    formatMultiple: function(results) {
        return results.map(function(result) {return '<li>' + result + '</li>'}).join('');
    }
};

primes.lastDecomposition = {

    load: function(container, ajax) {
        ajax.onload = function () {
            container.querySelector('#last-decomposition').innerHTML = primes.renderSingle(JSON.parse(ajax.responseText))
        };

        ajax.open('GET', '/primeFactors/last', true);
        ajax.send();
    }
};



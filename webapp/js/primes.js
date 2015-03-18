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
        var self = this;
        container.querySelector('form#primes').addEventListener('submit', function (event) {
            event.preventDefault();
            self.send(container, ajax);
        });
    },

    send: function (container, ajax) {
        var self = this;
        ajax.onload = function () {
            self.clear(container)
            self.render(JSON.parse(ajax.responseText), container);
        };

        ajax.open('POST', '/primeFactors', true);
        ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        ajax.send(this.data(container.querySelector('input#number').value));
    },

    clear: function(container) {
        container.querySelector('#results').innerHTML = '';
        container.querySelector('#result').innerHTML = '';
    },

    render: function(results, container) {
        if (results instanceof Array) {
            container.querySelector('#results').innerHTML = primes.form.formatMultiple(primes.renderMultiple(results));
        } else {
            container.querySelector('#result').innerHTML = primes.renderSingle(results);
        }
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
            if (ajax.responseText) {
                container.querySelector('#last-decomposition').innerHTML = primes.renderSingle(JSON.parse(ajax.responseText))
            }
        };

        ajax.open('GET', '/primeFactors/last', true);
        ajax.send();
    }
};



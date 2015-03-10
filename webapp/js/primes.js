Primes = function(container, ajax) {
    this.container = container;
    this.ajax = ajax;
};

Primes.prototype = {

    send: function(number) {
        var self = this;
        this.ajax.onreadystatechange = function() {
            if(self.ajax.readyState === 4) {
                var response = JSON.parse(self.ajax.responseText);
                var result = self.container.createElement("div");
                result.setAttribute("id", "result");
                result.innerHTML = self.render(response.number, response.decomposition);

                self.container.querySelector("form").appendChild(result);
            }
        };

        this.ajax.open('POST', '/primeFactors', true);
        this.ajax.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        this.ajax.send('number=' + this.container.querySelector('input#number').value);
    },

    render: function(number, primes) {
        return number + " = " + primes.join(" x ");
    }

};



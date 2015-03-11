require('../../../webapp/js/primes');

describe("Prime", function () {

    var ajax = {
        send: function () {},
        setRequestHeader: function() {},
        open: function() {}
    };

    var document = require('jsdom').jsdom('' +
        '<form id="primes">' +
        '   <input type="text" id="number" value="24"/>' +
        '   <div id="result"></div>' +
        '</form>'
    );

    beforeEach(function () {
        primes.init(document, ajax);
    });

    it('opens a POST request', function() {
        spyOn(ajax, 'open');

        primes.send();

        expect(ajax.open).toHaveBeenCalledWith('POST', '/primeFactors', true);
    });

    it('sets correct content-type', function () {
        spyOn(ajax, 'setRequestHeader');

        primes.send();

        expect(ajax.setRequestHeader).toHaveBeenCalledWith('Content-type', 'application/x-www-form-urlencoded');
    });

    it('sends the value of the input form', function () {
        spyOn(ajax, 'send');

        primes.send();

        expect(ajax.send).toHaveBeenCalledWith("number=24");
    });

    it('knows how to decompose the response', function() {

        expect(primes.render(20, [2,2,5])).toEqual("20 = 2 x 2 x 5");
    });

    it('displays the decomposed number', function() {
        ajax.send = function() {
            ajax.responseText = JSON.stringify({number:24, decomposition:[2,3,4]});
            ajax.onload();
        };

        primes.send();

        expect(document.getElementById("result").innerHTML).toEqual("24 = 2 x 3 x 4");
    });

});
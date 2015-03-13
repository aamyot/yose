require('../../../webapp/js/primes');

describe("Primes UI", function () {

    var ajax = {
        send: function () {},
        setRequestHeader: function() {},
        open: function() {}
    };

    var document = require('domino').createWindow('' +
        '<form id="primes">' +
        '   <input type="text" id="number" />' +
        '   <div id="result"></div>' +
        '   <ol id="results"></ol>' +
        '</form>'
    ).document;

    beforeEach(function () {
        document.querySelector('#number').value = 24;
        primes.init(document, ajax);
    });

    describe('Submitting the form', function() {

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

        it('sends a single value', function () {
            spyOn(ajax, 'send');

            primes.send();

            expect(ajax.send).toHaveBeenCalledWith("number=24");
        });

        it('parses multiple entries', function() {
            expect(primes.data('1, 2, 3')).toEqual('number=1&number=2&number=3');
        }),

        xit('sends multiple values', function () {
            spyOn(ajax, 'send');

            document.querySelector('#number').value = "1, hello, 3";
            primes.send();

            expect(ajax.send).toHaveBeenCalledWith("number=1&number=hello&number=3");
        });
    });

    describe('Rendering', function() {

        it('knows how to decompose the response', function() {
            expect(primes.renderPrimes(20, [2,2,5])).toEqual("20 = 2 x 2 x 5");
        });

        it('displays the decomposed number', function() {
            ajax.send = function() {
                ajax.responseText = JSON.stringify({number:24, decomposition:[2,3,4]});
                ajax.onload();
            };

            primes.send();

            expect(document.querySelector("#result").innerHTML).toEqual("24 = 2 x 3 x 4");
        });

        it('displays error message for NumberIsTooBig input', function(){
            ajax.send = function() {
                ajax.responseText = JSON.stringify({number:1234567, error:'too big number (>1e6)'});
                ajax.onload();
            };

            primes.send();

            expect(document.querySelector("#result").innerHTML).toEqual("too big number (&gt;1e6)");
        });

        it('displays error message for NotANumber input', function(){
            ajax.send = function() {
                ajax.responseText = JSON.stringify({number:'NaN', error:'not a number'});
                ajax.onload();
            };

            primes.send();

            expect(document.querySelector("#result").innerHTML).toEqual("NaN is not a number");
        });

        it('displays error message for NegativeNumber input', function(){
            ajax.send = function() {
                ajax.responseText = JSON.stringify({number:-24, error:'-24 is not an integer > 1'});
                ajax.onload();
            };

            primes.send();

            expect(document.querySelector("#result").innerHTML).toEqual("-24 is not an integer &gt; 1");
        });

        it('displays result for multiple numbers', function(){
            ajax.send = function() {
                ajax.responseText = JSON.stringify([
                    {number:24, decomposition:[2,3,4]},
                    {number: -24, error: '-24 is not an integer > 1'},
                    {number:'NaN', error:'not a number'},
                    {number:-24, error:'-24 is not an integer > 1'}
                ]);
                ajax.onload();
            };

            primes.send();

            expect(document.querySelector("#results li:nth-child(1)").innerHTML).toEqual("24 = 2 x 3 x 4");
            expect(document.querySelector("#results li:nth-child(2)").innerHTML).toEqual("-24 is not an integer &gt; 1");
            expect(document.querySelector("#results li:nth-child(3)").innerHTML).toEqual("NaN is not a number");
            expect(document.querySelector("#results li:nth-child(4)").innerHTML).toEqual("-24 is not an integer &gt; 1");
        });
    });

});
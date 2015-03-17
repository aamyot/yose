require('../../../webapp/js/primes');

describe("Primes Last Decomposition", function () {

    var last = primes.lastDecomposition;

    var ajax = {
        send: function () {},
        open: function() {}
    };

    var container = require('domino').createWindow('<div id="last-decomposition"></div>').document;

    describe('Loading', function() {

        it('opens a GET request', function() {
            spyOn(ajax, 'open');

            last.load(container, ajax);

            expect(ajax.open).toHaveBeenCalledWith('GET', '/primeFactors/last', true);
        });

        it('sends the request', function() {
            spyOn(ajax, 'send');

            last.load(container, ajax);

            expect(ajax.send).toHaveBeenCalled();
        });
    });

    describe('Rendering', function() {

        it('displays the last decomposition', function () {
            ajax.send = function () {
                ajax.responseText = JSON.stringify({number: 24, decomposition: [2, 3, 4]});
                ajax.onload();
            };

            last.load(container, ajax);

            expect(container.querySelector("#last-decomposition").innerHTML).toEqual("24 = 2 x 3 x 4");
        });

        it('handles errors', function () {
            ajax.send = function () {
                ajax.responseText = JSON.stringify({number:'error', error:'the error message'});
                ajax.onload();
            };

            last.load(container, ajax);

            expect(container.querySelector("#last-decomposition").innerHTML).toEqual("the error message");
        });
    });

});
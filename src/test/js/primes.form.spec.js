require('../../../webapp/js/primes');

describe("Primes Form", function () {

    var form = primes.form;

    var ajax = {
        send: function () {},
        setRequestHeader: function() {},
        open: function() {}
    };

    var container = require('domino').createWindow('' +
        '<form id="primes">' +
        '   <input type="text" id="number" />' +
        '   <div id="result"></div>' +
        '   <ol id="results"></ol>' +
        '</form>'
    ).document;

    beforeEach(function () {
        container.querySelector('#number').value = 24;
    });

    describe('Submitting the form', function() {

        it('opens a POST request', function() {
            spyOn(ajax, 'open');

            form.send(container, ajax);

            expect(ajax.open).toHaveBeenCalledWith('POST', '/primeFactors', true);
        });

        it('sets correct content-type', function () {
            spyOn(ajax, 'setRequestHeader');

            form.send(container, ajax);

            expect(ajax.setRequestHeader).toHaveBeenCalledWith('Content-type', 'application/x-www-form-urlencoded');
        });

        it('sends a single value', function () {
            spyOn(ajax, 'send');

            form.send(container, ajax);

            expect(ajax.send).toHaveBeenCalledWith("number=24");
        });

        it('parses multiple entries', function() {
            expect(form.data('1, 2, 3')).toEqual('number=1&number=2&number=3');
        });

        it('sends multiple values', function () {
            spyOn(ajax, 'send');
            container.querySelector('#number').value = "1, hello, 3";

            form.send(container, ajax);

            expect(ajax.send).toHaveBeenCalledWith("number=1&number=hello&number=3");
        });

        it('displays the result of a single decomposition', function() {
            ajax.send = function() {
                ajax.responseText = JSON.stringify({number:8, decomposition:[2,2,2]});
                ajax.onload();
            };

            form.send(container, ajax);

            expect(container.querySelector("#result").innerHTML).toEqual("8 = 2 x 2 x 2");
        });

        it('displays result for multiple decomposition', function(){
            ajax.send = function() {
                ajax.responseText = JSON.stringify([
                    {number:24, decomposition:[2,3,4]},
                    {number: -24, error: '-24 is not an integer > 1'},
                    {number:'NaN', error:'not a number'},
                    {number:-24, error:'-24 is not an integer > 1'}
                ]);
                ajax.onload();
            };

            form.send(container, ajax);

            expect(container.querySelector("ol#results li:nth-child(1)").innerHTML).toEqual("24 = 2 x 3 x 4");
            expect(container.querySelector("ol#results li:nth-child(2)").innerHTML).toEqual("-24 is not an integer &gt; 1");
            expect(container.querySelector("ol#results li:nth-child(3)").innerHTML).toEqual("NaN is not a number");
            expect(container.querySelector("ol#results li:nth-child(4)").innerHTML).toEqual("-24 is not an integer &gt; 1");
        });

    });

    it('clears the results of previous decomposition', function(){
        spyOn(form, 'clear');

        ajax.send = function() {
            ajax.responseText = JSON.stringify({number:8, decomposition:[2,2,2]});
            ajax.onload();
        };

        form.send(container, ajax);

        expect(form.clear).toHaveBeenCalledWith(container);
    });

    it('clears both single and multiple decompositions', function(){
        container.querySelector("#result").innerHTML = "single decomposition";
        container.querySelector("#results").innerHTML = "multiple decomposition";

        form.clear(container);

        expect(container.querySelector("#result").innerHTML).toEqual("");
        expect(container.querySelector("#results").innerHTML).toEqual("");
    });

});
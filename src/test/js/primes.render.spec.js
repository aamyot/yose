require('../../../webapp/js/primes');

describe("Primes rendering", function () {

    it('renders the decomposition of a number', function () {
        expect(primes.renderSingle({number: 24, decomposition: [2, 3, 4]})).toEqual("24 = 2 x 3 x 4");
    });

    it('renders the error message for NumberIsTooBig input', function () {
        expect(primes.renderSingle({number: 1234567, error: 'too big number (>1e6)'})).toEqual("too big number (>1e6)");
    });

    it('renders the error message for NotANumber input', function () {
        expect(primes.renderSingle({number: 'NaN', error: 'not a number'})).toEqual("NaN is not a number");
    });

    it('renders the error message for NegativeNumber input', function () {
        expect(primes.renderSingle({number: -24, error: '-24 is not an integer > 1'})).toEqual("-24 is not an integer > 1");
    });

    it('renders the result for multiple numbers', function () {
        var results = primes.renderMultiple([
            {number: 24, decomposition: [2, 3, 4]},
            {number: -24, error: '-24 is not an integer > 1'},
            {number: 'NaN', error: 'not a number'},
            {number: -24, error: '-24 is not an integer > 1'}
        ]);

        expect(results[0]).toEqual("24 = 2 x 3 x 4");
        expect(results[1]).toEqual("-24 is not an integer > 1");
        expect(results[2]).toEqual("NaN is not a number");
        expect(results[3]).toEqual("-24 is not an integer > 1");
    });

});
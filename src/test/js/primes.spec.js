require('../../../webapp/js/primes');

describe("Prime Factors", function() {

    it('posts the number', function() {
        var primes = new Primes(24);

        expect(primes.number).toEqual(24);
    });

});
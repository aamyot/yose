require('../../../webapp/js/minesweeper');

describe("Minesweeper", function () {

    var document;

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;
    });

    it('loads the grid', function() {
        minesweeper.grid = [
            ['empty', 'empty'],
            ['empty', 'bomb']
        ];

        minesweeper.load(document);

        expect(document.querySelector("#cell-2x2").className).toEqual("lost");
    });
});
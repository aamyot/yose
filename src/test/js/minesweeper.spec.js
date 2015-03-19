require('../../../webapp/js/minesweeper');

describe("Minesweeper", function () {

    var document;

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;
    });

    it('loads the grid and handles the click', function() {
        var grid = [
            ['empty', 'empty'],
            ['empty', 'bomb']
        ];

        minesweeper.load(grid, document);
        clickOnCell(document.querySelector("#cell-2x2"));

        expect(document.querySelector("#cell-1x1").className).toEqual("");
        expect(document.querySelector("#cell-2x2").className).toEqual("lost");
    });

    var clickOnCell = function(cell) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cell.dispatchEvent(click);
    };
});

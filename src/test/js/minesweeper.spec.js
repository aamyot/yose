require('../../../webapp/js/minesweeper');

describe("Minesweeper", function () {

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;
    });

    it('handles clicking on a bomb', function() {
        var grid = [
            ['empty', 'empty'],
            ['empty', 'bomb']
        ];

        minesweeper.load(grid);
        clickOnCell(document.querySelector("#cell-2x2"));

        expect(document.querySelector("#cell-2x2").className).toEqual("lost");
    });

    it('handles clicking on a safe cell', function() {
        var grid = [
            ['empty', 'empty', 'empty'],
            ['empty', 'bomb', 'empty'],
            ['empty', 'bomb', 'empty']
        ];

        minesweeper.load(grid);
        clickOnCell(document.querySelector("#cell-2x1"));

        expect(document.querySelector("#cell-2x1").className).toEqual("safe");
        expect(document.querySelector("#cell-2x1").innerHTML).toEqual("2");
    });

    var clickOnCell = function(cell) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cell.dispatchEvent(click);
    };
});

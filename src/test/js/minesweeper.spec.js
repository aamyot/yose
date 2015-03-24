require('../../../webapp/js/minesweeper');

describe("Minesweeper", function () {

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;

        var grid =  [
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'bomb' , 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'bomb',  'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty']
        ];
        board = new minesweeper.Board(grid);
        board.render();
    });

    describe("Rendering", function() {

        it('renders a 8x8 grid', function() {
            for (var row = 1; row <= 8; row++) {
                for (var col = 1; col <= 8; col++) {
                    expect(utils.cell(row, col)).toBeDefined();
                }
            }
        });

        it('calls the reset function', function() {
            spyOn(board, 'reset');

            board.render();

            expect(board.reset).toHaveBeenCalled();
        });

        it('resetting the board clears the HTML', function() {
            board.reset();

            expect(document.querySelector("#minesweeper-grid").innerHTML).toEqual("");
        });

    });

    describe("Actions", function() {

        it('handles trapped cell', function () {
            var cell = utils.cell(4, 4);

            utils.clickOnCell(cell);

            expect(cell.className).toEqual("lost");
        });

        it('handles safe cell with bombs around', function () {
            var cell = utils.cell(4, 5);

            utils.clickOnCell(cell);

            expect(cell.className).toEqual("safe");
            expect(cell.innerHTML).toEqual("2");
        });

        it('handles safe cell with no bomb around', function () {
            var cell = utils.cell(5, 2);

            utils.clickOnCell(cell);

            expect(cell.className).toEqual("safe");
            expect(cell.innerHTML).toEqual("");
        });
    });

});

utils = {
    clickOnCell: function(cell) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cell.dispatchEvent(click);
    },

    cell: function cell(row, col) {
        return document.querySelector("#cell-"+ row + "x" + col);
    }
};


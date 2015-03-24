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
                    expect(cell(row, col)).toBeDefined();
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
            clickOnCell(4, 4);

            expect(cell(4, 4).className).toEqual("lost");
        });

        it('handles safe cell', function () {
            clickOnCell(5, 4);

            expect(cell(5, 4).className).toEqual("safe");
            expect(cell(5, 4).innerHTML).toEqual("2");
        });
    });

});


clickOnCell = function(row, col) {
    var click = document.createEvent('Event');
    click.initEvent('click', true, true);
    cell(row, col).dispatchEvent(click);
};

cell = function(row, col) {
    return document.querySelector("#cell-"+ row + "x" + col);
};

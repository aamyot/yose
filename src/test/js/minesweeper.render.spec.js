require('../../../webapp/js/minesweeper');
require('./minesweeper.utils');

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

    describe("Rendering the grid", function() {

        it('displays a 8x8 grid', function() {
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

        describe("Resetting the board", function() {

            it(' clears the HTML', function() {
                board.reset();

                expect(document.querySelector("#minesweeper-grid").innerHTML).toEqual("");
            });
        });

    });


});


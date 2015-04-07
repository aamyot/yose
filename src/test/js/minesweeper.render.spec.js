require('../../../webapp/js/minesweeper');

describe('Minesweeper', function () {

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;
    });

    describe('empty grid', function() {

        var board;

        beforeEach(function() {
            board = new minesweeper.Board();
        });

        it('creates an 8x8 grid if no grid is provided', function () {
            board.render(document.querySelector('#minesweeper-grid'));

            for (var row = 1; row <= 8; row++) {
                for (var col = 1; col <= 8; col++) {
                    expect(cellAt(row, col)).toBeDefined();
                }
            }
        });

        it('resets the container', function () {
            spyOn(board, 'reset');

            board.render(document.querySelector('#minesweeper-grid'));

            expect(board.reset).toHaveBeenCalledWith(document.querySelector('#minesweeper-grid'))
        });
    });

    it('loads a injected grid', function() {
        var grid = [
            ['empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty'],
            ['empty', 'bomb' , 'empty']
        ];

        var board = new minesweeper.Board(grid);
        board.render(document.querySelector('#minesweeper-grid'));

        grid.forEach(function(line, row) {
            line.forEach(function(cell, col) {
                expect(board.cellAt(row, col).state).toEqual(grid[row][col]);
            })
        });
    });

    var cellAt = function(row, col) {
        return document.querySelector('#cell-' + row + 'x' + col);
    };

});


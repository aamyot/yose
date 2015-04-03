require('../../../webapp/js/minesweeper');

describe('Minesweeper', function () {

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;
    });

    describe('Rendering', function() {

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

    describe('Rendering an injected grid', function() {

        beforeEach(function() {
            grid = [
                ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
                ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty']
            ];

            board = new minesweeper.Board(grid);
            board.render(document.querySelector('#minesweeper-grid'));
        });

        it('loads a injected grid', function() {

            grid.forEach(function(line, row) {
                line.forEach(function(cell, col) {
                    expect(board.cellAt(row, col)).toEqual(grid[row][col]);
                })
            });
        });

        describe('Actions', function() {

            it('fails when a cell is trapped', function() {

                clickOnCell(2, 2);

                expect(document.querySelector('#cell-2x2').className).toEqual('lost');
            });

            it('shows bombs around when a cell is safe', function() {

                clickOnCell(1, 1);

                expect(document.querySelector('#cell-1x1').className).toEqual('safe');
                expect(document.querySelector('#cell-1x1').innerHTML).toEqual('1');
            });

        });

        describe('Open fields', function() {

            it('does not display 0 when there is no bomb around', function() {
                clickOnCell(4, 4);

                expect(document.querySelector('#cell-4x4').innerHTML).toEqual('');
            });

            it('reveals open field when a cell has no bomb', function() {
                var grid =  [
                    ['bomb' , 'empty', 'empty'],
                    ['empty', 'empty', 'empty'],
                    ['empty', 'empty', 'bomb' ]
                ];

                var board = new minesweeper.Board(grid);
                board.render(document.querySelector('#minesweeper-grid'));

                clickOnCell(3, 1);

                expect(document.querySelector('#cell-2x1').className).toEqual('safe');
                expect(document.querySelector('#cell-2x1').innerHTML).toEqual('1');

                expect(document.querySelector('#cell-2x2').className).toEqual('safe');
                expect(document.querySelector('#cell-2x2').innerHTML).toEqual('2');

                expect(document.querySelector('#cell-3x2').className).toEqual('safe');
                expect(document.querySelector('#cell-3x2').innerHTML).toEqual('1');
            });
        });
    });

    var cellAt = function(row, col) {
        return document.querySelector('#cell-' + row + 'x' + col);
    };

    var clickOnCell = function(row, col) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cellAt(row, col).dispatchEvent(click);
    };

});


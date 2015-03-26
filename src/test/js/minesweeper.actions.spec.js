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

    describe("Actions", function() {

        it('handles trapped cell', function () {
            var cell = utils.cell(4, 4);

            utils.clickOnCell(cell);

            expect(cell.className).toEqual("lost");
        });

        it('handles safe cell with bombs around', function () {
            var cell = utils.cell(4, 5);

            utils.clickOnCell(cell);

            utils.assertCellIsSafe(4, 5, "2");
        });

        it('handles safe cell with no bomb around', function () {
            var cell = utils.cell(5, 2);

            utils.clickOnCell(cell);

            utils.assertCellIsSafe(5, 2, "");
        });

        it('reveals open field when a cell has no bomb', function() {
            var grid =  [
                ['bomb' , 'empty', 'empty'],
                ['empty', 'empty', 'empty'],
                ['empty', 'empty', 'bomb' ]
            ];
            board = new minesweeper.Board(grid);
            board.render();

            utils.clickOnCell(utils.cell(3, 1));

            utils.assertCellIsSafe(2, 1, "1");
            utils.assertCellIsSafe(2, 2, "2");
            utils.assertCellIsSafe(3, 2, "1");
        });
    });

});


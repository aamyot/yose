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


require('../../../webapp/js/minesweeper');

describe('Actions:', function() {

    beforeEach(function() {
        var html = require('fs').readFileSync('./webapp/views/minesweeper.html').toString();
        document = require('domino').createWindow(html).document;

        var grid = [
            ['empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty'],
            ['empty', 'bomb' , 'empty', 'empty', 'empty', 'empty', 'empty', 'empty']
        ];

        var board = new minesweeper.Board(grid);
        board.render(document.querySelector('#minesweeper-grid'));

    });

    it('fails when a cell is trapped', function() {

        clickOnCell(2, 2);

        expect(document.querySelector('#cell-2x2').className).toEqual('lost');
    });

    it('shows bombs around when a cell is safe', function() {

        clickOnCell(1, 1);

        expect(document.querySelector('#cell-1x1').className).toEqual('safe');
        expect(document.querySelector('#cell-1x1').innerHTML).toEqual('1');
    });

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

    describe('suspect mode', function() {
        it('suspects a cell to be trapped', function() {
            activateSuspectMode();

            clickOnCell(1, 1);

            expect(document.querySelector('#cell-1x1').className).toEqual('suspect');
            expect(document.querySelector('#cell-1x1').innerHTML).toEqual('');
        });

        it('resets a suspected cell', function() {
            activateSuspectMode();

            clickOnCell(1, 1);

            expect(document.querySelector('#cell-1x1').className).toEqual('suspect');

            clickOnCell(1, 1);

            expect(document.querySelector('#cell-1x1').className).toEqual('');
        });
    });

    var clickOnCell = function(row, col) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        document.querySelector('#cell-' + row + 'x' + col).dispatchEvent(click);
    };

    var activateSuspectMode = function() {
        document.querySelector('#suspect-mode').checked = true;
    }

});
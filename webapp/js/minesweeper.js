minesweeper = {};

minesweeper.Board = function (grid) {
    document.grid = grid || this.generateGrid();
    this.cells = this.generate(document.grid);
};

minesweeper.Board.prototype = {
    generate: function(grid) {
        var cells = [];
        for (var row = 0; row < grid.length; row++) {
            cells.push([]);
            for (var col = 0; col < grid[row].length; col++) {
                var cell = new minesweeper.Cell(row, col, grid[row][col], this);
                cells[row].push(cell);
            }
        }
        return cells;
    },

    render: function (container) {
        this.reset(container);

        for (var row = 0; row < this.cells.length; row++) {
            var tr = document.createElement('tr');
            container.appendChild(tr);
            for (var col = 0; col < this.cells[row].length; col++) {
                tr.appendChild(this.cells[row][col].render());
            }
        }
    },

    cellAt: function (row, col) {
        return this.cells[row][col];
    },

    generateGrid: function () {
        var grid = [];

        for (var row = 0; row < 8; row++) {
            grid.push([]);
            for (var col = 0; col < 8; col++) {
                grid[row].push(Math.random() > 0.75 ? 'bomb' : 'empty');
            }
        }

        return grid;
    },

    cellIsValid: function(row, col) {
        return (row >=0 && row < this.cells.length) &&
               (col >= 0 && col < this.cells[row].length);
    },

    reset: function (container) {
        container.innerHTML = '';
    }

};

minesweeper.Cell = function (row, col, state, board) {
    this.row = row;
    this.col = col;
    this.state = state;
    this.board = board;
};

minesweeper.Cell.prototype = {

    render: function() {
        this.elem = document.createElement('td');
        this.elem.setAttribute('id', this.cellId());
        this.elem.addEventListener('click', function () {
            this.reveal();
        }.bind(this));
        return this.elem;
    },

    cellId: function() {
        return 'cell-' + (this.row + 1) + 'x' + (this.col + 1);
    },

    reveal: function() {
        if (this.isSuspectModeActivated()) {
            this.suspect();
            return;
        }

        if (this.isTrapped()) {
            this.lost();
            return;
        }

        this.safe();

        if (this.bombsAround() == 0) {
            this.neighbours()
                .filter(function(cell) { return !cell.isRevealed(); })
                .forEach(function(cell) { cell.reveal(); });
        }
    },

    isTrapped: function () {
        return this.state == 'bomb';
    },

    lost: function() {
        this.elem.className = 'lost';
    },

    safe: function() {
        this.elem.className = 'safe';

        var bombs = this.bombsAround();
        if (bombs > 0) {
            this.elem.innerHTML = bombs;
        }
    },

    suspect: function() {
        this.elem.className = 'suspect';
    },

    isRevealed: function () {
        return this.elem.className != '';
    },

    neighbours: function () {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];

        return around.map(function(offset) { return [this.row+offset[0], this.col+offset[1]]; }.bind(this))
            .filter(function(coord) { return this.board.cellIsValid(coord[0], coord[1]); }.bind(this))
            .map(function(coord) { return this.board.cellAt(coord[0], coord[1]) }.bind(this));

    },

    bombsAround: function () {
        return this.neighbours()
                   .filter(function(cell) { return cell.isTrapped(); }).length;
    },

    isSuspectModeActivated: function() {
        return document.querySelector('#suspect-mode').checked;
    }

};


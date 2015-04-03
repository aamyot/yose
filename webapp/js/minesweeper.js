minesweeper = {};

minesweeper.Board = function (grid) {
    this.grid = grid || this.generateGrid();
};

minesweeper.Board.prototype = {
    render: function (container) {
        this.reset(container);

        this.grid.forEach(function (line, row) {
            var tr = document.createElement('tr');
            container.appendChild(tr);

            line.forEach(function (cell, col) {
                tr.appendChild(this.createCell(row, col));
            }.bind(this));
        }.bind(this));

    },

    cellAt: function (row, col) {
        return this.grid[row][col];
    },

    generateGrid: function () {
        var grid = [];

        for (var row = 0; row < 8; row++) {
            grid.push([]);
            for (var col = 0; col < 8; col++) {
                grid[row].push('empty');
            }
        }

        return grid;
    },

    createCell: function (row, col) {
        var cell = document.createElement('td');
        cell.setAttribute('id', this.cellId(row, col));
        cell.addEventListener('click', function () {
            this.reveal(row, col);
        }.bind(this));
        return cell;
    },

    cellId: function(row, col) {
        return 'cell-' + (row + 1) + 'x' + (col + 1);
    },

    cellIsValid: function(row, col) {
        return (row >=0 && row < this.grid.length) &&
            (col >= 0 && col < this.grid[row].length);
    },

    cellElementAt: function(row, col) {
        return document.querySelector('#cell-' + (row + 1) + 'x' + (col + 1));
    },

    reset: function (container) {
        container.innerHTML = '';
    },

    reveal: function (row, col) {
        var cell = this.cellElementAt(row, col);

        if (this.isTrapped(row, col)) {
            cell.className = 'lost';
            return;
        }

        cell.className = 'safe';

        var bombs = this.bombsAround(row, col);
        if (bombs > 0) {
            cell.innerHTML = bombs;
        } else {
            this.neighbours(row, col)
                .filter(function(point) { return !this.isRevealed(point[0], point[1]); }.bind(this))
                .forEach(function(point) { this.reveal(point[0], point[1]); }.bind(this));
        }
    },

    bombsAround: function (row, col) {
        return this.neighbours(row, col)
            .filter(function(point) { return this.isTrapped(point[0], point[1]); }.bind(this)).length;
    },

    neighbours: function (row, col) {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];

        return around.map(function(offset) { return [row+offset[0], col+offset[1]]; }.bind(this))
            .filter(function(point) { return this.cellIsValid(point[0], point[1]); }.bind(this));
    },

    isTrapped: function (row, col) {
        return this.cellAt(row, col) == 'bomb';
    },

    isRevealed: function (row, col) {
        return this.cellElementAt(row, col).className != '';
    }
};



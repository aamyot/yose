minesweeper = {

    load: function(injectedGrid) {
        this.reset();

        var grid = injectedGrid || this.grid();

        var board = document.querySelector("#minesweeper-grid");
        for (var row = 0; row < grid.length; row++) {
            var line = board.appendChild(document.createElement("tr"));
            for (var col = 0; col < grid[row].length; col++) {
                line.appendChild(new minesweeper.Cell(row, col, grid[row][col]).render());
            }
        }
    },

    bombsAround: function(row, col) {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];

        var bombsCount = 0;
        around.forEach(function(offset) {
            if (minesweeper.cell(row+offset[0], col+offset[1]).className == "lost")
                bombsCount++;
        });

        return bombsCount;
    },

    grid: function() {

        var grid = [[]];
        for (var row = 0; row < 8; row++) {
            grid.push([]);
            for (var col = 0; col < 8; col++) {
                grid[row].push("empty");
            }
        }

        return grid;
    },

    reset: function() {
        document.querySelector("#minesweeper-grid").innerHTML = "";
    }
};

minesweeper.Board = function(grid) {
    this.board = this.load(grid || this.generateGrid());
};

minesweeper.Board.prototype = {

    load: function(grid) {
        var board = [[]];
        for (var row = 0; row < grid.length; row++) {
            board.push([]);
            for (var col = 0; col < grid[row].length; col++) {
                board[row].push(new minesweeper.Cell(row, col, grid[row][col]));
            }
        }
        return board;
    },

    render: function() {
        this.reset();

        var container = document.querySelector("#minesweeper-grid")
        for (var row = 0; row < this.board.length; row++) {
            var line = container.appendChild(document.createElement("tr"));
            for (var col = 0; col < this.board[row].length; col++) {
                line.appendChild(this.board[row][col].render());
            }
        }
    },

    generateGrid: function() {
        var grid = [[]];
        for (var row = 0; row < 8; row++) {
            grid.push([]);
            for (var col = 0; col < 8; col++) {
                grid[row].push("empty");
            }
        }

        return grid;
    },

    reset: function() {
        document.querySelector("#minesweeper-grid").innerHTML = "";
    }
};

minesweeper.Cell = function(row, col, state) {
    this.row = row;
    this.col = col;
    this.state = state;
};

minesweeper.Cell.prototype = {
    render: function() {
        var td = document.createElement("td");
        td.setAttribute("id", "cell-" + (this.row + 1) + "x" + (this.col + 1));

        var self = this;
        td.addEventListener('click', function(event) {
            if (self.state == "bomb") self.markAsFail(td);
            else self.markAsSafe(td);
        });

        return td;
    },

    markAsFail: function(td) {
        td.setAttribute("class", "lost")
    },

    markAsSafe: function(td) {
        td.setAttribute("class", "safe");
        td.innerHTML = 2;
    }


};



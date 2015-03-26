minesweeper = {};

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

    render: function(container) {
        this.reset();

        var container = document.querySelector("#minesweeper-grid")
        for (var row = 0; row < this.board.length; row++) {
            var line = container.appendChild(document.createElement("tr"));
            for (var col = 0; col < this.board[row].length; col++) {
                line.appendChild(this.board[row][col].elem);
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
    },

    cell: function(row, col) {
        if (row >= 0 && row < 8 &&
            col >= 0 && col < 8) {
            return this.board[row][col];
        }
    }
};

minesweeper.Cell = function(row, col, state) {
    this.row = row;
    this.col = col;
    this.elem = this.createElement();
    this.state = state;
};

minesweeper.Cell.prototype = {
    createElement: function() {
        var td = document.createElement("td");
        td.setAttribute("id", this.id());

        var self = this;
        td.addEventListener('click', function(event) {
            if (self.state == "bomb") self.markAsFail();
            else self.markAsSafe();
        });

        return td;
    },

    markAsFail: function() {
        this.elem.setAttribute("class", "lost")
    },

    markAsSafe: function() {
        this.elem.setAttribute("class", "safe");

        var bombsAround = this.bombsAround();

        if (bombsAround == 0) {
            this.revealOpenField();
        } else {
            this.elem.innerHTML = bombsAround;
        }
    },

    id: function() {
        return "cell-" + (this.row + 1) + "x" + (this.col + 1);
    },

    bombsAround: function() {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];

        var bombsCount = 0;
        var self = this;
        around.forEach(function(offset) {
            var cell = board.cell(self.row + offset[0], self.col + offset[1]);
            if (cell && cell.state == "bomb")
                bombsCount++;
        });

        return bombsCount;
    },

    revealOpenField: function() {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];
        var self = this;
        around.forEach(function(offset) {
            var cell = board.cell(self.row + offset[0], self.col + offset[1]);
            if (cell) {
                if (cell.elem.className == "") {
                    cell.markAsSafe()
                }
            }
        });
    }
};



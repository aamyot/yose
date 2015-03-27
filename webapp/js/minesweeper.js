minesweeper = {};

minesweeper.Board = function (grid) {
    this.matrix = this.load(grid || this.generateGrid());
};

minesweeper.Board.prototype = {

    load: function (grid) {
        console.log(grid);
        var matrix = [[]];
        for (var row = 0; row < grid.length; row++) {
            matrix.push([]);
            for (var col = 0; col < grid[row].length; col++) {
                matrix[row].push(this.createCell(row, col , grid[row][col]));
            }
        }
        console.log(matrix);
        return matrix;
    },

    createCell: function(row, col, state) {
        var cell = new minesweeper.Cell(row, col, state);
        cell.addOnClick(this);
        return cell;
    },

    render: function () {
        this.reset();

        var container = document.querySelector("#minesweeper-grid");
        for (var row = 0; row < this.matrix.length; row++) {
            var line = container.appendChild(document.createElement("tr"));
            for (var col = 0; col < this.matrix[row].length; col++) {
                line.appendChild(this.matrix[row][col].elem);
            }
        }
    },

    generateGrid: function () {
        var grid = [[]];
        for (var row = 0; row < 8; row++) {
            grid.push([]);
            for (var col = 0; col < 8; col++) {
                grid[row].push("empty");
            }
        }

        return grid;
    },

    reset: function () {
        document.querySelector("#minesweeper-grid").innerHTML = "";
    },

    cellAt: function (row, col) {
        return this.matrix[row][col];
    },

    cellExists: function(row, col) {
        return row >= 0 && row < this.matrix.length &&
            col >= 0 && col < this.matrix[row].length;
    }


};

minesweeper.Cell = function (row, col, state) {
    this.row = row;
    this.col = col;
    this.elem = this.createElement();
    this.state = state;
};

minesweeper.Cell.prototype = {

    createElement: function () {
        var td = document.createElement("td");
        td.setAttribute("id", this.id());
        return td;
    },

    addOnClick: function(board) {
        this.elem.addEventListener('click', function () {
            this.show(board);
        }.bind(this));
    },

    show: function(board) {
        if (this.state == "bomb") {
            this.markAsFail();
            return;
        }

        this.markAsSafe(board);

        if (this.bombsAround(board) == 0) {
            this.neighbor(board)
                .filter(function(cell) { return cell.elem.className == "" })
                .forEach(function(cell) { cell.show(board) });
        }

    },

    markAsFail: function () {
        this.elem.setAttribute("class", "lost")
    },

    markAsSafe: function (board) {
        this.elem.setAttribute("class", "safe");

        var bombs =  this.bombsAround(board);
        if (bombs > 0) {
            this.elem.innerHTML = bombs;
        }
    },

    id: function () {
        return "cell-" + (this.row + 1) + "x" + (this.col + 1);
    },

    bombsAround: function (board) {
        var bombsCount = 0;
        this.neighbor(board)
            .filter(function(cell) { return cell.isTrapped() })
            .forEach(function() { bombsCount++; });

        return bombsCount;
    },

    neighbor: function (board) {
        var around = [
            [-1, -1], [-1, 0], [-1, +1],
            [ 0, -1],          [ 0, +1],
            [+1, -1], [+1, 0], [+1, +1]
        ];

        return around.map(function (offset) { return {x: this.row + offset[0], y: this.col + offset[1]} }.bind(this))
                     .filter(function (point) { return board.cellExists(point.x, point.y); })
                     .map(function (point) { return board.cellAt(point.x, point.y); });
    },

    isTrapped: function () {
        return this.state == "bomb";
    }
};



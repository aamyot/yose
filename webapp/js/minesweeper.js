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

minesweeper.Cell = function(row, col, state) {
    this.row = row;
    this.col = col;
    this.state = state;
};

minesweeper.Cell.prototype = {
    render: function() {
        var td = document.createElement("td");
        td.setAttribute("id", "#cell-" + (this.row + 1) + "x" + (this.col + 1));

        var state = this.state;
        td.addEventListener('click', function(event) {
            if (state == "bomb") td.setAttribute("class", "lost");
            else td.setAttribute("class", "safe");
        });



        return td;
    }
};


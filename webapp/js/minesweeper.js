minesweeper = {

    load: function(grid) {
        var click = this.click;

        for (var row = 0; row < grid.length; row++) {
            for (var col = 0; col < grid[row].length; col++) {
                this.cell(row, col).addEventListener('click', function(event) {
                    click(this, grid[row][col]);
                }) ;
            }
        }
    },

    click: function(cell, state) {
        console.log(args);
        if (state == 'bomb') {
            return function() { cell.className = 'lost' };
        } else {
            return function() { cell.className = 'safe' };
        }
    },

    cell: function(row, col) {
        return document.querySelector("#cell-" + (row + 1) + "x" + (col + 1));
    }
};
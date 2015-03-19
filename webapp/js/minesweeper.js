minesweeper = {

    grid: [],

    load: function(container) {

        var grid = this.grid;
        for (var row = 0; row < grid.length; row++) {
            for (var col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 'bomb') {
                    container.querySelector("#cell-" + (row+1) + "x" + (col+1)).className = 'lost';
                }
            }
        }
    }

};